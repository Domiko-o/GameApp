package com.example.gameapp.presentation.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gameapp.databinding.ActivitySuccessBinding

/**
 * Экран успешного создания игровой сессии.
 * Отображается после публикации игры и возвращает пользователя на Landing Screen.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
class SuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    /**
     * Настраивает обработчики нажатий на элементы экрана.
     */
    private fun setupClickListeners() {
        binding.btnBackToLanding.setOnClickListener {
            navigateToLanding()
        }
    }

    /**
     * Переход на главный экран (Landing Screen).
     * Закрывает текущий экран и открывает LandingActivity.
     */
    private fun navigateToLanding() {
        val intent = Intent(this, LandingActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}