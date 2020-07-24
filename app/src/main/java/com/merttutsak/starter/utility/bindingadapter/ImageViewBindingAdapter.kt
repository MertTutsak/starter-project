package com.merttutsak.starter.utility.bindingadapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.merttutsak.starter.R
import com.merttutsak.starter.utility.extension.*

object ImageViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("bind:setImageBase64")
    fun setImageBase64(view: ImageView?, base64Str: String?) {
        base64Str?.let {
            view?.loadImageFromBase64(base64Str)
        }

    }

    @JvmStatic
    @BindingAdapter("bind:createQrCode")
    fun createQrCode(view: ImageView?, base64Str: String?) {
        base64Str?.let {
            view?.createQrCodeWithColor(
                base64Str,
                ContextCompat.getColor(view.context, R.color.colorPrimary),//TODO change
                ContextCompat.getColor(view.context, R.color.colorAccent)//TODO change
            )
        }

    }

    @JvmStatic
    @BindingAdapter(value = ["bind:setImage", "bind:setImagePlaceHolder"], requireAll = false)
    fun setImage(view: ImageView?, url: String?, placeholder: Drawable?) {
        url?.let {
            view?.loadImage(url, placeholder, null)
        }

    }

    @JvmStatic
    @BindingAdapter(
        value = ["bind:setImageNoCache", "bind:setImagePlaceHolder"],
        requireAll = false
    )
    fun setImageNoCache(view: ImageView?, url: String?, placeholder: Drawable?) {
        url?.let {
            view?.loadImage(url, placeholder, hasCache = false, libType = ImgLibType.PICASSO)
        }

    }

    @JvmStatic
    @BindingAdapter(
        value = ["bind:setImageCenterInside", "bind:setImagePlaceHolder"],
        requireAll = false
    )
    fun setImageCenterInside(view: ImageView?, url: String?, placeholder: Drawable?) {
        url?.let {
            view?.loadImage(url, placeholder, scaleType = ImageView.ScaleType.CENTER_INSIDE)
        }
    }

    @JvmStatic
    @BindingAdapter(
        value = ["bind:setImageCenterCrop", "bind:setImagePlaceHolder"],
        requireAll = false
    )
    fun setImageCenterCrop(view: ImageView?, url: String?, placeholder: Drawable?) {
        url?.let {
            view?.loadImage(url, placeholder, scaleType = ImageView.ScaleType.CENTER_CROP)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["bind:setCircleImage", "bind:setImagePlaceHolder"], requireAll = false)
    fun setCircleImage(view: ImageView, url: String?, placeholder: Drawable?) {
        url?.let {
            view?.loadImage(url, placeholder, isCircle = true)
        }
    }

}
