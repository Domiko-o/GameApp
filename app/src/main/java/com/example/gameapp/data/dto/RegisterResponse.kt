package com.example.gameapp.data.dto

/**
 * DTO для ответа сервера при регистрации.
 * Содержит данные созданного пользователя.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
data class RegisterResponse(
    val id: String,
    val email: String,
    val name: String
)