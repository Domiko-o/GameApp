package com.example.gameapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameapp.data.repositories.AuthRepositoryImpl
import com.example.gameapp.domain.entities.User
import com.example.gameapp.domain.usecases.LoginUseCase
import com.example.gameapp.domain.usecases.RegisterUseCase
import kotlinx.coroutines.launch

/**
 * ViewModel для управления состоянием экранов авторизации и регистрации.
 * Обрабатывает пользовательский ввод и вызывает Use Cases для выполнения бизнес-логики.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
class AuthViewModel : ViewModel() {

    private val authRepository = AuthRepositoryImpl()
    private val loginUseCase = LoginUseCase(authRepository)
    private val registerUseCase = RegisterUseCase(authRepository)

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    /**
     * Выполняет вход пользователя в систему.
     * @param email - email пользователя
     * @param password - пароль пользователя
     */
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = loginUseCase.execute(email, password)
            _authState.value = when {
                result.isSuccess -> AuthState.Success(result.getOrNull()!!)
                else -> AuthState.Error(result.exceptionOrNull()?.message ?: "Ошибка входа")
            }
        }
    }

    /**
     * Выполняет регистрацию нового пользователя.
     * @param email - email пользователя
     * @param password - пароль пользователя
     * @param name - имя пользователя
     */
    fun register(email: String, password: String, name: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = registerUseCase.execute(email, password, name)
            _authState.value = when {
                result.isSuccess -> AuthState.Success(result.getOrNull()!!)
                else -> AuthState.Error(result.exceptionOrNull()?.message ?: "Ошибка регистрации")
            }
        }
    }
}

/**
 * Запечатанный класс, описывающий состояние экрана авторизации.
 * Используется для безопасной передачи состояния между ViewModel и UI.
 */
sealed class AuthState {
    /** Состояние загрузки (ожидание ответа сервера) */
    object Loading : AuthState()
    /** Успешный вход/регистрация с данными пользователя */
    data class Success(val user: User) : AuthState()
    /** Ошибка с текстовым сообщением */
    data class Error(val message: String) : AuthState()
}