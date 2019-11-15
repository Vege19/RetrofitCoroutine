package com.github.vege19.coroutine_retrofit

import retrofit2.Call
import retrofit2.http.GET

interface JokeApi {

    @GET("chistes")
    fun getJokes(): Call<ArrayList<JokeModel>>

}