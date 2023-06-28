package com.example.assignmenttavant.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.assignmenttavant.model.RoomsModel
import com.example.assignmenttavant.network.ResultData
import com.example.assignmenttavant.usecase.RoomsDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow

class RoomsViewModel @ViewModelInject constructor(
    private val roomsDataUseCase: RoomsDataUseCase
): ViewModel() {
    fun getRoomsRepositoriesList(since: String): LiveData<ResultData<RoomsModel?>> {
        return flow {
            emit(ResultData.Loading())
            try {
                emit(roomsDataUseCase.getRoomsRepositoriesList())
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ResultData.Exception())
            }
        }.asLiveData(Dispatchers.IO)
    }
}