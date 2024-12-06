package com.kalyani.fetchrewards.network

import com.kalyani.fetchrewards.network.model.Item
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    suspend fun getItems(): List<Item>
}