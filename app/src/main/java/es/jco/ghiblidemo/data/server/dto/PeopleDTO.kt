package es.jco.ghiblidemo.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PeopleDTO (
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("gender") var gender: String,
    @SerializedName("age") var age: String,
    @SerializedName("eye_color") var eyeColor: String,
    @SerializedName("hair_color") var hairColor: String,
    @SerializedName("films") var films: List<String>,
    @SerializedName("species") var species: String
) : Parcelable