package com.example.gameapp.domain.usecases

import com.example.gameapp.domain.entities.User
import com.example.gameapp.domain.repositories.AuthRepository

/**
 * Класс отвечает за сценарий входа пользователя в систему.
 * Использует AuthRepository для выполнения операции входа.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
class LoginUseCase(private val repository: AuthRepository) {
    /**
     * Выполняет вход пользователя.
     * @param email - email пользователя
     * @param password - пароль пользователя
     * @return Result<User> - результат операции входа
     */
    suspend fun execute(email: String, password: String): Result<User> {
        return repository.login(email, password)
    }
}