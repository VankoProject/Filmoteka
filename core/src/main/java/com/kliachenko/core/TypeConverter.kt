package com.kliachenko.core

import androidx.room.TypeConverter
import com.google.gson.Gson

class TypeConverter {

    private val gsonWrapper = GsonWrapper.Base(Gson())

    @TypeConverter
    fun convertStringListToString(list: List<String>): String {
        return gsonWrapper.listToJson(list)
    }

    @TypeConverter
    fun convertStringToStringList(data: String): List<String> {
        return gsonWrapper.jsonToList(data, Array<String>::class.java)
    }
}