package com.merttutsak.starter.ui.common.base.view.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import com.merttutsak.starter.ui.common.base.BaseNavigator
import com.merttutsak.starter.ui.common.base.BaseViewModel
import com.merttutsak.starter.ui.common.components.progressdialog.LottieProgressDialog
import com.merttutsak.starter.utility.extension.isNull
import com.merttutsak.starter.utility.extension.notNull
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel<*>> : Fragment(),
    BaseNavigator{

    abstract fun getLayoutId(): Int

    abstract var viewDataBinding: VB

    abstract var viewModel: VM

    abstract fun bindScreen()

    override fun getContext(): Context = this.requireContext()

    private var progressDialog: LottieProgressDialog? = null
    var rootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false)
            ButterKnife.bind(this, rootView!!)
            bindScreen()
        }
        return rootView
    }

    override fun showLoading() {
        hideLoading()
        this.context.notNull { cxt ->
            progressDialog.isNull {
                progressDialog = LottieProgressDialog(cxt)
            }
            progressDialog?.show()
        }
    }

    override fun hideLoading() {
        progressDialog.notNull { it.cancel() }
    }

    //    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//        if (context is BaseActivity) {
//            val activity = context as BaseActivity?
//            this.parentActivity = activity
//            activity?.onFragmentAttached()
//        }
//    }

    private fun performDI() = AndroidSupportInjection.inject(this)

    interface CallBack {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }
}