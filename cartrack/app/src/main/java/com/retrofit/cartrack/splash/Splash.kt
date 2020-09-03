package com.retrofit.cartrack.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.retrofit.cartrack.R
import com.retrofit.cartrack.databinding.SplashScreenBinding
import com.retrofit.cartrack.authentication.Login
import com.retrofit.cartrack.authentication.SignUp


class Splash : AppCompatActivity() {
    lateinit var binding : SplashScreenBinding
    lateinit var viewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.splash_screen)
        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)

        viewModel.liveData.observe(this, Observer {
            when (it) {
                is SplashState.MainActivity -> {
                    goToMainActivity()
                }
            }
        })

    }

    private fun goToMainActivity() {
        finish()
        val prefs = getSharedPreferences("MyPref",
            Context.MODE_PRIVATE);
        val isSingUp = prefs.getBoolean("key_name", false)

        if(isSingUp)
        {
            startActivity(Intent(this, Login::class.java))
            overridePendingTransition(R.animator.lefttoright, R.animator.righttoleft)

        }
        else{
            startActivity(Intent(this, SignUp::class.java))
            overridePendingTransition(R.animator.righttoleft, R.animator.lefttoright)
        }

    }

}