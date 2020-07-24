package com.merttutsak.starter.utility.extension

/**
 * if the variable isn't null, the function runs
 */
fun Boolean?.isTrue(f: () -> Unit) {
    if (this == true) {
        f()
    }
}

fun Boolean?.isNotTrue(f: () -> Unit) {
    if (this != true) {
        f()
    }
}


fun Boolean?.isFalse(f: () -> Unit) {
    if (this == false) {
        f()
    }
}