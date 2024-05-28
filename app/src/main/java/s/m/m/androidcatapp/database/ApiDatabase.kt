package s.m.m.androidcatapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import s.m.m.androidcatapp.model.CatBreed

@androidx.room.Database(
    entities = [CatBreed::class],
    version = 1,
    exportSchema = true
)

abstract class ApiDatabase : RoomDatabase() {
    abstract fun catBreedDao(): CatBreedDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: ApiDatabase

        fun init(context: Context) {
            synchronized(this) {
                val builder = Room
                    .databaseBuilder(
                        context.applicationContext,
                        ApiDatabase::class.java,
                        "api_database"
                    )

                migrations.forEach { migration -> builder.addMigrations(migration) }
                INSTANCE = builder.build()
            }
        }

        fun getDatabase(): ApiDatabase {
            return INSTANCE
        }
    }
}
