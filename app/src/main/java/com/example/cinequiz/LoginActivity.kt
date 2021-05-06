package com.example.cinequiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.Login
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {
    private val tvEmail by lazy { findViewById<TextInputLayout>(R.id.emailLoginField) }
    private val tvPassword by lazy { findViewById<TextInputLayout>(R.id.passField) }
    private val fieldEmail by lazy { findViewById<TextInputEditText>(R.id.tvLoginEmail) }
    private val fieldPassword by lazy { findViewById<TextInputEditText>(R.id.tvLoginPass) }

    private lateinit var viewModel: LoginViewModel
    lateinit var firebaseAuth: FirebaseAuth

    lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var callbackManager: CallbackManager

    private var loginManager = LoginManager.getInstance()

    private val btnFacebook by lazy { findViewById<Button>(R.id.btnFacebook) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        firebaseAuth = FirebaseAuth.getInstance()
        callbackManager = CallbackManager.Factory.create()
        fieldsValidate()

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestProfile()
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions)

        btnFacebook.setOnClickListener {
            signFacebook()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 200) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("GoogleSign", "firebaseAuthWithGoogle:" + account.idToken)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("GoogleSign", "Google sign in failed", e)
            }catch (e: Exception) {
                Log.d("error","error ao efeutuar login")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("GoogleSign", "signInWithCredential:failure", task.exception)
                }
            }
    }

    fun sigIn(view:View){
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent,200)
    }

    private fun signFacebook(){
        loginManager.logInWithReadPermissions(this, arrayListOf("email","public_profile"))
        loginManager.registerCallback(callbackManager, object: FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult?) {
                result?.let { firebaseAuthWithFacebook(it.accessToken) }

            }

            override fun onCancel() {
                Log.d("Facebook", "deu erro no login")

            }

            override fun onError(error: FacebookException?) {
                Log.d("Facebook", "deu erro no login")
            }

        })
    }

    private fun firebaseAuthWithFacebook(idToken: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(idToken.token)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("GoogleSign", "signInWithCredential:failure", task.exception)
                }
            }
    }

    fun fieldsValidate() {
        viewModel.emailValidate.observe(this) {
            if (it)
                tvEmail.error = null
            else {
                tvEmail.error = "Preenchido incorretamente"
                tvEmail.errorIconDrawable = null
            }
            navigation()
        }

        viewModel.passwordValidate.observe(this) {
            if (it)
                tvPassword.error = null
            else {
                tvPassword.error = "Preenchido incorretamente"
                tvPassword.errorIconDrawable = null
            }
            navigation()
        }
    }

    private fun navigation(){
        if (viewModel.passwordValidate.value == true && viewModel.emailValidate.value == true){
            sigInFirebase(fieldEmail.text.toString(),fieldPassword.text.toString())
        }
    }

    private fun sigInFirebase(email: String, pass: String) {
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {task->
            if (task.isSuccessful){
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Log.d("error", task.exception?.message!!)
            }
        }
    }
    fun login(view: View){
        val email = fieldEmail.text.toString()
        val password = fieldPassword.text.toString()

        viewModel.validateFields(email,password)
    }

    fun newUser(view: View){
        val intent = Intent(this, CadastroActivity::class.java)
        startActivity(intent)
    }

    fun forgetPass(view: View){
        val intent = Intent(this, EsqueciASenhaActivity::class.java)
        startActivity(intent)
    }
}