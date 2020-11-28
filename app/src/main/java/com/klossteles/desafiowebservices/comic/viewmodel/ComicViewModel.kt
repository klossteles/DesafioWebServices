package com.klossteles.desafiowebservices.comic.viewmodel

import androidx.lifecycle.*
import com.klossteles.desafiowebservices.comic.model.ComicsModel
import com.klossteles.desafiowebservices.comic.repository.ComicRepository
import kotlinx.coroutines.Dispatchers

class ComicViewModel (
    private val _repository: ComicRepository
): ViewModel() {
    private var _comics: List<ComicsModel> = listOf()
    private var _totalPages: Int = 0
    private var _offset: Int = 0
    private var _count: Int = 0

    fun getList() = liveData(Dispatchers.IO) {
        val response = _repository.getComics()

        _count = response.data.count
        if (response.data.total != 0) {
            _totalPages = response.data.total / _count
        } else {
            _totalPages = 0
        }
        _comics = response.data.results
        emit(response.data.results)
    }

    fun nextPage() = liveData(Dispatchers.IO) {
        if (_offset.plus(_count) <= _totalPages) {
            _offset = _offset.plus(_count)
            val response = _repository.getComics(_offset)
            emit(response.data.results)
        }
    }

    class ComicViewModelFactory(private val _repository: ComicRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ComicViewModel(_repository) as T
        }
    }
}