package com.poussiere.hellokotlin.utils

import android.databinding.BaseObservable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject
import java.io.Serializable

/**
 * Helper class in order to easily make ViewModel fields both Databinding observable, and able to
 * consume RxJava data
 */
data class ViewModelField<T>(private var mValue : T) : BaseObservable(), Consumer<T>, Serializable {

    companion object {
        const val serialVersionUID = 1L
    }

    var onChange = PublishSubject.create<T>()

    /**
     * Value property for databinding notification
     */
    var value : T
        get() = this.mValue
        set(newValue) {
                this.mValue = newValue
                notifyChange()
                onChange.onNext(newValue)
        }

    /**
     * Update value with consumed data value
     */
    @Throws(Exception::class)
    override fun accept(newValue: T) {
        value = newValue
    }
}