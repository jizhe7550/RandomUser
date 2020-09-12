package com.jizhe7550.randomuser.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
@Entity(tableName = Dob.TABLE)
@JsonClass(generateAdapter = true)
data class Dob(

    @ColumnInfo(name = "age")
    @Json(name = "age")
    val age: Int?,

    @PrimaryKey
    @ColumnInfo(name = "date")
    @Json(name = "date")
    val date: String
) : Parcelable {

    fun asString(): String {
        val apiIsoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val humanFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)

        return try {
            apiIsoFormat.parse(date)?.let { humanFormat.format(it) } ?: ""
        } catch (e: ParseException) {
            Timber.e("Dob.asString() Error parsing $date")
            ""
        }
    }

    companion object {
        const val TABLE = "DobEntity"
    }

}