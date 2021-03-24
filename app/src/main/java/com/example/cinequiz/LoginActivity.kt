package com.example.cinequiz

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    lateinit var tvNovoCadastro: TextView
    lateinit var tvEsqueciASenha: TextView
    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title

        supportActionBar?.hide(); // hide the title bar
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            Toast.makeText(this, "Logando...", Toast.LENGTH_LONG).show();
        }

        tvNovoCadastro = findViewById(R.id.tvNovo)

        tvNovoCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        tvEsqueciASenha = findViewById(R.id.tvEsqueciSenha)

        tvEsqueciASenha.setOnClickListener {
            val intent = Intent(this, EsqueciASenhaActivity::class.java)
            startActivity(intent)
        }

    }
}