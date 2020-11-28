package com.klossteles.desafiowebservices.comic.repository

import com.klossteles.desafiowebservices.comic.model.ComicsModel
import com.klossteles.desafiowebservices.data.api.NetworkUtils
import com.klossteles.desafiowebservices.data.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface IComicEndpoint {
    @GET("/v1/public/comics")
    suspend fun getComics(@Query("offset") offset: Int? = 0): ResponseModel<ComicsModel>

    companion object {
        val endpoint: IComicEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(IComicEndpoint::class.java)
        }
    }
}