package com.poussiere.hellokotlin.data

/**
 * Created by poussiere on 24/01/18.
 */

data class Card (val id : Int, val song : Int,  var checked : Boolean = false , var discovered : Boolean = false, var discovered2: Boolean=false,
                 var p1Won : Boolean = false, var p2Won : Boolean=false)
