package com.merttutsak.starter.utility.extension

import com.merttutsak.starter.data.remote.service.resource.Resource
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable


fun <T> applyLoading(): ObservableTransformer<Resource<T>, Resource<T>> = ObservableTransformer { upstream ->
    Observable.just(Resource.loading<T>()).concatWith(upstream)
}


operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}
