package com.merttutsak.starter.utility.transition

import com.merttutsak.starter.R


open class Transition(val enterAnim: Int, val exitAnim: Int, val enterAnimBack: Int, val exitAnimBack: Int) {

    class TransitionSlide :
        Transition(R.anim.slide_from_right, R.anim.slide_to_left, R.anim.slide_from_left, R.anim.slide_to_right)

    class TransitionSlideUpDown :
        Transition(R.anim.enter_in_bottom, R.anim.no_anim, R.anim.no_anim, R.anim.exit_out_bottom)

    class TransitionAlpha :
        Transition(R.anim.alpha_enter, R.anim.alpha_exit, R.anim.no_anim, R.anim.alpha_exit)

    class TransitionAlphaLongExit :
        Transition(R.anim.alpha_enter, R.anim.alpha_exit, R.anim.alpha_enter, R.anim.alpha_exit_long)

    class TransitionFadeInOut :
        Transition(R.anim.fade_in, R.anim.fade_out, R.anim.no_anim, R.anim.fade_out)

    class TransitionNoAnim :
        Transition(R.anim.no_anim, R.anim.no_anim, R.anim.no_anim, R.anim.no_anim)
}