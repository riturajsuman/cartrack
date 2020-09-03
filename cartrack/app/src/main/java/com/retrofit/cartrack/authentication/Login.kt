package com.retrofit.cartrack.authentication

import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.retrofit.cartrack.landingUI.ui.MainActivity
import com.retrofit.cartrack.R
import com.retrofit.cartrack.databinding.LoginBinding
import com.retrofit.cartrack.db.UserDatabase
import com.retrofit.cartrack.db.UserRepository
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.signup.*


class Login : AppCompatActivity() {
    lateinit var binding: LoginBinding
    private lateinit var userViewModel: UserViewModel
    private var name: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.login)
        val dao = UserDatabase.getInstance(application).userDAO
        val repository = UserRepository(dao)
        val factory =
            UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        binding.myViewModel = userViewModel
        binding.lifecycleOwner = this


        userViewModel.subscribers.observe(this, Observer {
            Log.i("MYTAG", it.toString())
            name = it[0].name
            password = it[0].password
        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onResume() {
        super.onResume()
        login.setOnClickListener { it ->

            if (name_login.text.toString()
                    .trim() == name && password_detail.text.toString().trim() == password
            ) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()

            } else {

                if (name_login.text.toString().isEmpty()) {
                    name_login.error = "Name not entered";
                    name_login.requestFocus();
                }
                if (password_detail.text.toString().isEmpty()) {
                    password_detail.error = "Password is not entered"
                    password_detail.requestFocus();
                } else if (
                    name_login.text.toString()
                        .trim().isNotEmpty() && password_detail.text.toString().trim().isNotEmpty()
                ) {
                    val dialog = Dialog(this); // Context, this, etc.
                    dialog.setContentView(R.layout.dialog);
                    dialog.show();
                }


            }
        }
    }
}
