package com.jizhe7550.randomuser.db

import androidx.paging.DataSource
import com.jizhe7550.randomuser.model.User
import timber.log.Timber
import java.util.concurrent.Executor

/**
 * Class that handles the DAO local data source. This ensures that methods are triggered on the
 * correct executor.
 */
class RandomUserLocalCache(
    private val database: UserDatabase,
    private val ioExecutor: Executor
) {

    /**
     * Insert a list of user in the database, on a background thread.
     */
    fun insert(users: List<User>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            Timber.d("RandomUserLocalCache inserting ${users.size} users")
            database.userDao().insert(users)
            insertFinished()
        }
    }

    /**
     * Request a LiveData<List<User>> from the Dao, based on a user name. If the name contains
     * multiple words separated by spaces, then we're emulating the GitHub API behavior and allow
     * any characters between the words.
     * @param name user name
     */
    fun usersByName(name: String): DataSource.Factory<Int, User> {
        // appending '%' so we can allow other characters to be before and after the query string
        val query = "%${name.replace(' ', '%')}%"
        return database.userDao().usersByName(query)
    }

    /**
     * Request a LiveData<List<User>> from the Dao, based on a user name. If the name contains
     * multiple words separated by spaces, then we're emulating the GitHub API behavior and allow
     * any characters between the words.
     * @param name user name
     */
    fun allUsers(): DataSource.Factory<Int, User> {
        return database.userDao().allUsers()
    }
}
