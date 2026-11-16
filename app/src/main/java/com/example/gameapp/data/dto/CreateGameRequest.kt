package com.example.gameapp.data.dto

/**
 * DTO для запроса создания игровой сессии.
 * Содержит данные игры для отправки на сервер.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
data class CreateGameRequest(
    val title: String,
    val date: Long,
    val time: String
)