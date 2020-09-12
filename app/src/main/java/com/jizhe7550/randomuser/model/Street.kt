package com.jizhe7550.randomuser.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Street.TABLE, primaryKeys = ["name", "number"])
@JsonClass(generateAdapter = true)
data class Street(

    @ColumnInfo(name = "name")
    @Json(name = "name")
    val name: String,

    @ColumnInfo(name = "number")
    @Json(name = "number")
    val number: Int

) : Parcelable {

    companion object {
        const val TABLE = "StreetEntity"
    }

}