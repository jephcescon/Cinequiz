package com.example.cinequiz

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class EsqueciASenhaActivity : AppCompatActivity() {
    lateinit var firebaseAuth : FirebaseAuth
    private val tvEmail by lazy { findViewById<TextInputEditText>(R.id.tvEmail) }
    private val button by lazy { findViewById<Button>(R.id.buttonResetPass) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esqueci_a_senha)

        firebaseAuth = FirebaseAuth.getInstance()
        button.setOnClickListener { resetPass() }
    }

    private fun resetPassword(email:String) {
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener { task->
            if (task.isSuccessful){
                Toast.makeText(this,"Email enviado para recuperação de senha",Toast.LENGTH_LONG).show()
                onBackPressed()
            }else{
                tvEmail.error = "E-mail invalido ou não cadastrado"
            }
        }
    }

    private fun resetPass(){
        if (tvEmail.text.isNullOrBlank()){
            tvEmail.error = "Digite um e-mail"
        }else{
            resetPassword(tvEmail.text.toString())
        }
    }
}