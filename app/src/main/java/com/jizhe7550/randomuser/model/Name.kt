package com.jizhe7550.randomuser.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Name.TABLE, primaryKeys = ["first", "last"])
@JsonClass(generateAdapter = true)
data class Name(

    @ColumnInfo(name = "first")
    @Json(name = "first")
    var first: String,

    @ColumnInfo(name = "last")
    @Json(name = "last")
    var last: String,

    @ColumnInfo(name = "title")
    @Json(name = "title")
    var title: String?
) : Parcelable {

    fun fullName(): String {
        val builder = StringBuilder()

        if (title?.isNotEmpty() == true) builder.append(title)
        if (builder.toString().isNotEmpty() && first.isNotEmpty()) builder.append(" ")
        if (first.isNotEmpty()) builder.append(first)
        if (builder.toString().isNotEmpty() && last.isNotEmpty()) builder.append(" ")
        if (last.isNotEmpty()) builder.append(last)

        return builder.toString()
    }


    companion object {
        const val TABLE = "NameEntity"
    }

}
