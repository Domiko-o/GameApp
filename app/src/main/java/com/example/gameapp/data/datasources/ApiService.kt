package com.example.gameapp.data.datasources

import com.example.gameapp.data.dto.*
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Интерфейс API сервиса для сетевых запросов.
 * Определяет эндпоинты для авторизации, регистрации и создания игр.
 * Использует библиотеку Retrofit для HTTP-запросов.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
interface ApiService {
    /**
     * Запрос на авторизацию пользователя.
     * @param request - данные для входа (email, password)
     * @return LoginResponse - данные пользователя после успешного входа
     */
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    /**
     * Запрос на регистрацию нового пользователя.
     * @param request - данные для регистрации (email, password, name)
     * @return RegisterResponse - данные созданного пользователя
     */
    @POST("register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse

    /**
     * Запрос на создание новой игровой сессии.
     * @param request - данные игры (title, date, time)
     * @return CreateGameResponse - данные созданной игры
     */
    @POST("games")
    suspend fun createGame(@Body request: CreateGameRequest): CreateGameResponse
}