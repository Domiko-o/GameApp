package com.example.gameapp.presentation.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gameapp.databinding.ActivityScheduleGameBinding
import com.example.gameapp.presentation.viewmodels.GameState
import com.example.gameapp.presentation.viewmodels.GameViewModel
import java.util.Calendar

/**
 * Экран создания новой игровой сессии.
 * Позволяет пользователю ввести название, выбрать дату и время игры.
 * После успешного создания перенаправляет на экран Success.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
class ScheduleGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScheduleGameBinding
    private val gameViewModel = GameViewModel()
    private var selectedDate: Long = 0L
    private var selectedTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        observeGameState()
    }

    /**
     * Настраивает обработчики нажатий на кнопки выбора даты, времени и создания игры.
     */
    private fun setupClickListeners() {
        binding.btnSelectDate.setOnClickListener { showDatePicker() }
        binding.btnSelectTime.setOnClickListener { showTimePicker() }
        binding.btnCreateGame.setOnClickListener { createGame() }
    }

    /**
     * Показывает диалог выбора даты.
     */
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                selectedDate = calendar.timeInMillis
                binding.tvSelectedDate.text = "$dayOfMonth.${month + 1}.$year"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    /**
     * Показывает диалог выбора времени.
     */
    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                binding.tvSelectedTime.text = selectedTime
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }

    /**
     * Создаёт игровую сессию через ViewModel.
     * Проверяет заполненность всех полей перед отправкой.
     */
    private fun createGame() {
        val title = binding.etGameTitle.text.toString().trim()

        if (title.isEmpty()) {
            Toast.makeText(this, "Введите название игры", Toast.LENGTH_SHORT).show()
            return
        }
        if (selectedDate == 0L) {
            Toast.makeText(this, "Выберите дату", Toast.LENGTH_SHORT).show()
            return
        }
        if (selectedTime.isEmpty()) {
            Toast.makeText(this, "Выберите время", Toast.LENGTH_SHORT).show()
            return
        }

        gameViewModel.createGame(title, selectedDate, selectedTime)
    }

    /**
     * Наблюдает за состоянием ViewModel и реагирует на результат создания игры.
     */
    private fun observeGameState() {
        gameViewModel.gameState.observe(this) { state ->
            when (state) {
                is GameState.Loading -> {
                    binding.btnCreateGame.isEnabled = false
                }
                is GameState.Success -> {
                    val intent = Intent(this, SuccessActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                is GameState.Error -> {
                    binding.btnCreateGame.isEnabled = true
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}