package com.example.moregetandpostrequests

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIinterface {

    //get request: get -read- data from API
    @Headers("Content-Type: application/json")
    @GET("/test/?format=json")
    fun getDate (): Call<List<myData.myDataItem>>?

    //post request: add -insert- data to API
    //we must use @Body
    @Headers("Content-Type: application/json")
    @POST("/test/?format=json")
    fun postData (@Body info:myData.myDataItem): Call<myData.myDataItem>
}