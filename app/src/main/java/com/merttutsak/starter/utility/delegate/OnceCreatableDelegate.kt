package com.merttutsak.starter.utility.delegate

import kotlin.reflect.KProperty

class OnceCreatableDelegate<T>  {

    private var data: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T? = data

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        if (data != null) {
            throw Exception("This object can be initialized once.")
        }
        data = value
    }

}