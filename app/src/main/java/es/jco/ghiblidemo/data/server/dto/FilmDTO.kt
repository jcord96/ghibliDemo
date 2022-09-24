package es.jco.ghiblidemo.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmDTO (
    @SerializedName("id") var id: String,
    @SerializedName("title") var title: String,
    @SerializedName("original_title") var originalTitle: String,
    @SerializedName("original_title_romanised") var originalTitleRomanised: String,
    @SerializedName("image") var image: String,
    @SerializedName("movie_banner") var movieBanner: String,
    @SerializedName("description") var description: String,
    @SerializedName("director") var director: String,
    @SerializedName("producer") var producer: String,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("running_time") var runningTime: String,
    @SerializedName("rt_score") var rtScore: String,
    @SerializedName("people") var people: List<String>,
    @SerializedName("species") var species: List<String>,
    @SerializedName("locations") var locations: List<String>,
    @SerializedName("vehicles") var vehicles: List<String>
) : Parcelable