package com.example.gameapp.data.datasources

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Синглтон для создания и настройки клиента Retrofit.
 * Обеспечивает единый экземпляр ApiService для всего приложения.
 * Дата создания: 14-11-2026
 * Автор: 5
 */
object RetrofitClient {
    // Базовый URL API сервера (заменить на реальный при интеграции)
    private const val BASE_URL = "https://api.example.com/"

    /**
     * Создаёт экземпляр Retrofit с настройками.
     * @return Retrofit - настроенный клиент для HTTP-запросов
     */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Создаёт экземпляр ApiService для выполнения запросов.
     * @return ApiService - интерфейс для работы с API
     */
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}