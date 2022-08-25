package com.example.samplecrudapp_innocv.repository

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateDeserializer: JsonDeserializer<Date> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date? {
        val date: String = json.asString

        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        format.timeZone = TimeZone.getTimeZone("GMT")

        return try {
            format.parse(date)
        } catch (exp: ParseException) {
            null
        }
    }
}