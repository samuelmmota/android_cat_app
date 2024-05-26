package s.m.m.androidcatapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import s.m.m.androidcatapp.model.CatBreed
import s.m.m.androidcatapp.repository.CatRepository

class BreedViewModel : ViewModel() {
    private val repository = CatRepository()

    private val _breeds = MutableStateFlow<List<CatBreed>>(emptyList())
    val breeds: StateFlow<List<CatBreed>> = _breeds

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
}
