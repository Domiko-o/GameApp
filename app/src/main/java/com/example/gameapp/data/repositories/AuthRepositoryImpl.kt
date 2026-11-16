package com.example.gameapp.data.repositories

import com.example.gameapp.data.datasources.RetrofitClient
import com.example.gameapp.data.dto.LoginRequest
import com.example.gameapp.data.dto.RegisterRequest
import com.example.gameapp.domain.entities.User
import com.example.gameapp.domain.repositories.AuthRepository

/**
 * Реализация репозитория аутентификации.
 * Выполняет сетевые запросы через ApiService для входа и регистрации.
 * Преобразует DTO в доменные модели User.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
class AuthRepositoryImpl : AuthRepository {

    private val apiService = RetrofitClient.apiService

    /**
     * Выполняет вход пользователя через API.
     * @param email - email пользователя
     * @param password - пароль пользователя
     * @return Result<User> - результат операции с данными пользователя
     */
    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val response = apiService.login(LoginRequest(email, password))
            Result.success(User(response.id, response.email, response.name))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Выполняет регистрацию нового пользователя через API.
     * @param email - email пользователя
     * @param password - пароль пользователя
     * @param name - имя пользователя
     * @return Result<User> - результат операции с данными пользователя
     */
    override suspend fun register(email: String, password: String, name: String): Result<User> {
        return try {
            val response = apiService.register(RegisterRequest(email, password, name))
            Result.success(User(response.id, response.email, response.name))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}