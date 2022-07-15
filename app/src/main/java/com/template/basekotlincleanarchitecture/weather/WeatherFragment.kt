package com.template.basekotlincleanarchitecture.weather

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.widgetcommon.dialog.DialogError
import com.template.basekotlincleanarchitecture.R
import com.template.basekotlincleanarchitecture.databinding.FragmentWeatherBinding
import com.template.common.base.BaseFragment
import com.template.sharelocal.Constant
import com.template.sharelocal.convertCelsiusToFahrenheit
import com.template.sharelocal.convertFahrenheitToCelsius
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
        binding?.tvTemp?.setOnClickListener {
            if (binding?.tvTemp?.text.toString().contains("C")) {
                binding?.tvTemp?.text =
                    "${viewModel.weatherCurrentEventLiveData.value?.main?.temp?.convertCelsiusToFahrenheit()} F"

            } else {
                binding?.tvTemp?.text =
                    "${viewModel.weatherCurrentEventLiveData.value?.main?.temp?.toInt()} C"

            }

        }
        binding?.imgSearch?.setOnClickListener {
            city = binding?.edtSearch?.text.toString()
            viewModel.searchCurrentWeather(city)
        }

        binding?.imgNext?.setOnClickListener {
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

        viewModel.weatherCurrentEventLiveData.observe(viewLifecycleOwner) {
            binding?.tvTemp?.text = "${it?.main?.temp?.toInt().toString()} C"
            binding?.weatherCurrent = it
        }
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
                val longi = locationGPS.longitude
                viewModel.getLocation(lat, longi)
            } else {
                toast(getString(R.string.cant_get_location))
            }
        }
    }

    override fun getLayout(): Int = R.layout.fragment_weather
    override val viewModel: WeatherViewModel by viewModels()

}