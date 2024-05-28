package s.m.m.androidcatapp.database

import s.m.m.androidcatapp.model.CatBreed
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@androidx.room.Dao
interface CatBreedDao {
    //OnConflictStrategy.REPLACE because the CatBreed could be updated from API
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatBreed(vararg catBreed: CatBreed)

    @Query("SELECT catBreed.* FROM catBreed ORDER BY name ASC")
    suspend fun getAllCatBreeds(): List<CatBreed>

    @Query("SELECT id FROM catBreed WHERE isFavorite = 1")
    suspend fun getFavoriteCatBreedIds(): List<String>

    @Query("UPDATE catBreed SET isFavorite = NOT isFavorite WHERE id = :catBreedId")
    suspend fun updateCatBreedFavorite(catBreedId: String)
}
