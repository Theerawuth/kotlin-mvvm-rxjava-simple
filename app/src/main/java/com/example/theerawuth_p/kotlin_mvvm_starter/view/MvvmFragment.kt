package com.example.theerawuth_p.kotlin_mvvm_starter.view

import android.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by theerawuth_p on 10/17/2017 AD.
 */
open class MvvmFragment: android.support.v4.app.Fragment() {

    val subsciptions = CompositeDisposable()

    fun subscribe(disposable: Disposable): Disposable {
        subsciptions.add(disposable)
        return disposable
    }

    override fun onStop() {
        super.onStop()
        subsciptions.dispose()
    }

}