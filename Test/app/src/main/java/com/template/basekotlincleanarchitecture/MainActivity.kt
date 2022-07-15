package com.template.basekotlincleanarchitecture

import androidx.activity.viewModels
import com.template.basekotlincleanarchitecture.databinding.ActivityMainBinding
import com.template.common.base.BaseActivity
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