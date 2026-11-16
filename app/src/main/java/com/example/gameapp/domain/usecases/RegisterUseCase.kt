package com.example.gameapp.domain.usecases

import com.example.gameapp.domain.entities.User
import com.example.gameapp.domain.repositories.AuthRepository

/**
 * Класс отвечает за сценарий регистрации нового пользователя.
 * Реализует бизнес-логику создания учётной записи через абстракцию AuthRepository.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
class RegisterUseCase(private val repository: AuthRepository) {
    /**
     * Выполняет регистрацию нового пользователя.
     * @param email - email пользователя
     * @param password - пароль пользователя
     * @param name - имя пользователя
     * @return Result<User> - результат операции регистрации с данными пользователя
     */
    suspend fun execute(email: String, password: String, name: String): Result<User> {
        return repository.register(email, password, name)
    }
}