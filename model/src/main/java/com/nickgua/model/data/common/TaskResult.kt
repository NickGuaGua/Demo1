package com.nickgua.model.data.common

sealed class TaskResult<out R>(open val flag: RequestFlag) {
    data class Success<out T>(val data: T, override val flag: RequestFlag) : TaskResult<T>(flag)
    data class Error(val throwable: Throwable, override val flag: RequestFlag) :
        TaskResult<Nothing>(flag)

    override fun toString(): String {
        return when(this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$throwable]"
        }
    }

    inline fun <reified T> getResponse(): T? {
        return when (this) {
            is Success<*> -> {
                (this.data as T)
            }
            is Error -> {
                (this.throwable as T)
            }
        }
    }
}