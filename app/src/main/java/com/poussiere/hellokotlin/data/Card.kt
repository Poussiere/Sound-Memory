package com.poussiere.hellokotlin.data

/**
 * Created by poussiere on 24/01/18.
 */

data class Card (val id : Int, val song : Int,  var checked : Boolean = false , var discovered : Boolean = false)
