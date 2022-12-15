package com.example.test.repository

import android.util.Log
import com.example.test.models.MeaningResponse
import com.example.test.network.ApiService
import com.example.test.utils.NetworkResponse
import java.lang.Exception
import javax.inject.Inject

class BaseRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getMeaning(word: String): NetworkResponse<MeaningResponse?> {
        try{
            val response = apiService.getMeaning(word = word)
            if(response.code() == 200){
                return NetworkResponse.Success(response.body())
            }
            return NetworkResponse.Error(response.errorBody().toString())
        }
        catch (e:Exception){
            Log.v(TAG, e.message.toString())
            return NetworkResponse.Error(message = "Something went wrong")

        }
    }

    companion object{
        const val TAG = "RepoTag"
    }
}