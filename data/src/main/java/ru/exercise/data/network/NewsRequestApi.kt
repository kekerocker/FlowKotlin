package ru.exercise.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.exercise.data.model.NewsDTO

interface NewsRequestApi {

    @GET("everything")
    suspend fun getNewsByQuery(
        @Query("apiKey") api: String,
        @Query("q") query: String
    ): NewsDTO

}