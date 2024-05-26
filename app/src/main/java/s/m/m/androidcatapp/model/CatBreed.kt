package s.m.m.androidcatapp.model

import com.google.gson.annotations.SerializedName

data class CatBreed(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("reference_image_id") var breedReferenceId: String,
) {
    val breedReferenceImageUrl: String
        get() = "https://cdn2.thecatapi.com/images/${breedReferenceId}.jpg"
}