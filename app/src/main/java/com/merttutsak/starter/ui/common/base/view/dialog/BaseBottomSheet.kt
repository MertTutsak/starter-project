package com.merttutsak.starter.ui.common.base.view.dialog

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.merttutsak.starter.R
import com.merttutsak.starter.ui.main.component.ExampleBottomSheetDialog
import com.merttutsak.starter.utility.extension.*
import kotlinx.android.synthetic.main.bottomsheet_dialog_base.*

abstract class BaseBottomSheet : BottomSheetDialogFragment() {

    private var rootView: View? = null
    protected var contentView: View? = null
    private var behavior: BottomSheetBehavior<*>? = null

    private var isHandleCollapseEnabled: Boolean = true
    protected var isHandleExpandEnabled: Boolean = true

    var onStateListener: OnStateListener? = null

    public abstract fun getContentId(): Int
    public abstract fun bindView(view: View, savedInstanceState: Bundle?)
    public abstract fun onBottomSheetStateChanged(bottomSheet: View, newState: Int)
    public abstract fun onBottomSheetSlide(bottomSheet: View, slideOffset: Float)

    override fun onCreate(savedInstanceState: Bundle?) {
        //performDI()//TODO bunu konuş
        super.onCreate(savedInstanceState)
    }

    private fun setBinding(): View? {
        contentView = LayoutInflater.from(context).inflate(getContentId(), bottomSheetContent, true)
        return contentView
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        rootView = inflater.inflate(R.layout.bottomsheet_dialog_base, container, false).rootView
        if (activity.isNotNull()) {
            bottomSheetContent?.setPadding(0, 0, 0, activity!!.getNavigationbarHeight())
        }

        rootView?.context?.theme?.applyStyle(
            R.style.AppBottomSheetDialogTheme
            , true
        )
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val window = requireDialog().window
        window!!.findViewById<View>(com.google.android.material.R.id.container).fitsSystemWindows =
            true
        val decorView = window.decorView
        decorView.setPadding(0, 0, 0, activity!!.getNavigationbarHeight() * -1)
        /*bottomSheetContent.setPadding(
            bottomSheetContent.left,
            bottomSheetContent.top,
            bottomSheetContent.right,
            bottomSheetContent.bottom + activity!!.getNavigationbarHeight()
        )*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            decorView.systemUiVisibility =
                decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.waitForLayout {
            val bottomSheet =
                (requireDialog().findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)) as FrameLayout
            bottomSheet.fitsSystemWindows = false
            behavior = BottomSheetBehavior.from(bottomSheet)
            if (behavior != null) {
                behavior?.apply {
                    isHideable = true
                    state = BottomSheetBehavior.STATE_EXPANDED
                    peekHeight = 0
                    addBottomSheetCallback(object :
                        BottomSheetBehavior.BottomSheetCallback() {
                        override fun onStateChanged(bottomSheet: View, newState: Int) {
                            onBottomSheetStateChanged(bottomSheet, newState)
                            if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                                if (isResumed && requireDialog().isShowing) {
                                    dismiss()
                                }
                            }
                        }

                        override fun onSlide(bottomSheet: View, slideOffset: Float) {
                            setIndicator(slideOffset)
                            setOverlay(slideOffset)
                            onBottomSheetSlide(bottomSheet, slideOffset)
                        }
                    })
                }
            }
        }
        setBinding().notNull { v -> bindView(v, savedInstanceState) }
        bottomSheetRL.setOnDebouncedClickListener {
            dismissBottomSheet(true)
        }
        if (isHandleExpandEnabled) {
            this.handleExpand(tag ?: "")
            onStateListener?.onExpand(tag ?: "")
        } else {
            isHandleExpandEnabled = true
        }
    }

    private fun setIndicator(offset: Float) {
        bottomSheetIndicator.alpha = offset
        if (offset == 0f) {
            bottomSheetIndicator.visibility = View.INVISIBLE
        }
    }

    private fun setOverlay(offset: Float) {
        this.rootView?.background?.alpha = offset.toInt()
    }

    public fun dismissBottomSheet(isHandleCollapseEnabled: Boolean) {
        this.isHandleCollapseEnabled = isHandleCollapseEnabled
        if (behavior != null && behavior!!.state == BottomSheetBehavior.STATE_EXPANDED) {
            behavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
        } else {
            dismiss()
        }
    }

    public fun setPeekHeight(peekHeight: Int) {
        if (behavior != null) {
            behavior!!.peekHeight = peekHeight
        }
    }

    public fun showBottomSheet(supportFragmentManager: FragmentManager, tag: String) {
        this.isCancelable = true
        this.show(supportFragmentManager, tag)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnStateListener) {
            onStateListener = context as OnStateListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        onStateListener = null
    }

    override fun onDestroyView() {
        if (isHandleCollapseEnabled) {
            onStateListener?.onCollapse(tag ?: "")
            this.handleCollapse(tag ?: "")
        } else {
            isHandleCollapseEnabled = true
        }
        super.onDestroyView()
    }

    public interface OnStateListener {
        fun onExpand(tag: String)
        fun onCollapse(tag: String)
    }

    //private fun performDI() = AndroidSupportInjection.inject(this)//TODO bunu konuş
}

fun BaseBottomSheet?.handleExpand(tag: String) {
    this?.activity?.let {
        when (tag) {
            ExampleBottomSheetDialog::class.java.simpleName -> {
                //TODO handle expand
            }
        }
    }
}

fun BaseBottomSheet?.handleCollapse(tag: String) {
    this?.activity?.let {
        when (tag) {
            ExampleBottomSheetDialog::class.java.simpleName -> {
                //TODO handle collapse
            }
        }
    }
}