package com.example.gameapp.presentation.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gameapp.databinding.ActivityLoginBinding
import com.example.gameapp.presentation.viewmodels.AuthViewModel
import com.example.gameapp.presentation.viewmodels.AuthState

/**
 * Экран входа пользователя в систему.
 * Принимает email и пароль, вызывает AuthViewModel для авторизации.
 * При успехе переходит на LandingActivity.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val authViewModel = AuthViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        observeAuthState()
    }

    /**
     * Настраивает обработчики нажатий на кнопки входа и перехода к регистрации.
     */
    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener { performLogin() }
        binding.btnGoToRegister.setOnClickListener { navigateToRegister() }
    }

    /**
     * Выполняет вход пользователя с валидацией полей.
     */
    private fun performLogin() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (email.isEmpty()) {
            Toast.makeText(this, "Введите email", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show()
            return
        }

        authViewModel.login(email, password)
    }

    /**
     * Наблюдает за состоянием авторизации и реагирует на результат.
     */
    private fun observeAuthState() {
        authViewModel.authState.observe(this) { state ->
            when (state) {
                is AuthState.Loading -> {
                    binding.btnLogin.isEnabled = false
                }
                is AuthState.Success -> {
                    val intent = Intent(this, LandingActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                is AuthState.Error -> {
                    binding.btnLogin.isEnabled = true
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    /**
     * Переход на экран регистрации.
     */
    private fun navigateToRegister() {
        startActivity(Intent(this, RegistrationActivity::class.java))
    }
}