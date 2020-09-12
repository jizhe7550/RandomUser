package com.jizhe7550.randomuser.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Timezone.TABLE)
@JsonClass(generateAdapter = true)
data class Timezone(

    @ColumnInfo(name = "description")
    @Json(name = "description")
    val description: String?,

    @PrimaryKey
    @ColumnInfo(name = "offset")
    @Json(name = "offset")
    val offset: String

) : Parcelable {
    companion object {
        const val TABLE = "TimezoneEntity"
    }
}