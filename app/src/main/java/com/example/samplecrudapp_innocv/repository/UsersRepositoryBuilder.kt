package com.example.samplecrudapp_innocv.repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

private const val baseURL = "https://hello-world.innocv.com/"

object UsersRepositoryBuilder {

    fun getInstance(): Retrofit {
        val gson = GsonBuilder().registerTypeAdapter(Date::class.java, DateDeserializer()).create()
        return Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(
                GsonConverterFactory.create(gson)
            )
            .build()
    }
}