package com.gmg.user.utils

data class LiveDataState<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> completed(data: T?): LiveDataState<T> {
            return LiveDataState(Status.COMPLETED, data, null)
        }

        fun <T> error(msg: String, data: T?): LiveDataState<T> {
            return LiveDataState(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): LiveDataState<T> {
            return LiveDataState(Status.LOADING, data, null)
        }

        fun <T> emptyData(data: T?): LiveDataState<T> {
            return LiveDataState(Status.LOADING, data, null)
        }

        fun <T> canceled(data: T?): LiveDataState<T> {
            return LiveDataState(Status.CANCELED, data, null)
        }

    }

}