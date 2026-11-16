package com.example.gameapp.data.repositories

import com.example.gameapp.data.datasources.RetrofitClient
import com.example.gameapp.data.dto.CreateGameRequest
import com.example.gameapp.domain.entities.Game
import com.example.gameapp.domain.repositories.GameRepository

/**
 * Реализация репозитория игровых сессий.
 * Выполняет сетевые запросы через ApiService для создания игр.
 * Преобразует DTO в доменные модели Game.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
class GameRepositoryImpl : GameRepository {

    private val apiService = RetrofitClient.apiService

    /**
     * Создаёт новую игровую сессию через API.
     * @param game - объект игры с названием, датой и временем
     * @return Result<Game> - результат операции с данными созданной игры
     */
    override suspend fun createGame(game: Game): Result<Game> {
        return try {
            val response = apiService.createGame(
                CreateGameRequest(game.title, game.date, game.time)
            )
            Result.success(Game(response.id, response.title, response.date, response.time))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Получает список всех игровых сессий.
     * Заглушка для будущего расширения функционала.
     * @return Result<List<Game>> - пустой список (реализация будет добавлена позже)
     */
    override suspend fun getGames(): Result<List<Game>> {
        return Result.success(emptyList())
    }
}