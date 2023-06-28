package com.example.assignmenttavant.repository

import com.example.assignmenttavant.model.ProductModel
import com.example.assignmenttavant.model.RoomsModel
import com.example.assignmenttavant.network.ApiService
import javax.inject.Inject

class DataRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getRepositoriesProductList(): ProductModel {
        return apiService.getProduct()
    }

    suspend fun getRoomsPeopleList(): RoomsModel {
        return apiService.getRoom()
    }
}