package s.m.m.androidcatapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import s.m.m.androidcatapp.model.CatBreed
import s.m.m.androidcatapp.repository.CatRepository

class BreedViewModel : ViewModel() {
    private val repository = CatRepository()
    private val _breeds = MutableStateFlow<List<CatBreed>>(emptyList())
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
    val favoriteBreeds: StateFlow<List<CatBreed>> = _breeds
        .map { breeds -> breeds.filter { it.isFavorite } }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val averageFavoriteBreedsLifeSpan: StateFlow<Double> = favoriteBreeds
        .map { breeds ->
            if (breeds.isNotEmpty()) {
                breeds.map { (it.minLifeSpan + it.maxLifeSpan) / 2.0 }.average()
            } else {
                0.0
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, 0.0)

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

    fun toggleFavorite(breedId: String) {
        _breeds.value = _breeds.value.map { breed ->
            if (breed.id == breedId) {
                val updatedBreed = breed.copy(isFavorite = !breed.isFavorite)
                viewModelScope.launch {
                    repository.updateFavoriteBreedById(updatedBreed.id)
                }
                updatedBreed
            } else {
                breed
            }
        }
    }

    fun getCatBreed(breedId: String): CatBreed? {
        return _breeds.value.find { it.id == breedId }
    }
}
