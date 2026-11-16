package com.example.gameapp.domain.usecases

import com.example.gameapp.domain.entities.Game
import com.example.gameapp.domain.repositories.GameRepository

/**
 * Класс отвечает за сценарий создания новой игровой сессии.
 * Реализует бизнес-логику сохранения игры через абстракцию GameRepository.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
class CreateGameUseCase(private val repository: GameRepository) {
    /**
     * Создаёт новую игровую сессию и сохраняет её на сервере.
     * @param game - объект игры с названием, датой и временем
     * @return Result<Game> - результат операции создания с данными игры
     */
    suspend fun execute(game: Game): Result<Game> {
        return repository.createGame(game)
    }
}