package com.merttutsak.starter.utility.extension

import com.merttutsak.starter.data.remote.service.resource.Resource
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun <T> applyLoading(): ObservableTransformer<Resource<T>, Resource<T>> = ObservableTransformer { upstream ->
    Observable.just(Resource.loading<T>()).concatWith(upstream)
}


operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}
