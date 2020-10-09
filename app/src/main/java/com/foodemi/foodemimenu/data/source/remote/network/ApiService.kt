package com.foodemi.foodemimenu.data.source.remote.network

import com.foodemi.foodemimenu.data.model.response.ModelMenu
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    companion object{
        fun getApiService(): Class<ApiService> {
            return ApiService::class.java
        }
    }

    @GET("admin/api/widget/v1/foodemi/list/menu")
    suspend fun getAllMenu(): Response<ModelMenu>

}