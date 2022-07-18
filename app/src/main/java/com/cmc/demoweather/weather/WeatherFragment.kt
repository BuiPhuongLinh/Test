package com.cmc.demoweather.weather

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.widgetcommon.dialog.DialogError
import com.cmc.demoweather.MainViewModel
import com.cmc.demoweather.R
import com.cmc.demoweather.TemperatureType
import com.cmc.demoweather.databinding.FragmentWeatherBinding
import com.cmc.demoweather.extension.getTemperatureByType
import com.cmc.common.base.BaseFragment
import com.cmc.domain.entities.WeatherCurrent
import com.cmc.sharelocal.Constant
import com.cmc.sharelocal.convertCelsiusToFahrenheit
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : BaseFragment<FragmentWeatherBinding, WeatherViewModel>() {

    var city: String? = null
    private var nManager: LocationManager? = null

    override fun initView() {
        if (isLocationPermission()) {
            checkLocation()
        } else {
            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun checkLocation() {
        nManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        if (!nManager?.isProviderEnabled(LocationManager.GPS_PROVIDER)!!) {
            onGPS()
        } else {
            getLocation()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initControl() {
        binding.tvTemp.setOnClickListener {
            if (binding.tvTemp.text.toString().contains("C")) {
                binding.tvTemp.text =
                    "${viewModel.weatherCurrentEventLiveData.value?.main?.temp?.convertCelsiusToFahrenheit()} F"

            } else {
                binding.tvTemp.text =
                    "${viewModel.weatherCurrentEventLiveData.value?.main?.temp?.toInt()} C"

            }

        }
        binding.imgSearch.setOnClickListener {
            city = binding.edtSearch.text.toString()
            viewModel.searchCurrentWeather(city)
        }

        binding.imgNext.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(
                Constant.KEY_FORECAST_LON.value,
                viewModel.weatherCurrentEventLiveData.value?.coord?.lon
            )
            bundle.putString(
                Constant.KEY_FORECAST_LAT.value,
                viewModel.weatherCurrentEventLiveData.value?.coord?.lat
            )
            findNavController().navigate(R.id.action_weatherFragment_to_foreCastFragment, bundle)
        }

        binding.imgSwitch.setOnClickListener {
            val currentUnit = mainViewModel.currentTemperatureType.value
            if (currentUnit == TemperatureType.Celsius) {
                mainViewModel.currentTemperatureType.value = TemperatureType.Fahrenheit
            } else {
                mainViewModel.currentTemperatureType.value = TemperatureType.Celsius
            }
        }

        viewModel.weatherCurrentEventLiveData.observe(viewLifecycleOwner) {
            updateWeather(it)
        }

        mainViewModel.currentTemperatureType.observe(viewLifecycleOwner) {
            updateWeather()
        }
    }

    private fun updateWeather(weatherCurrent: WeatherCurrent? = viewModel.weatherCurrentEventLiveData.value) {
        binding.tvTemp.text =
            weatherCurrent?.main?.temp?.getTemperatureByType(mainViewModel.currentTemperatureType.value)
        binding.weatherCurrent = weatherCurrent
    }

    override fun initViewModel() {
    }

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                checkLocation()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                checkLocation()
            }
            else -> {
                val dialogError = DialogError(requireContext())
                dialogError.showDialog(getString(R.string.need_allow_permission))
            }
        }
    }

    private fun isLocationPermission(): Boolean {
        return (requireContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && requireContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
    }

    private fun onGPS() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setMessage(getString(R.string.enable_gps))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
            .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.cancel()
            }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        } else {
            val locationGPS = nManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (locationGPS != null) {
                val lat = locationGPS.latitude
                val long = locationGPS.longitude
                if (viewModel.weatherCurrentEventLiveData.value == null) {
                    viewModel.getLocation(lat, long)
                }
            } else {
                toast(getString(R.string.cant_get_location))
            }
        }
    }

    override fun getLayout(): Int = R.layout.fragment_weather
    override val viewModel: WeatherViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
}