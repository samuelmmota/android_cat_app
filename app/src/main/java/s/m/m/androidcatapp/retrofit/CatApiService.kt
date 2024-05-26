package s.m.m.androidcatapp.retrofit

import android.media.Image
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import s.m.m.androidcatapp.model.CatBreed

interface CatApiService {
    @GET("breeds")
    suspend fun getBreeds(): List<CatBreed>

    @GET("images/")
    suspend fun getImageByBreedId(@Query("breed_ids") breedId: String): Image
}

object RetrofitClient {
    private const val BASE_URL = "https://api.thecatapi.com/v1/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val apiService: CatApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatApiService::class.java)
    }
}
