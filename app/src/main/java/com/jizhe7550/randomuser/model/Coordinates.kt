package com.jizhe7550.randomuser.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Coordinates.TABLE, primaryKeys = ["latitude", "longitude"])
@JsonClass(generateAdapter = true)
data class Coordinates(

    @ColumnInfo(name = "latitude")
    @Json(name = "latitude")
    val latitude: String,

    @ColumnInfo(name = "longitude")
    @Json(name = "longitude")
    val longitude: String
) : Parcelable {

    companion object {
        const val TABLE = "CoordinatesEntity"
    }

}