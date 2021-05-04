package com.gmg.user.data.network

import com.gmg.user.data.model.UserResponse
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUser(pageNumber:Int): Response<UserResponse> = apiService.getUsers(pageNumber)


}