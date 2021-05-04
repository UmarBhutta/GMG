package com.gmg.user.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Info (

	@SerializedName("seed") val seed : String,
	@SerializedName("results") val results : Int,
	@SerializedName("page") val page : Int,
	@SerializedName("version") val version : Double
):Parcelable