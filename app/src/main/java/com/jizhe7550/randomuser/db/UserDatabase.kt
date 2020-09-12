package com.jizhe7550.randomuser.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jizhe7550.randomuser.model.*

/**
 * Database schema that holds the list of users.
 */
@Database(
    entities = [User::class, Name::class, Dob::class, Id::class, Location::class, Coordinates::class,
        Street::class, Timezone::class, Picture::class, Registered::class, Login::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java, "UserDatabase.db"
            )
                .build()
    }
}