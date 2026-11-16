package com.example.gameapp.data.dto

/**
 * DTO для запроса регистрации нового пользователя.
 * Содержит email, пароль и имя для отправки на сервер.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String
)