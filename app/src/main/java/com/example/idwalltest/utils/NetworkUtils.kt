package com.example.idwalltest.utils

import retrofit2.Response
import java.io.IOException
import com.example.idwalltest.data.Result

suspend fun <T : Any> safeApiCall(
    call: suspend () -> Response<T>,
    errorMessage: String
): Result<T> {
    return try {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(body)
            }
        }
        return Result.Error(IOException("$errorMessage ${response.code()} : ${response.message()}"))
    } catch (e: Exception) {
        Result.Error(IOException(errorMessage, e))
    }
}
