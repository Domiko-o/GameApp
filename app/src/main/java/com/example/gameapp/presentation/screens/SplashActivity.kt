package com.example.gameapp.presentation.screens

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.gameapp.databinding.ActivitySplashBinding

/**
 * Экран заставки приложения (Splash Screen).
 * Отображается при запуске приложения в течение 2 секунд, затем переходит на WelcomeActivity.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Задержка 2 секунды перед переходом на WelcomeActivity
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToWelcome()
        }, 2000)
    }

    /**
     * Переход на экран приветствия.
     */
    private fun navigateToWelcome() {
        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }
}