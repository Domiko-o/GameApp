package com.example.gameapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameapp.data.repositories.GameRepositoryImpl
import com.example.gameapp.domain.entities.Game
import com.example.gameapp.domain.usecases.CreateGameUseCase
import kotlinx.coroutines.launch

/**
 * ViewModel для управления состоянием экрана создания игры.
 * Обрабатывает ввод данных игры и вызывает Use Case для сохранения.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
class GameViewModel : ViewModel() {

    private val gameRepository = GameRepositoryImpl()
    private val createGameUseCase = CreateGameUseCase(gameRepository)

    private val _gameState = MutableLiveData<GameState>()
    val gameState: LiveData<GameState> = _gameState

    /**
     * Создаёт новую игровую сессию и сохраняет на сервере.
     * @param title - название игры
     * @param date - дата в формате timestamp (Long)
     * @param time - время в формате "HH:MM"
     */
    fun createGame(title: String, date: Long, time: String) {
        viewModelScope.launch {
            _gameState.value = GameState.Loading
            val game = Game(id = "", title = title, date = date, time = time)
            val result = createGameUseCase.execute(game)
            _gameState.value = when {
                result.isSuccess -> GameState.Success
                else -> GameState.Error(result.exceptionOrNull()?.message ?: "Ошибка создания игры")
            }
        }
    }
}

/**
 * Запечатанный класс, описывающий состояние экрана создания игры.
 */
sealed class GameState {
    /** Состояние загрузки */
    object Loading : GameState()
    /** Успешное создание игры */
    object Success : GameState()
    /** Ошибка с текстовым сообщением */
    data class Error(val message: String) : GameState()
}