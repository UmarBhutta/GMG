package com.gmg.user.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Dob (

	@SerializedName("date") val date : String,
	@SerializedName("age") val age : Int
):Parcelable