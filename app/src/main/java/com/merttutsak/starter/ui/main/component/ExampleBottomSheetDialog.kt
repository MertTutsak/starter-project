package com.merttutsak.starter.ui.main.component

import android.os.Bundle
import android.view.View
import com.merttutsak.starter.R
import com.merttutsak.starter.ui.common.base.view.dialog.BaseBottomSheet

public class ExampleBottomSheetDialog : BaseBottomSheet() {

    companion object {
        fun newInstance(): ExampleBottomSheetDialog {
            return ExampleBottomSheetDialog()
        }
    }

    override fun getContentId(): Int {
        return R.layout.bottomsheet_dialog_example
    }

    override fun bindView(view: View, savedInstanceState: Bundle?) {
    }

    override fun onBottomSheetStateChanged(bottomSheet: View, newState: Int) {

    }

    override fun onBottomSheetSlide(bottomSheet: View, slideOffset: Float) {

    }

}