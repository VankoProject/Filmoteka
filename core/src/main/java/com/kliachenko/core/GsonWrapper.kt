package com.kliachenko.core

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

class GsonWrapper {

    private val gson = Gson()

    fun errorApiMessage(errorBody: String?): String {
        return try {
            gson.fromJson(errorBody, String::class.java) ?: "Unknown error"
        } catch (e: JsonSyntaxException) {
            "Error parsing error message"
        }
    }

}