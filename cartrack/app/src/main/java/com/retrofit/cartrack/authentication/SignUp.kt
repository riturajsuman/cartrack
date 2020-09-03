package com.retrofit.cartrack.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.retrofit.cartrack.R
import com.retrofit.cartrack.databinding.SignupBinding
import com.retrofit.cartrack.db.UserDatabase
import com.retrofit.cartrack.db.UserRepository
import kotlinx.android.synthetic.main.signup.*


class SignUp : AppCompatActivity() {
    lateinit var binding: SignupBinding
    private lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.signup)

        val dao = UserDatabase.getInstance(application).userDAO
        val repository = UserRepository(dao)
        val factory =
            UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        binding.myViewModel = userViewModel
        binding.lifecycleOwner = this




    }

    private fun addItemsforContry() {
        val list: MutableList<String> = ArrayList()
        list.add("Singapore")
        list.add("India")
        list.add("Sri Lanka")
        list.add(("Bhutan"))
        val dataAdapter = ArrayAdapter(
            this, R.layout.simple_spinner_item, list
        )
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        displaycontry.adapter = dataAdapter
    }

    override fun onResume() {
        super.onResume()
        addItemsforContry()
        binding.singup.setOnClickListener {
            if(username.text.toString().isEmpty())
            {
                username.error = "Name not entered";
                username.requestFocus();
            }
            if(password.text.toString().isEmpty()){
                password.error="Password is not entered"
                username.requestFocus();
            }
            if(confirmPassword.text.toString().isEmpty()){
                confirmPassword.error=" Re enter Password is not entered"
                confirmPassword.requestFocus();
            }
            else{
                saveSingUp()
                userViewModel.saveOrUpdate()
                startActivity(Intent(this, Login::class.java))
                finish()
            }

        }
    }

    private fun saveSingUp() {
        val pref =
            applicationContext.getSharedPreferences("MyPref", 0)
        val editor = pref.edit()
        editor.putBoolean("key_name", true);
        editor.apply();
    }
}