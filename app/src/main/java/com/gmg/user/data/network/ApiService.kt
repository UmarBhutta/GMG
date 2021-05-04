package com.gmg.user.data.network

import com.gmg.user.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/")
    suspend fun getUsers(@Query("page") page: Int, @Query("results") results: Int = 10): Response<UserResponse>
}