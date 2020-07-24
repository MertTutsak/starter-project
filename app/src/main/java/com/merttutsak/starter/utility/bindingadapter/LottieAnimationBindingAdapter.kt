package com.merttutsak.starter.utility.bindingadapter

import android.animation.ValueAnimator
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.merttutsak.starter.data.remote.model.response.animation.AnimationResponse
import com.merttutsak.starter.utility.extension.isNotNull
import com.merttutsak.starter.utility.extension.notNull

object LottieAnimationBindingAdapter {

    @JvmStatic
    @BindingAdapter("bind:setAnimation")
    fun setAnimation(view: LottieAnimationView?, animationResponse: AnimationResponse?) {
        view.notNull { v ->
            if(animationResponse.isNotNull() && !animationResponse?.jsonPath.isNullOrEmpty()){
                when(animationResponse?.repeatMode){
                    "INFINITE" -> {
                        v.repeatCount = ValueAnimator.INFINITE
                    }
                    else ->  {
                        v.repeatCount = 0
                    }
                }
                v.setAnimationFromUrl(animationResponse?.jsonPath!!)
                v.playAnimation()
            }
        }
    }

}