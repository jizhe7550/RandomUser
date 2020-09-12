package com.jizhe7550.randomuser.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Registered.TABLE)
@JsonClass(generateAdapter = true)
data class Registered(

    @Json(name = "age")
    @ColumnInfo(name = "registered_age")
    val age: Int?,

    @PrimaryKey
    @Json(name = "date")
    @ColumnInfo(name = "registered_date")
    val date: String

) : Parcelable {
    companion object {
        const val TABLE = "RegisteredEntity"
    }
}