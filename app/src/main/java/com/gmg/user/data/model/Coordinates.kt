package com.gmg.user.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coordinates (

	@SerializedName("latitude") val latitude : Double,
	@SerializedName("longitude") val longitude : Double
):Parcelable