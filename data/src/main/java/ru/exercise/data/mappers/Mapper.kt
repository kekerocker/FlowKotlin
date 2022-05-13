package ru.exercise.data.mappers

import ru.exercise.data.model.NewsDTO
import ru.exercise.domain.model.News

fun NewsDTO.toDomain(): List<News> {
    return articles?.map {
        News(
            text = it.title ?: "",
            description = it.description ?: "",
            imageUrl = it.urlToImage ?: ""
        )
    } ?: emptyList()
}