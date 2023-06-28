package com.example.assignmenttavant.usecase

import com.example.assignmenttavant.model.ProductModel
import com.example.assignmenttavant.model.RoomsModel
import com.example.assignmenttavant.network.ResultData
import com.example.assignmenttavant.repository.DataRepository
import javax.inject.Inject

class RoomsDataUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {
    suspend fun getRoomsRepositoriesList(): ResultData<RoomsModel> {
        val repositoriesModel = dataRepository.getRoomsPeopleList()

        val resultData = when(repositoriesModel.isNotEmpty()) {
            true -> {
                ResultData.Success(repositoriesModel)
            }
            else -> {
                ResultData.Failed()
            }
        }
        return resultData
    }
}