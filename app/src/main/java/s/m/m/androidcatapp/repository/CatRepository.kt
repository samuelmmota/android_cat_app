package s.m.m.androidcatapp.repository

import s.m.m.androidcatapp.database.ApiDatabase
import s.m.m.androidcatapp.model.CatBreed
import s.m.m.androidcatapp.retrofit.CatBreedApi

class CatRepository() {
    private val database = ApiDatabase.getDatabase()
    private val localDatabaseSource = CatBreedLocalDatabaseSource(database.catBreedDao())
    private val catBreedApiDataSource = CatBreedApiDataSource(CatBreedApi)
    suspend fun getBreeds(repositoryFetchStrategy: CatRepositoryFetchStrategy = CatRepositoryFetchStrategy.STANDARD): List<CatBreed> {
        return repositoryFetchStrategy.fetch(localDatabaseSource, catBreedApiDataSource)
    }
}
