package ru.exercise.data.mappers

import ru.exercise.data.model.NewsDTO
import ru.exercise.domain.model.News

fun NewsDTO.toDomain(): News {
    return News(
        articles = articles?.map {
            News.Article(
                author = it.author ?: "",
                content = it.content ?: "",
                description = it.description ?: "",
                publishedAt = it.publishedAt ?: "",
                source = News.Article.Source(
                    id = it.source?.id ?: "",
                    name = it.source?.name ?: ""
                ),
                title = it.title ?: "",
                url = it.url ?: "",
                urlToImage = it.urlToImage ?: ""
            )
        } ?: emptyList(),
        status = status ?: "",
        totalResults = totalResults ?: 0
    )
}