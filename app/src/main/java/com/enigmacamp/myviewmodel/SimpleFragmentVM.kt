package com.enigmacamp.myviewmodel

import androidx.lifecycle.ViewModel

class SimpleFragmentVM : ViewModel() {
    var totalCustomer: Int = 0

    fun addTotalCustomer() {
        totalCustomer += 1
    }
}