package com.kalyani.fetchrewards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalyani.fetchrewards.network.model.Item
import com.kalyani.fetchrewards.network.model.ItemGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val repository: ItemRepository
) : ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items

    init {
        viewModelScope.launch {
            val items = repository.fetchItems()
            val filteredItems = items.asSequence().filter { it.name != null && it.name.isNotBlank() }
                .groupBy { it.listId }
                .map { (listId, items) -> ItemGroup(listId, items.sortedBy { it.name }) }
                .sortedBy { it.listId }
                .flatMap { it.items }
                .toList()

            _items.value = filteredItems
        }
    }
}