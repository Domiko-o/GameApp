package com.example.gameapp.presentation.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gameapp.databinding.ActivityWelcomeBinding

/**
 * Экран приветствия пользователя (Welcome Screen).
 * Отображает информацию о приложении и предлагает перейти к регистрации.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    /**
     * Настраивает обработчик нажатия на кнопку "Далее".
     */
    private fun setupClickListeners() {
        binding.btnNext.setOnClickListener {
            navigateToRegistration()
        }
    }

    /**
     * Переход на экран регистрации.
     */
    private fun navigateToRegistration() {
        startActivity(Intent(this, RegistrationActivity::class.java))
        finish()
    }
}