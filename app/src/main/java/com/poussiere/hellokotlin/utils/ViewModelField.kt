package com.poussiere.hellokotlin.utils

import androidx.databinding.BaseObservable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject
import java.io.Serializable


data class ViewModelField<T>(private var mValue : T) : BaseObservable(), Consumer<T>, Serializable {

    companion object {
        const val serialVersionUID = 1L
    }

    var onChange = PublishSubject.create<T>()


    var value : T
        get() = this.mValue
        set(newValue) {
                this.mValue = newValue
                notifyChange()
                onChange.onNext(newValue!!)
        }


    @Throws(Exception::class)
    override fun accept(newValue: T) {
        value = newValue
    }
}