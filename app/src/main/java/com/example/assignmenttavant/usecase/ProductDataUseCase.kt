package com.example.assignmenttavant.usecase

import com.example.assignmenttavant.model.ProductModel
import com.example.assignmenttavant.network.ResultData
import com.example.assignmenttavant.repository.DataRepository
import javax.inject.Inject

class ProductDataUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {
    suspend fun getPeopleRepositoriesList(): ResultData<ProductModel> {
        val repositoriesModel = dataRepository.getRepositoriesProductList()

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