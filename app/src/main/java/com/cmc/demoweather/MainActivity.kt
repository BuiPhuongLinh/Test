package com.cmc.demoweather

import androidx.activity.viewModels
import com.cmc.demoweather.databinding.ActivityMainBinding
import com.cmc.common.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    val viewModel: MainViewModel by viewModels()

    override fun initControl() {

    }

    override fun initView() {

    }

    override fun initViewModel() {
    }

    override fun getLayout(): Int = R.layout.activity_main

}