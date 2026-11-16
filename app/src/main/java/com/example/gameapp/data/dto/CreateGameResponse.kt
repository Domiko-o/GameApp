package com.example.gameapp.data.dto

/**
 * DTO для ответа сервера при создании игры.
 * Содержит данные созданной игровой сессии.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
data class CreateGameResponse(
    val id: String,
    val title: String,
    val date: Long,
    val time: String
)