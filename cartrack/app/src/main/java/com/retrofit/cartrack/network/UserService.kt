package com.retrofit.cartrack.network

import com.retrofit.cartrack.landingUI.model.User
import retrofit2.Response
import retrofit2.http.GET


interface UserService {

   @GET("/users")
   suspend fun getUser() :Response<User>

}