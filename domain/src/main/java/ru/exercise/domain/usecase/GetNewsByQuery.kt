package ru.exercise.domain.usecase

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import ru.exercise.domain.common.Resource
import ru.exercise.domain.model.News
import ru.exercise.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsByQuery @Inject constructor(
    private val repository: NewsRepository
) {
    private val _newsData: MutableSharedFlow<Resource<List<News>>> = MutableSharedFlow(1)

    operator fun invoke(): SharedFlow<Resource<List<News>>> {
        return _newsData
    }

    suspend fun sendRequest(query: String) {
        _newsData.emit(Resource.Loading())
        _newsData.emit(Resource.on { repository.getNewsByQuery(query) })
    }

}