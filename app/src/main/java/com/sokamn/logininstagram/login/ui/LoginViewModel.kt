package com.sokamn.logininstagram.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokamn.logininstagram.login.domain.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {

    val loginUseCase = LoginUseCase()

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _passVisibility = MutableLiveData<Boolean>()
    val passVisibility: LiveData<Boolean> = _passVisibility



    fun onLoginChanged(email: String, password: String){
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email, password)
    }

    private fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 7

    fun onChangePassVisibility(passVisibility: Boolean) {
        _passVisibility.value = !passVisibility
    }

    fun onLoginSelected(){
        viewModelScope.launch {
            _isLoading.value = true
            val result = loginUseCase(email.value!!, password.value!!)
            if(result){
                // navegar a la siguiente pantalla
            }else{
                // lanzar dialog con error
            }
            _isLoading.value = false
        }
    }
}