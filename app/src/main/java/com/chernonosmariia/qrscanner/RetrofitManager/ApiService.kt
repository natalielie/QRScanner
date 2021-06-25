package com.chernonosmariia.qrscanner.RetrofitManager

import com.chernonosmariia.qrscanner.Data.RetrofitConstants
import com.chernonosmariia.qrscanner.Models.Request.LoginRequest
import com.chernonosmariia.qrscanner.Models.Response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @POST(RetrofitConstants.LOGIN_URL)
    @FormUrlEncoded
    fun login(@Body request: LoginRequest): Call<LoginResponse>

}