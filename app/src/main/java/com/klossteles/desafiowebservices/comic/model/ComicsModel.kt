package com.klossteles.desafiowebservices.comic.model

import com.klossteles.desafiowebservices.data.model.ThumbnailModel

class ComicsModel (
    val id: Int?,
    val title: String?,
    val description: String?,
    val pageCount: Int?,
    val dates: List<ComicDate>?,
    val prices: List<ComicPrice>?,
    val thumbnail: ThumbnailModel?,
    val images: List<ThumbnailModel>?,
)