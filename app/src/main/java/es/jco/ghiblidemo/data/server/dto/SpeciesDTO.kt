package es.jco.ghiblidemo.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpeciesDTO (
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("classification") var classification: String,
    @SerializedName("eye_colors") var eyeColors: String,
    @SerializedName("hair_colors") var hairColors: String,
    @SerializedName("people") var people: List<String>,
    @SerializedName("films") var films: List<String>
) : Parcelable