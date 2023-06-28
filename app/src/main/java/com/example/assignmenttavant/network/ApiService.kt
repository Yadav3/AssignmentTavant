package com.example.assignmenttavant.network

import com.example.assignmenttavant.model.ProductModel
import com.example.assignmenttavant.model.RoomsModel
import com.example.assignmenttavant.network.NetworkingConstants
import retrofit2.http.GET

interface ApiService {

    @GET(NetworkingConstants.URL_PRODUCT)
    suspend fun getProduct(): ProductModel


    @GET(NetworkingConstants.URL_ROOMS)
    suspend fun getRoom(): RoomsModel
}