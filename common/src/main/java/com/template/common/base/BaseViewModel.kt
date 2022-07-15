package com.template.common.base

import androidx.lifecycle.ViewModel
import com.template.share.livedata.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {

    protected var TAG = this.javaClass.simpleName
    val clickBackEvent by lazy { SingleLiveEvent<Unit>() }
    val loadingIndicator by lazy { SingleLiveEvent<Boolean>() }
    val showDialogSucess by lazy { SingleLiveEvent<String>() }
    val showDialogError by lazy { SingleLiveEvent<String>() }

    override fun onCleared() {
        super.onCleared()
        System.gc()
    }

    fun onClickBackHander() {
        clickBackEvent.call()
    }

    fun showDialogError() {
        showDialogError.call()
    }

}