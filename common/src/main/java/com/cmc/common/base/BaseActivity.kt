package com.cmc.common.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    protected val TAG = this.javaClass.simpleName

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayout())
        binding.lifecycleOwner = this
        initViewModel()
        initView()
        initControl()

    }

    private fun showDialogError(it: String) {
        //TODO

    }

    abstract fun initControl()

    abstract fun initView()

    abstract fun initViewModel()

    @LayoutRes
    abstract fun getLayout(): Int


}