package com.kliachenko.core

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface GsonWrapper {

    fun <T> toJson(data: T): String

    fun <T> fromJson(json: String, resultClass: Class<T>): T

    fun <T> listToJson(list: List<T>): String

    fun <T> jsonToList(json: String, clazz: Class<Array<T>>): List<T>

    fun <T> fromJsonToList(json: String, typeToken: TypeToken<List<T>>): List<T>

    class Base(private val gson: Gson): GsonWrapper {

        override fun <T> toJson(data: T): String {
            return gson.toJson(data)
        }

        override fun <T> fromJson(json: String, resultClass: Class<T>): T {
            return gson.fromJson(json, resultClass)
        }

        override fun <T> listToJson(list: List<T>): String {
            return gson.toJson(list)
        }

        override fun <T> jsonToList(json: String, clazz: Class<Array<T>>): List<T> {
            return gson.fromJson(json, clazz).toList()
        }

        override fun <T> fromJsonToList(json: String, typeToken: TypeToken<List<T>>): List<T> {
            return gson.fromJson(json, typeToken.type)
        }


    }

}

//    fun errorApiMessage(errorBody: String?): String {
//        return try {
//            gson.fromJson(errorBody, String::class.java) ?: "Unknown error"
//        } catch (e: JsonSyntaxException) {
//            "Error parsing error message"
//        }
//    }
