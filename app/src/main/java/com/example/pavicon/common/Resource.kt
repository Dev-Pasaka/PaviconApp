package com.example.pavicon.common


sealed class Resource<T>(
    val data:T? = null,
    val message:String? = null,
) {
    class Success<T>(data: T, message: String):Resource<T>(data = data, message)
    class Failed<T>(data: T, message: String):Resource<T>(data = data, message)
    class Error<T>(message: String, data: T? = null):Resource<T>(message = message,data = data)
    class Loading<T>(data: T? = null, message: String):Resource<T>(data, message)
}