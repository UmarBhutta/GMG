package com.gmg.user.data.repo

import com.gmg.user.data.network.ApiHelper


class UserRepo (private val apiHelper: ApiHelper) {

    suspend fun getUsers(pageNumber:Int) =  apiHelper.getUser(pageNumber)

}