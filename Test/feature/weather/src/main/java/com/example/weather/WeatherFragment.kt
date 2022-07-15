package com.example.weather

import androidx.fragment.app.viewModels
import com.example.weather.databinding.FragmentWeatherBinding
import com.template.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : BaseFragment<FragmentWeatherBinding, WeatherViewModel>() {


    override fun initView() {
    }

    override fun initControl() {

    }

    override fun initViewModel() {

    }

    override fun getLayout(): Int = R.layout.fragment_weather
    override val viewModel: WeatherViewModel by viewModels()

}