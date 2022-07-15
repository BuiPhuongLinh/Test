package com.template.basekotlincleanarchitecture.forecast

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.template.basekotlincleanarchitecture.R
import com.template.basekotlincleanarchitecture.databinding.FragmentForeCastBinding
import com.template.common.base.BaseFragment
import com.template.sharelocal.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForeCastFragment : BaseFragment<FragmentForeCastBinding, ForeCastViewModel>() {
    lateinit var adapter: ForeCastAdapter

    override fun initView() {
        val lat = arguments?.let { it.getString(Constant.KEY_FORECAST_LAT.value) } ?: ""
        val lon = arguments?.let { it.getString(Constant.KEY_FORECAST_LON.value) } ?: ""
        viewModel.getForeCastWeather(lat, lon)
        adapter = ForeCastAdapter(mutableListOf())
        binding?.rcvWeather?.adapter = adapter
    }

    override fun initControl() {
        viewModel.listForecastWeatherLiveData.observe(viewLifecycleOwner) {
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
}