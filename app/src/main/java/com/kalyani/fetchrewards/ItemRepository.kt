package com.kalyani.fetchrewards

import com.kalyani.fetchrewards.network.ApiService
import com.kalyani.fetchrewards.network.model.Item
import javax.inject.Inject

class ItemRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun fetchItems(): List<Item> {
        return try {
            apiService.getItems()
        } catch (e: Exception) {
            println(e)
            emptyList()
        }
    }
}