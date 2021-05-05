package com.example.cinequiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CadastroViewModel:ViewModel() {
    val emailLiveData by lazy { MutableLiveData<Boolean>() }
    val nameValidate by lazy { MutableLiveData<Boolean>() }
    val passwordValidate by lazy { MutableLiveData<Boolean>() }
    val confirmPasswordValidate by lazy { MutableLiveData<Boolean>() }

    fun validateFields(name: String,email:String, password: String,confirmPassword: String){
        when{
            name.isBlank() && password.isBlank() && validateEmail(email)->{
                emailLiveData.postValue(false)
                nameValidate.postValue(false)
                passwordValidate.postValue(false)
            }
            name.isBlank() && password.isBlank()->{
                nameValidate.postValue(false)
                emailLiveData.postValue(true)
                passwordValidate.postValue(false)
            }
            name.isBlank() && validateEmail(email)->{
                nameValidate.postValue(false)
                emailLiveData.postValue(false)
                passwordValidate.postValue(true)
            }
            validateEmail(email) && password.isBlank()->{
                nameValidate.postValue(true)
                emailLiveData.postValue(false)
                passwordValidate.postValue(false)
            }
            name.isBlank()->{
                nameValidate.postValue(false)
                emailLiveData.postValue(true)
                passwordValidate.postValue(true)
            }
            password.isBlank()->{
                nameValidate.postValue(true)
                emailLiveData.postValue(true)
                passwordValidate.postValue(false)
            }
            validateEmail(email)->{
                nameValidate.postValue(true)
                emailLiveData.postValue(false)
                passwordValidate.postValue(true)
            }
            else->{
                nameValidate.postValue(true)
                emailLiveData.postValue(true)
                passwordValidate.postValue(true)
            }
        }
        if (password == confirmPassword)
            confirmPasswordValidate.postValue(true)
        else
            confirmPasswordValidate.postValue(false)
    }
}

fun validateEmail(email: String):Boolean{
    return email.isBlank() || !email.contains('@') || email.length < 8
}