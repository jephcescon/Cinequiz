package com.example.cinequiz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest

class CadastroActivity : AppCompatActivity() {
    private val name by lazy { findViewById<TextInputEditText>(R.id.tvName) }
    private val email by lazy { findViewById<TextInputEditText>(R.id.tvEmail) }
    private val password by lazy { findViewById<TextInputEditText>(R.id.tvPassword) }
    private val confirmPassword by lazy { findViewById<TextInputEditText>(R.id.tvConfirmPassword) }
    private val button by lazy { findViewById<Button>(R.id.register) }
    private val tvName by lazy { findViewById<TextInputLayout>(R.id.emailLoginField) }
    private val tvEmail by lazy { findViewById<TextInputLayout>(R.id.emailField) }
    private val tvPassword by lazy { findViewById<TextInputLayout>(R.id.passwordField) }
    private val tvConfirmPassword by lazy { findViewById<TextInputLayout>(R.id.confirmPasswordField) }

    private lateinit var viewModel: CadastroViewModel
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        firebaseAuth = FirebaseAuth.getInstance()
        viewModel = ViewModelProviders.of(this).get(CadastroViewModel::class.java)

        fieldsValidate()
        button.setOnClickListener { verifiedRegister() }
    }

    fun fieldsValidate() {
        viewModel.emailLiveData.observe(this) {
            if (it)
                tvEmail.error = null
            else {
                tvEmail.error = "Preenchido incorretamente"
                tvEmail.errorIconDrawable = null
            }
            confirmFieldToCreateUser()
        }

        viewModel.nameValidate.observe(this) {
            if (it)
                tvName.error = null
            else {
                tvName.error = "Preenchido incorretamente"
                tvName.errorIconDrawable = null
            }
            confirmFieldToCreateUser()
        }

        viewModel.passwordValidate.observe(this) {
            if (it)
                tvPassword.error = null
            else {
                tvPassword.error = "Preenchido incorretamente"
                tvPassword.errorIconDrawable = null
            }
            confirmFieldToCreateUser()
        }

        viewModel.confirmPasswordValidate.observe(this) {
            if (it)
                tvConfirmPassword.error = null
            else {
                tvConfirmPassword.error = "Digite a senha igual a anterior"
                tvConfirmPassword.errorIconDrawable = null
            }
            confirmFieldToCreateUser()
        }
    }

    fun verifiedRegister() {
        val nameString = name.text.toString()
        val emailString = email.text.toString()
        val passwordString = password.text.toString()
        val confirmPasswordString = confirmPassword.text.toString()

        viewModel.validateFields(nameString, emailString, passwordString, confirmPasswordString)
    }

    fun confirmFieldToCreateUser() {
        if (viewModel.confirmPasswordValidate.value == true && viewModel.emailLiveData.value == true && viewModel.passwordValidate.value == true && viewModel.nameValidate.value == true)
            createUserWithEmailPass(
                email.text.toString(),
                password.text.toString(),
                name.text.toString()
            )
    }

    private fun createUserWithEmailPass(email: String, pass: String, name: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = firebaseAuth.currentUser
                val profileUpdate = userProfileChangeRequest {
                    displayName = name
                }
                user?.updateProfile(profileUpdate)
                //Colocar intent para navegar para a tela inicial
            } else {
                Log.d("error", task.exception?.message!!)
            }
        }
    }

}