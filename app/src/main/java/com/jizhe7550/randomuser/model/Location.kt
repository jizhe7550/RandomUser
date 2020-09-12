package com.jizhe7550.randomuser.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jizhe7550.randomuser.model.Coordinates
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Location.TABLE, primaryKeys = ["postcode", "city", "country"])
@JsonClass(generateAdapter = true)
data class Location(

    @ColumnInfo(name = "city")
    @Json(name = "city")
    val city: String,

    @Embedded
    @Json(name = "coordinates")
    val coordinates: Coordinates?,

    @ColumnInfo(name = "country")
    @Json(name = "country")
    val country: String,

    @ColumnInfo(name = "postcode")
    @Json(name = "postcode")
    val postcode: String,

    @ColumnInfo(name = "state")
    @Json(name = "state")
    val state: String,

    @Embedded
    @Json(name = "street")
    val street: Street,

    @Embedded
    @Json(name = "timezone")
    val timezone: Timezone?
) : Parcelable {

    fun fullAddress(): String {
        val builder = StringBuilder()

        if (street.name.isNotEmpty()) builder.append("${street.number} ${street.name}")
        if (builder.toString().isNotEmpty() && city.isNotEmpty()) builder.append(", ")
        if (city.isNotEmpty()) builder.append(city)
        if (builder.toString().isNotEmpty() && country.isNotEmpty()) builder.append(", ")
        if (country.isNotEmpty()) builder.append(country)

        return builder.toString()
    }

    companion object {
        const val TABLE = "LocationEntity"
    }

}