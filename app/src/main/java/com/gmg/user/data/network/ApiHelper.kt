package com.gmg.user.data.network

import com.gmg.user.data.model.UserResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getUser(pageNumber:Int) : Response<UserResponse>
}