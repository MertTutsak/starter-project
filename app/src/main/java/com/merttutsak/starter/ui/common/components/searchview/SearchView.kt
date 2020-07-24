package com.merttutsak.starter.ui.common.components.searchview

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import butterknife.BindView
import butterknife.ButterKnife
import com.merttutsak.starter.R
import com.merttutsak.starter.R.styleable
import com.merttutsak.starter.utility.extension.hideKeyboard
import com.orhanobut.logger.Logger

class SearchView : RelativeLayout {
    @BindView(R.id.editTextSearch)
    lateinit var editTextSearch: EditText
    @BindView(R.id.imageViewSearchClear)
    lateinit var imageViewClear: ImageView

    var searchText: String = ""
    private var hintText: String = ""
    var minCount: Int = 0

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

    var textListener: TextListener? = null
    var eventListener: EventListener? = null

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init(context, attrs)
    }

    @SuppressLint("CustomViewStyleable")
    private fun init(context: Context, attrs: AttributeSet?) {
        val view = View.inflate(context, R.layout.component_search_view, this)
        ButterKnife.bind(this, view)
        if (attrs != null) {
            val attributes = context.obtainStyledAttributes(attrs, styleable.CustomSearchView)
            try {
                minCount =
                    attributes.getInt(
                        styleable.CustomSearchView_minCount,
                        0
                    )

                hintText = attributes.getString(
                    styleable.CustomSearchView_hintText
                )?:""
            } catch (e: RuntimeException) {
                e.message?.let { Logger.e(it) }
            }
            attributes.recycle()
        }
        isWarningMode(false)
        editTextSearch.setOnFocusChangeListener { v, hasFocus ->
            eventListener?.onChangeFocus(v, hasFocus)
        }
        editTextSearch.hint = hintText

        editTextSearch.addTextChangedListener(mTextWatcher)
        editTextSearch.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search()
            }
            true
        }
        imageViewClear.setOnClickListener {
            clear()
        }
    }

    private val mTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
        }

        override fun onTextChanged(text: CharSequence, i: Int, i1: Int, i2: Int) {
            imageViewClear.visibility = if (text.isNotEmpty()) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
            searchText = text.toString()
            textListener?.onChangeText(text.toString())
        }

        override fun afterTextChanged(editable: Editable) {
        }
    }

    private fun search() {
        if (searchText.length >= minCount) {
            context.hideKeyboard()
            textListener?.onQueryText(editTextSearch.text.toString())
        }
    }

    fun clear() {
        context.hideKeyboard()
        editTextSearch.setText("")
        if (editTextSearch.hasFocus()) {
            editTextSearch.clearFocus()
        }
    }

    fun isWarningMode(isWarning: Boolean) {
        if (isWarning) {
            MaximoSearchView@ this.background =
                ContextCompat.getDrawable(context, R.drawable.searchview_warning_bg)
        } else {
            MaximoSearchView@ this.background =
                ContextCompat.getDrawable(context, R.drawable.search_view_bg)
        }
    }

    interface TextListener {
        fun onQueryText(text: String)
        fun onChangeText(text: String)
    }

    interface EventListener {
        fun onChangeFocus(view: View, hasFocus: Boolean)
    }
}