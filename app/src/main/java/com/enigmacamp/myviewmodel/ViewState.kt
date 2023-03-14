package com.enigmacamp.simpleviewmodel

class ViewState(val status: ViewStatus, val data: Any?, val message: String?) {
    companion object {
        fun success(data: Any?) = ViewState(status = ViewStatus.SUCCESS, data, null)
        fun error(message: String?) = ViewState(status = ViewStatus.ERROR, null, message)
        fun loading() = ViewState(status = ViewStatus.LOADING, null, null)
    }
}