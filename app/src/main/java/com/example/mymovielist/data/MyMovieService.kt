package com.example.mymovielist.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyMovieService {

        @GET("?apikey=adc3dc3d")
        suspend fun findMovieByName(@Query("s") query: String): MovieResponse

        @GET("{Movie-id}")
        suspend fun findMovieById(@Path("i") i: String): Movie

        companion object {
            fun getRetrofit(): MyMovieService {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://www.omdbapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

             return retrofit.create(MyMovieService::class.java)
            }
        }
}