package es.jco.ghiblidemo.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VehicleDTO (
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String,
    @SerializedName("vehicle_class") var vehicleClass: String,
    @SerializedName("length") var length: String,
    @SerializedName("pilot") var pilot: String,
    @SerializedName("films") var films: List<String>,
) : Parcelable