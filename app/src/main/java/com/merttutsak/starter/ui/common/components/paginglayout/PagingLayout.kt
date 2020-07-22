package com.merttutsak.starter.ui.common.components.paginglayout

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.orhanobut.logger.Logger
import android.os.Handler
import androidx.core.widget.NestedScrollView
import com.github.ybq.android.spinkit.SpinKitView
import com.merttutsak.starter.R

class PagingLayout : LinearLayout {

    @BindView(R.id.conatinerLoading)
    lateinit var conatinerLoading: LinearLayout
    @BindView(R.id.recyclerViewPaging)
    lateinit var recyclerViewPaging: RecyclerView
    @BindView(R.id.spinKitPaging)
    lateinit var spinKitPaging: SpinKitView

    private var isLoadingEnabled: Boolean = true
        set(value) {
            if (!value) {
                isLastPage = true
            }
            field = value
        }
    var isLastPage = false
    var defaultPageIndex = 0
    var pageIndex: Int = 0
    var isLoading = false

    var onLoadingInterface: OnLoadingInterface? = null

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val view = View.inflate(context, R.layout.component_layout_paging, this)
        ButterKnife.bind(this, view)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.PagingLayout)

        try {
            pageIndex =
                attributes.getInteger(
                    R.styleable.PagingLayout_starPageIndex,
                    0
                ).also {
                    defaultPageIndex = pageIndex
                }
        } catch (e: RuntimeException) {
            e.message?.let { Logger.e(it) }
        }

        initRecyclerView()
        initLoading(attributes)
    }

    private fun initRecyclerView() {
        recyclerViewPaging.layoutManager = LinearLayoutManager(
            this@PagingLayout.context,
            RecyclerView.VERTICAL,
            false
        )
        recyclerViewPaging.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val itemCount = recyclerView.adapter!!.itemCount

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == itemCount - 1) {
                        //bottom of list!
                        if (!isLastPage) {
                            startLoading()
                        }
                    }
                }
            }
        })
    }

    private fun initLoading(attributes: TypedArray) {
        stopLoading()
        try {
            isLoadingEnabled =
                attributes.getBoolean(
                    R.styleable.PagingLayout_isLoadingEnabled,
                    true
                )
        } catch (e: RuntimeException) {
            e.message?.let { Logger.e(it) }
        }
    }

    fun setNestedScrollview(nestedScrollView: NestedScrollView) {
        recyclerViewPaging.clearOnScrollListeners()
        recyclerViewPaging.isNestedScrollingEnabled = false
        nestedScrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->

            if (v!!.getChildAt(v.childCount - 1) != null && !isLastPage && !isLoading) {
                if ((scrollY >= (v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight)) &&
                    scrollY > oldScrollY
                ) {
                    val linearLayoutManager =
                        PagingLayout@ this.recyclerViewPaging.layoutManager as LinearLayoutManager
                    var visibleItemCount = linearLayoutManager.childCount
                    var totalItemCount = linearLayoutManager.itemCount  //Loading çıkartıldı
                    var pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition()

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        startLoading()
                    }
                }
            }
        }

    }

    fun startLoading() {
        isLoading = true
        spinKitPaging.visibility = View.VISIBLE
        Handler().postDelayed(Runnable {
            onLoadingInterface?.onStartLoad()
        }, 1000)
    }

    fun stopLoading() {
        isLoading = false
        if (isLastPage) {
            spinKitPaging.visibility = View.GONE
        } else {
            spinKitPaging.visibility = View.INVISIBLE
        }
        onLoadingInterface?.onStopLoad()
    }

    fun cleanPageIndex(): Int {
        pageIndex = defaultPageIndex
        return pageIndex
    }

    fun pageIndexIncrease(): Int {
        pageIndex++
        return pageIndex
    }

    fun pageIndexDecrease(): Int {
        pageIndex--

        if (pageIndex < defaultPageIndex) {
            return cleanPageIndex()
        }

        return pageIndex
    }

    interface OnLoadingInterface {
        fun onStartLoad()
        fun onStopLoad()
    }
}