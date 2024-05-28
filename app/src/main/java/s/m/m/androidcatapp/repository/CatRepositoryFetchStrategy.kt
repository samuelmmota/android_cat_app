package s.m.m.androidcatapp.repository

import s.m.m.androidcatapp.model.CatBreed

enum class CatRepositoryDataSourceEnum {
    DATABASE,
    REMOTE
}

/**
 * Dictates which sources will be used by the repository's fetch logic.
 */
enum class CatRepositoryFetchStrategy(private val sourceList: List<CatRepositoryDataSourceEnum>) {
    /**
     * Fetches from the regular order, using the cache as a performance boost and the DB as an offline alternative
     */
    STANDARD(
        listOf(
            CatRepositoryDataSourceEnum.REMOTE,
            CatRepositoryDataSourceEnum.DATABASE
        )
    );

    suspend fun fetch(
        databaseDataSource: CatBreedLocalDatabaseSource,
        remoteDataSource: CatBreedApiDataSource
    ): List<CatBreed> {
        val dataSourceHolder = DataSourceHolder(databaseDataSource, remoteDataSource)
        var dataSourceCatBreedListResult: List<CatBreed>? = null

        for (source in this.sourceList) {
            val dataSource = dataSourceHolder.getDataSourceFromEnum(source)
            dataSourceCatBreedListResult = dataSource.fetch()!!
            updateOtherSourcesIfRequired(source, dataSourceCatBreedListResult, dataSourceHolder)
        }
        return dataSourceCatBreedListResult ?: emptyList()
    }

    private suspend fun updateOtherSourcesIfRequired(
        source: CatRepositoryDataSourceEnum,
        dataSourceListResult: List<CatBreed>?,
        dataSourceHolder: DataSourceHolder,
    ) {
        if (dataSourceListResult == null) {
            return
        }
        when (source) {
            CatRepositoryDataSourceEnum.REMOTE -> {
                val catBreedArray = dataSourceListResult.toTypedArray()
                dataSourceHolder.database.insertCatBreeds(*catBreedArray)
            }

            CatRepositoryDataSourceEnum.DATABASE -> {}
        }
    }

    private class DataSourceHolder(
        val database: CatBreedLocalDatabaseSource,
        val remote: CatBreedApiDataSource
    ) {
        fun getDataSourceFromEnum(sourceEnum: CatRepositoryDataSourceEnum) =
            when (sourceEnum) {
                CatRepositoryDataSourceEnum.REMOTE -> remote
                CatRepositoryDataSourceEnum.DATABASE -> database
            }
    }
}
