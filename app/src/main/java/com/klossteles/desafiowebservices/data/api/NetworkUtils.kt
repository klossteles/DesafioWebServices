package com.klossteles.desafiowebservices.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {
    companion object {
        private const val BASE_URL = "https://gateway.marvel.com/"

        private val _gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss-SSSS").create()

        fun getRetrofitInstance(): Retrofit {
            val client = OkHttpClient
                .Builder()
                .addInterceptor(NetworkInterceptor())
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(_gson))
                .client(client)
                .build()
        }
    }
}