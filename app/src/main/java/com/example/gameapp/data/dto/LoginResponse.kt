package com.example.gameapp.data.dto

/**
 * DTO для ответа сервера при авторизации.
 * Содержит данные пользователя после успешного входа.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
data class LoginResponse(
    val id: String,
    val email: String,
    val name: String
)