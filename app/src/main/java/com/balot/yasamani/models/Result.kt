package com.balot.yasamani.models

sealed class Result<T>(data: T?, message: String?) {
    class Success<T>(val data: T?, val message: String?) : Result<T>(data, message) {}
    class Failure<T>(val data: T?, val message: String?) : Result<T>(data, message) {}
    class Error<T>(val data: T?, val message: String?) : Result<T>(data, message) {}
}