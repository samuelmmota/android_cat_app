package s.m.m.androidcatapp.repository

import android.media.Image

import s.m.m.androidcatapp.model.CatBreed
import s.m.m.androidcatapp.retrofit.RetrofitClient

class CatRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun getBreeds(): List<CatBreed> {
        return apiService.getBreeds()
    }

    suspend fun getImagesByBreedId(breedId: String): Image {
        return apiService.getImageByBreedId(breedId)
    }
}
