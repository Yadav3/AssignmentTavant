package com.example.assignmenttavant.ui.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.assignmenttavant.model.ProductModel
import com.example.assignmenttavant.network.ResultData
import com.example.assignmenttavant.usecase.ProductDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow

class ProductViewModel @ViewModelInject constructor(
    private val productDataUseCase: ProductDataUseCase
): ViewModel() {
    fun getRepositoriesProductList(since: String): LiveData<ResultData<ProductModel?>> {
        return flow {
            emit(ResultData.Loading())
            try {
                emit(productDataUseCase.getPeopleRepositoriesList())
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ResultData.Exception())
            }
        }.asLiveData(Dispatchers.IO)
    }
}