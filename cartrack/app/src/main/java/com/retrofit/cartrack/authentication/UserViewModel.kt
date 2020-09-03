package com.retrofit.cartrack.authentication

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.retrofit.cartrack.db.User
import com.retrofit.cartrack.db.UserRepository
import kotlinx.coroutines.launch


class UserViewModel(private val repository: UserRepository) : ViewModel(),Observable {

    val subscribers = repository.subscribers

    @Bindable
    val inputName = MutableLiveData<String>()
    @Bindable
    val inputPassword = MutableLiveData<String>()

    fun saveOrUpdate(){
        val name = inputName.value!!
        val email = inputPassword.value!!
        insert(User(0,name,email))
        inputName.value = null
        inputPassword.value = null
    }


    private fun insert(user: User)= viewModelScope.launch {
            repository.insert(user)
        }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}