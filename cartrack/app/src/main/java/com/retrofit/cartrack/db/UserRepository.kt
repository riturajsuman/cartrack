package com.retrofit.cartrack.db

class UserRepository(private val dao : UserDAO) {

    val subscribers = dao.getUser()

    suspend fun insert(user: User){
        dao.insertUser(user)
    }
}