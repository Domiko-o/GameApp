package com.example.gameapp.domain.repositories

import com.example.gameapp.domain.entities.User

/**
 * Интерфейс репозитория для работы с аутентификацией.
 * Определяет методы входа и регистрации пользователя.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
interface AuthRepository {
    /**
     * Выполняет вход пользователя в систему.
     * @param email - email пользователя
     * @param password - пароль пользователя
     * @return Result<User> - результат операции входа
     */
    suspend fun login(email: String, password: String): Result<User>

    /**
     * Выполняет регистрацию нового пользователя.
     * @param email - email пользователя
     * @param password - пароль пользователя
     * @param name - имя пользователя
     * @return Result<User> - результат операции регистрации
     */
    suspend fun register(email: String, password: String, name: String): Result<User>
}