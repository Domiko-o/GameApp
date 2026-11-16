package com.example.gameapp.domain.entities

/**
 * Класс представляет модель игровой сессии.
 * Хранит информацию о запланированной игре (название, дата, время).
 * Дата создания: 14-11-2026
 * Автор: 5
 */
data class Game(
    val id: String,
    val title: String,
    val date: Long,
    val time: String
)