package com.example.gameapp.domain.entities

/**
 * Класс представляет модель пользователя системы.
 * Отвечает за хранение данных пользователя после авторизации.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
data class User(
    val id: String,
    val email: String,
    val name: String
)