package s.m.m.androidcatapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import s.m.m.androidcatapp.model.CatBreed
import s.m.m.androidcatapp.repository.CatRepository

class BreedViewModel : ViewModel() {
    private val repository = CatRepository()

    private val _breeds = MutableStateFlow<List<CatBreed>>(emptyList())
    val breeds: StateFlow<List<CatBreed>> = _breeds

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val filteredBreeds: StateFlow<List<CatBreed>> =
        combine(_breeds, _searchQuery) { breeds, query ->
            if (query.isEmpty()) {
                breeds
            } else {
                breeds.filter { it.name.contains(query, ignoreCase = true) }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    init {
        fetchBreeds()
    }

    private fun fetchBreeds() {
        viewModelScope.launch {
            try {
                _breeds.value = repository.getBreeds()
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}
