package com.example.cinequiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel:ViewModel() {
    val emailValidate: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val passwordValidate: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun validateFields(email: String, password: String){
        when{
            validateEmail(email) && password.isBlank()->{
                emailValidate.postValue(false)
                passwordValidate.postValue(false)
            }
            validateEmail(email)->{
                emailValidate.postValue(false)
                passwordValidate.postValue(true)
            }
            password.isBlank()->{
                emailValidate.postValue(true)
                passwordValidate.postValue(false)
            }
            else->{
                emailValidate.postValue(true)
                passwordValidate.postValue(true)
            }
        }
    }
}