package com.retrofit.cartrack.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id : Int,
    @ColumnInfo(name = "user_name")
    val name : String,
    @ColumnInfo(name = "user_password")
    val password : String

)