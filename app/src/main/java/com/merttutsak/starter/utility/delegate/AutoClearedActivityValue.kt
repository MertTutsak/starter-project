package com.merttutsak.starter.utility.delegate

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class AutoClearedActivityValue<T : Any> : ReadWriteProperty<AppCompatActivity, T>, LifecycleObserver {
    private var _value: T? = null

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T =
        _value
            ?: throw IllegalStateException("Trying to call an auto-cleared value outside of the view lifecycle.")

    override fun setValue(thisRef: AppCompatActivity, property: KProperty<*>, value: T) {
        thisRef.lifecycle.removeObserver(this)
        _value = value
        thisRef.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        _value = null
    }
}