package com.example.gameapp.presentation.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gameapp.databinding.ActivityLandingBinding

/**
 * Главный экран приложения (Landing Screen).
 * Отображает меню с основными действиями: создание игры, расписание, профиль.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
class LandingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    /**
     * Настраивает обработчики нажатий на кнопки меню.
     */
    private fun setupClickListeners() {
        binding.btnScheduleGame.setOnClickListener {
            navigateToScheduleGame()
        }
        binding.btnGameSchedule.setOnClickListener {
            // Заглушка: переход на экран расписания будет добавлен позже
        }
        binding.btnProfile.setOnClickListener {
            // Заглушка: переход на экран профиля будет добавлен позже
        }
        binding.btnLogout.setOnClickListener {
            navigateToLogin()
        }
    }

    /**
     * Переход на экран создания игры.
     */
    private fun navigateToScheduleGame() {
        val intent = Intent(this, ScheduleGameActivity::class.java)
        startActivity(intent)
    }

    /**
     * Переход на экран входа (выход из аккаунта).
     * Закрывает текущий стек экранов.
     */
    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}