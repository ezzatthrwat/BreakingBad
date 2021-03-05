package me.ezzattharwat.breakingbad.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
        fun <T> paginatingLoading(data: T?): Resource<T> {
            return Resource(Status.PAGINATING_LOADING, data, null)
        }
        fun <T> empty(data: T?): Resource<T> {
            return Resource(Status.EMPTY, data, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    PAGINATING_LOADING,
    EMPTY
}