package com.merttutsak.starter.utility.extension

/**
 * if the variable isn't null, the function runs
 */
inline fun Boolean?.isTrue(f: () -> Unit) {
    if (this == true) {
        f()
    }
}

inline fun Boolean?.isNotTrue(f: () -> Unit) {
    if (this != true) {
        f()
    }
}


inline fun Boolean?.isFalse(f: () -> Unit) {
    if (this == false) {
        f()
    }
}