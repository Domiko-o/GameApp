package com.example.gameapp.domain.repositories

import com.example.gameapp.domain.entities.Game

/**
 * Интерфейс репозитория для работы с игровыми сессиями.
 * Определяет методы создания и получения списка игр.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
interface GameRepository {
    /**
     * Создаёт новую игровую сессию.
     * @param game - объект игры для создания
     * @return Result<Game> - результат операции создания
     */
    suspend fun createGame(game: Game): Result<Game>

    /**
     * Получает список всех игровых сессий.
     * @return Result<List<Game>> - список игр
     */
    suspend fun getGames(): Result<List<Game>>
}