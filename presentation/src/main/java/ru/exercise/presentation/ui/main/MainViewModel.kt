package ru.exercise.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.exercise.domain.common.Resource
import ru.exercise.domain.model.News
import ru.exercise.domain.usecase.GetNewsByQuery
import ru.exercise.presentation.model.NewsRecyclerModel
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val getNewsByQuery: GetNewsByQuery
) : ViewModel() {

    val getNews: SharedFlow<Resource<News>> = getNewsByQuery()
    val isLoading = getNews.map { it is Resource.Loading }

    fun sendQuery(query: String) {
        viewModelScope.launch {
            getNewsByQuery.sendRequest(query)
        }
    }

}