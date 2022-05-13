package ru.exercise.data.repository

import ru.exercise.core.other.Constants
import ru.exercise.data.mappers.toDomain
import ru.exercise.data.network.NewsRequestApi
import ru.exercise.domain.model.News
import ru.exercise.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsRequestApi
) : NewsRepository {

    override suspend fun getNewsByQuery(query: String): List<News> {
        return api.getNewsByQuery(Constants.NEWS_API_KEY, query).toDomain()
    }

}