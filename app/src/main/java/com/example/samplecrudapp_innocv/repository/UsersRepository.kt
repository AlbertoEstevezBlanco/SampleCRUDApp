package com.example.samplecrudapp_innocv.repository

import com.example.samplecrudapp_innocv.model.User
import retrofit2.Response
import retrofit2.http.*

interface UsersRepository {

    @Headers("{Accept: application/json}")
    @GET("/api/User")
    suspend fun getUsers(): Response<List<User>>

    @Headers("{Accept: application/json}")
    @GET("/api/User/{id}")
    suspend fun getUserById(@Path("id") id: Int): Response<User>

    @DELETE("/api/User/{id}")
    suspend fun deleteUser(@Path("id") id: Int): Response<Unit>

    @Headers("{Content-Type: application/json}")
    @POST("/api/User")
    suspend fun postUser(@Body user: User): Response<Unit>

    @Headers("{Content-Type: application/json}")
    @PUT("/api/User")
    suspend fun updateUser(@Body user: User): Response<Unit>
}