package ru.exercise.domain.ext

import org.json.JSONObject

fun JSONObject.getMessage(): String {
    return getJSONObject("error").getString("message")
}