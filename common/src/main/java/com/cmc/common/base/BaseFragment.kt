package com.cmc.common.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.widgetcommon.dialog.DialogError
import com.example.widgetcommon.dialog.DialogLoading
import com.cmc.common.BR

abstract class BaseFragment<B : ViewDataBinding, M : BaseViewModel> : Fragment() {

    protected val TAG = this.javaClass.simpleName

    protected lateinit var binding: B
    protected abstract val viewModel: M

    private var loadingDialog: DialogLoading? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        initViewModel()
        initView()
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initControl()
        viewModel.apply {
            loadingIndicator.observe(viewLifecycleOwner) {
                if (it) {
                    showDialogLoading()
                } else {
                    hideDialogLoading()
                }
            }
            clickBackEvent.observe(viewLifecycleOwner) {
                findNavController().popBackStack()
            }
            showDialogError.observe(viewLifecycleOwner) {
                if (!it.isNullOrEmpty()) {
                    showDialogError(it)
                }
            }
        }
    }

    private fun hideDialogLoading() {
        if (loadingDialog != null) {
            try {
                loadingDialog!!.dismissLoading()
            } catch (ex: IllegalArgumentException) {
                Log.e(TAG, "Activity is already finished, no need dismissing dialog")
            }
        }
    }

    private fun showDialogLoading() {
        loadingDialog = DialogLoading(requireContext())
        loadingDialog!!.showLoading()
    }

    private fun showDialogError(message: String) {
        val dialog = DialogError(requireContext())
        dialog.showDialog(message)
    }

    protected fun toast(id: Int) {
        toast(getString(id))
    }

    @SuppressLint("ShowToast")
    protected fun toast(message: String) {
        if (!TextUtils.isEmpty(message)) {
            activity?.let { Toast.makeText(activity, message, Toast.LENGTH_LONG).show() }
        } else {
            Log.e(TAG, "Message is null")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (loadingDialog != null) {
            loadingDialog!!.dismissLoading()
        }
    }

    abstract fun initView()

    abstract fun initControl()

    abstract fun initViewModel()

    @LayoutRes
    abstract fun getLayout(): Int
}