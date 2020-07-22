package com.merttutsak.starter.utility.provider

import android.os.Handler
import com.squareup.otto.Bus
import com.squareup.otto.ThreadEnforcer

class BusProvider {

    companion object {
        private var bus: InnerBus? = null

        fun getInstance(): InnerBus {
            if (bus == null) {
                bus = BusProvider().InnerBus()
            }

            return bus!!
        }

    }

    inner class InnerBus : Bus(ThreadEnforcer.ANY) {

        fun postDelayed(event: Any, delayMillis: Long) {
            Handler().postDelayed({ post(event) }, delayMillis)
        }

    }
}