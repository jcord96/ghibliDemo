package es.jco.ghiblidemo.data.server.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vehicle (
    @SerializedName("id") var id: String,
) : Parcelable