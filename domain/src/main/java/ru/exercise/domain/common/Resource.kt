package ru.exercise.domain.common

import android.util.Log
import org.json.JSONObject
import retrofit2.HttpException
import ru.exercise.domain.ext.getMessage

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Loading<T>(val message: String = "") : Resource<T>()
    data class Error<T>(val code: Int = 0, val message: String = "") : Resource<T>()

    companion object {
        inline fun <T> on(function: () -> T): Resource<T> = try {
            Success(function())
        } catch (ex: Throwable) {
            when (ex) {
                is HttpException -> {
                    val jObjError = JSONObject(ex.response()?.errorBody()?.string() ?: "")
                    val errorCode = ex.response()?.code() ?: 0
                    Log.e("Resource.Error", "Exception: $ex")
                    Error(code = errorCode, message = jObjError.getMessage())
                }
                else -> {
                    Error(message = "Something went wrong.")
                }
            }

        }

    }

}
