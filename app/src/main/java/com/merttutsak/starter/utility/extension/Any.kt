package com.merttutsak.starter.utility.extension

import java.lang.Exception

/**Example**/
/**
 * var any:Any? = null
 * if(any.isNull()) -> true
 * any = Any()
 *
 * if(any.isNull()) -> false
 * */
fun Any?.isNull(): Boolean {
    return (this == null)
}

/**Example**/
/**
 * var any:Any? = null
 * if(any.isNull()) -> false
 * any = Any()
 *
 * if(any.isNull()) -> true
 * */
fun Any?.isNotNull(): Boolean {
    return (this != null)
}

/**
 * if the variable isn't null, the function runs
 */
fun <R> R?.notNull(f: (it: R) -> Unit) {
    if (this != null) {
        f(this)
    }
}

fun <R> R?.isNull(f: (it: R?) -> Unit) {
    if (this == null) {
        f(this)
    }
}

/**
 * If toString convert from a null integer variable to string variable in the kotlin default, result is seen "null".
 * The extension is seen empty ("").
 *
 * /** Example **/
 *
 * kotlin default extension ->
 * var i:Int? = null
 * i.toString() -> null
 * custom extension  ->
 * var i:Int? = null
 * i.toString() ->
 */
fun Any?.toString(): String {
    return if (this.isNull()) {
        ""
    } else {
        try {
            this!!.toString()
        } catch (e: Exception) {
            ""
        }
    }
}

fun <T> Any?.castOrNull(): T? {
    return try {
        this as T
    } catch (e: Exception) {
        null
    }
}

