package s.m.m.androidcatapp.repository

import s.m.m.androidcatapp.model.CatBreed

interface DataSourceInterface {
    suspend fun fetch(): List<CatBreed>?
}
