package s.m.m.androidcatapp.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "catBreed")
data class CatBreed(
    @PrimaryKey @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("reference_image_id") var breedReferenceId: String?,
    @SerializedName("life_span") var lifeSpan: String?,
    @SerializedName("origin") var origin: String?,
    @SerializedName("temperament") var temperament: String?,
    @SerializedName("description") var description: String?,
    var isFavorite: Boolean = false
) {
    @get:Ignore
    val breedReferenceImageUrl: String
        get() = "https://cdn2.thecatapi.com/images/${breedReferenceId}.jpg"

    @get:Ignore
    val minLifeSpan: Int
        get() = lifeSpan?.split("-")?.getOrNull(0)?.trim()?.toIntOrNull() ?: 0

    @get:Ignore
    val maxLifeSpan: Int
        get() = lifeSpan?.split("-")?.getOrNull(1)?.trim()?.toIntOrNull() ?: 0
}