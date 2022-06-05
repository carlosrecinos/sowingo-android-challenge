package com.recinos.sowingo_android_challenge.main

sealed class ApiResponse<T>(
    val data : T? = null,
    val error : String? = null
){
    class Success<T> (data : T) : ApiResponse<T>(data = data)
    class Failure<T> (error : String) : ApiResponse<T>(error = error)
}