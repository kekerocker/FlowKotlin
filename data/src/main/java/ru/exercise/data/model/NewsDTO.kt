package ru.exercise.data.model

data class NewsDTO(
    val articles: List<ArticleDTO>?,
    val status: String?,
    val totalResults: Int?
) {
    data class ArticleDTO(
        val author: String?,
        val content: String?,
        val description: String?,
        val publishedAt: String?,
        val source: SourceDTO?,
        val title: String?,
        val url: String?,
        val urlToImage: String?
    ) {
        data class SourceDTO(
            val id: String?,
            val name: String?
        )
    }
}