package s.m.m.androidcatapp.model

import com.google.gson.annotations.SerializedName

data class CatBreed(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("reference_image_id") var breedReferenceId: String,
    @SerializedName("life_span") var lifeSpan: String,
    var isFavorite: Boolean = false
) {
    val breedReferenceImageUrl: String
        get() = "https://cdn2.thecatapi.com/images/${breedReferenceId}.jpg"
    val minLifeSpan: Int
        get() = lifeSpan.split("-").getOrNull(0)?.trim()?.toIntOrNull() ?: 0

    val maxLifeSpan: Int
        get() = lifeSpan.split("-").getOrNull(1)?.trim()?.toIntOrNull() ?: 0
}