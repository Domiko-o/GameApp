package com.example.gameapp.data.dto

/**
 * DTO для запроса авторизации пользователя.
 * Содержит email и пароль для отправки на сервер.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
data class LoginRequest(
    val email: String,
    val password: String
)