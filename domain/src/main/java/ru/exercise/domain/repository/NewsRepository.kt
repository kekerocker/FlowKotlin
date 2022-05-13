package ru.exercise.domain.repository

import ru.exercise.domain.model.News

interface NewsRepository {
    suspend fun getNewsByQuery(query: String): List<News>
}