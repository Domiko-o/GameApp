package com.example.gameapp.presentation.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gameapp.databinding.ActivityRegistrationBinding
import com.example.gameapp.presentation.viewmodels.AuthViewModel
import com.example.gameapp.presentation.viewmodels.AuthState

/**
 * Экран регистрации нового пользователя.
 * Принимает имя, email и пароль, вызывает AuthViewModel для создания аккаунта.
 * При успехе переходит на LandingActivity.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private val authViewModel = AuthViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        observeAuthState()
    }

    /**
     * Настраивает обработчики нажатий на кнопки регистрации и перехода ко входу.
     */
    private fun setupClickListeners() {
        binding.btnRegister.setOnClickListener { performRegistration() }
        binding.btnGoToLogin.setOnClickListener { navigateToLogin() }
    }

    /**
     * Выполняет регистрацию пользователя с валидацией полей.
     */
    private fun performRegistration() {
        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (name.isEmpty()) {
            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show()
            return
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Введите email", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show()
            return
        }

        authViewModel.register(email, password, name)
    }

    /**
     * Наблюдает за состоянием авторизации и реагирует на результат.
     */
    private fun observeAuthState() {
        authViewModel.authState.observe(this) { state ->
            when (state) {
                is AuthState.Loading -> {
                    binding.btnRegister.isEnabled = false
                }
                is AuthState.Success -> {
                    val intent = Intent(this, LandingActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                is AuthState.Error -> {
                    binding.btnRegister.isEnabled = true
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    /**
     * Переход на экран входа.
     */
    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}