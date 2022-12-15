package com.example.test.network

import com.example.test.models.MeaningResponse
import com.example.test.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {

    @GET("v4/dictionary/{word}")
    suspend fun getMeaning(@Header("Authorization") token:String = Constants.TOKEN,
                           @Path("word") word:String) :Response<MeaningResponse>
}