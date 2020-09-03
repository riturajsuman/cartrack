package com.retrofit.cartrack.landingUI.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.retrofit.cartrack.landingUI.model.User
import com.retrofit.cartrack.network.*
import com.retrofit.cartrack.R
import com.retrofit.cartrack.landingUI.adapter.CustomAdapter
import com.retrofit.cartrack.landingUI.model.UserItem
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    private lateinit var userlist_data: ArrayList<UserItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retService = RetrofitInstance.getRetrofitInstance()
            .create(UserService::class.java)
        val responseLiveData: LiveData<Response<User>> = liveData {
            val response = retService.getUser()
            emit(response)
        }

        responseLiveData.observe(this, Observer {
            val userList = it.body()?.listIterator()

            if(userList!=null) {
                while (userList.hasNext()) {
                    val albumsItem = userList.next()
                    Log.i("====", albumsItem.toString())
                         userlist_data = ArrayList<UserItem>()
                        for(i in userList )
                        {
                           userlist_data.add(i)
                        }

                }
            }

            val recyclerView = findViewById<RecyclerView>(R.id.list_of_user)
            recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

            val adapter = CustomAdapter(userlist_data)
            recyclerView.adapter = adapter



        })
    }
}

