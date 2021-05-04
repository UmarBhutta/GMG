package com.gmg.user.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location (

	@SerializedName("street") val street : Street,
	@SerializedName("city") val city : String,
	@SerializedName("state") val state : String,
	@SerializedName("country") val country : String,
	@SerializedName("postcode") val postcode : Int,
	@SerializedName("coordinates") val coordinates : Coordinates,
	@SerializedName("timezone") val timezone : Timezone
):Parcelable