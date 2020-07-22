package com.merttutsak.starter.ui.common.base.view.activity

import android.os.Handler
import androidx.databinding.ViewDataBinding
import com.merttutsak.starter.ui.common.base.BaseNavigator
import com.merttutsak.starter.ui.common.base.BaseViewModel
import com.merttutsak.starter.ui.common.base.view.fragment.BaseFragment


abstract class BaseFragmentActivity<VB: ViewDataBinding, VM: BaseViewModel<*>> : BaseActivity<VB, VM>() {

    protected abstract var frameLayoutId: Int

    //Frame Layout
    fun initView(fragment: BaseFragment<*, *>) {
        Handler().postDelayed(Runnable {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(frameLayoutId, fragment).commitAllowingStateLoss()
        }, 50)

    }

    fun clearStack() {
        val transaction = supportFragmentManager.beginTransaction()
        for (i in 0 until supportFragmentManager.backStackEntryCount) {
            supportFragmentManager.popBackStack()
        }
    }

    fun changeFragment(fragment: BaseFragment<*,*>) {
        Handler().postDelayed({
            //Animasyonlu geçiş
            //getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.to_right_animation, R.anim.from_left_animation, R.anim.from_right_animation, R.anim.to_left_animation).replace(R.id.frameLayout_baseActivity, fragment).addToBackStack(fragment.toString()).commit();
            supportFragmentManager.beginTransaction().replace(frameLayoutId, fragment)
                .addToBackStack(fragment.toString()).commit()
        }, 200)
    }
}
