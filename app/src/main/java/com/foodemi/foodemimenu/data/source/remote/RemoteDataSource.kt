package com.foodemi.foodemimenu.data.source.remote

import com.foodemi.foodemimenu.data.model.api.ApiResponse
import com.foodemi.foodemimenu.data.model.response.ModelMenuSectioned
import retrofit2.http.Body

interface RemoteDataSource {

    suspend fun getSectionedMenu()                      : ApiResponse<ModelMenuSectioned>

}