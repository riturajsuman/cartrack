package com.retrofit.cartrack.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDAO {

    @Insert
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM user_table")
    fun getUser():LiveData<List<User>>
}