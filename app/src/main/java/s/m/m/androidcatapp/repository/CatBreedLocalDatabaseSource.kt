package s.m.m.androidcatapp.repository

import s.m.m.androidcatapp.database.CatBreedDao
import s.m.m.androidcatapp.model.CatBreed

class CatBreedLocalDatabaseSource(private val catBreedDao: CatBreedDao) : DataSourceInterface {
    override suspend fun fetch(): List<CatBreed> {
        return catBreedDao.getAllCatBreeds()
    }

    suspend fun insertCatBreeds(vararg catBreed: CatBreed) {
        catBreedDao.insertCatBreed(*catBreed)
    }

    suspend fun getFavoriteCatBreedIds(): List<String> {
        return catBreedDao.getFavoriteCatBreedIds()
    }

    suspend fun updateCatBreed(catBreedId: String) {
        catBreedDao.updateCatBreedFavorite(catBreedId)
    }
}