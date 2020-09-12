package com.jizhe7550.randomuser.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Picture.TABLE, primaryKeys = ["large", "thumbnail"])
@JsonClass(generateAdapter = true)
data class Picture(

    @ColumnInfo(name = "large")
    @Json(name = "large")
    val large: String,

    @ColumnInfo(name = "medium")
    @Json(name = "medium")
    val medium: String?,

    @ColumnInfo(name = "thumbnail")
    @Json(name = "thumbnail")
    val thumbnail: String

) : Parcelable {
    companion object {
        const val TABLE = "PictureEntity"
    }
}