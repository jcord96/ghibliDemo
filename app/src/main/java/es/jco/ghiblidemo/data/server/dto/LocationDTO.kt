package es.jco.ghiblidemo.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationDTO (
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("climate") var climate: String,
    @SerializedName("terrain") var terrain: String,
    @SerializedName("surface_water") var surfaceWater: String,
    @SerializedName("residents") var residents: List<String>,
    @SerializedName("films") var films: List<String>
) : Parcelable