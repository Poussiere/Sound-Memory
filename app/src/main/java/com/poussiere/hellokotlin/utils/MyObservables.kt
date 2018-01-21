package com.poussiere.hellokotlin.utils

import com.poussiere.hellokotlin.R
import rx.Observable
import rx.Observable.create
import rx.Subscriber


class MyObservables{

    val textTab= arrayOf(R.string.msg1, R.string.msg2, R.string.msg3, R.string.msg4, R.string.msg5 )

    fun getFirstObservable(): Observable <Int> {
        var obs = create(object : Observable.OnSubscribe<Int> {

            override fun call(subscriber: Subscriber<in Int>?) {

                for (i in textTab) {
                    subscriber?.onNext(i)
                }
                subscriber?.onCompleted()

            }})

        return obs
    }



}