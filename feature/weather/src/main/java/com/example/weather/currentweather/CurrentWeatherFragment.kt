package com.example.weather.currentweather

import androidx.fragment.app.viewModels
import com.example.weather.R
import com.example.weather.databinding.FragmentCurrentWeatherBinding
import com.template.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentWeatherFragment :
    BaseFragment<FragmentCurrentWeatherBinding, CurrentWeatherViewModel>() {

    var city: String? = null

    override fun initView() {
    }

    override fun initControl() {
        binding?.imgSearch?.setOnClickListener {
            city = binding?.edtSearch?.text.toString()
            viewModel.searchCurrentWeather(city)
        }
    }

    override fun initViewModel() {
    }

    override fun getLayout(): Int = R.layout.fragment_current_weather
    override val viewModel: CurrentWeatherViewModel by viewModels()

}