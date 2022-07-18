package com.cmc.demoweather.forecast

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cmc.demoweather.MainViewModel
import com.cmc.demoweather.R
import com.cmc.demoweather.databinding.FragmentForeCastBinding
import com.cmc.common.base.BaseFragment
import com.cmc.sharelocal.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForeCastFragment : BaseFragment<FragmentForeCastBinding, ForeCastViewModel>() {
    lateinit var adapter: ForeCastAdapter

    override fun initView() {
        val lat = arguments?.getString(Constant.KEY_FORECAST_LAT.value) ?: ""
        val lon = arguments?.getString(Constant.KEY_FORECAST_LON.value) ?: ""
        viewModel.getForeCastWeather(lat, lon)
        adapter = ForeCastAdapter(mutableListOf())
        binding.rcvWeather.adapter = adapter
    }

    override fun initControl() {
        viewModel.listForecastWeatherLiveData.observe(viewLifecycleOwner) {
            it.hourly?.forEach { it.temperatureType = mainViewModel.currentTemperatureType.value?.ordinal }
            adapter.addList(it.hourly ?: mutableListOf())
        }
        viewModel.clickBackEvent.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }

    override fun initViewModel() {
    }

    override fun getLayout(): Int = R.layout.fragment_fore_cast
    override val viewModel: ForeCastViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
}