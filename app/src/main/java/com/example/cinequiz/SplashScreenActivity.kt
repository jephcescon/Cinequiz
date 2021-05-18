package com.example.cinequiz

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SplashScreenActivity : AppCompatActivity() {
    val firebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val handle = Handler()
        handle.postDelayed(Runnable { mostrarLogin() }, 2000)
    }

    private fun mostrarLogin() {
        if (firebaseAuth.currentUser != null){
            //Mudar após a apresentação
//            val intent = Intent(this,MainActivity::class.java)
//            startActivity(intent)
//            finish()
            val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
            startActivity(intent)
            firebaseAuth.signOut()
            finish()
        }else {
            val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}