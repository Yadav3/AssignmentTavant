package com.example.assignmenttavant.usecase

import com.example.assignmenttavant.network.ResultData
import com.example.assignmenttavant.repository.DataRepository
import javax.inject.Inject

class DataUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {

}