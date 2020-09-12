package com.jizhe7550.randomuser.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jizhe7550.randomuser.model.User

/**
 * Room data access object for accessing the [User] table.
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<User>)

    // TODO: Change email by name
    @Query("SELECT * FROM ${User.TABLE} WHERE (email LIKE :queryString)")
    fun usersByName(queryString: String): DataSource.Factory<Int, User>

    @Query("SELECT * FROM ${User.TABLE}")
    fun allUsers(): DataSource.Factory<Int, User>
}
