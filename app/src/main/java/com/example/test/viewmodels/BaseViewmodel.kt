package com.example.test.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.models.MeaningResponse
import com.example.test.repository.BaseRepository
import com.example.test.utils.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewmodel @Inject constructor(val baseRepository: BaseRepository):ViewModel() {

    private var _isLoading : MutableLiveData<Boolean> = MutableLiveData()
    var isLoading:LiveData<Boolean> = _isLoading

    private var _meaningResponse :MutableLiveData<NetworkResponse<MeaningResponse?>> = MutableLiveData()
    var meaningResponse:LiveData<NetworkResponse<MeaningResponse?>> = _meaningResponse

    fun getMeaning(word: String) =viewModelScope.launch {
        _isLoading.postValue(true)
        val response = baseRepository.getMeaning(word)
        _isLoading.postValue(false)
        _meaningResponse.postValue(response)
    }



}