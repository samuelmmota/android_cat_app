package s.m.m.androidcatapp.repository

import android.util.Log
import s.m.m.androidcatapp.model.CatBreed
import s.m.m.androidcatapp.retrofit.CatBreedApi

class CatBreedApiDataSource(private val catBreedApi: CatBreedApi) : DataSourceInterface {
    private val TAG = "CatBreedApiDataSource"

    override suspend fun fetch(): List<CatBreed> {
        return try {
            catBreedApi.getBreeds()!!
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected error. Refresh unsuccessful.", e)
            emptyList<CatBreed>()
        }
    }
}
