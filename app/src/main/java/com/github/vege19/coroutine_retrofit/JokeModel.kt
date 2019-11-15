package com.github.vege19.coroutine_retrofit

import com.google.gson.annotations.SerializedName

data class JokeModel(
    @SerializedName("id") var jokeId: Int = 0,
    @SerializedName("id_autor") var authorId: Int = 0,
    @SerializedName("nombre") var jokeName: String = "",
    @SerializedName("texto") var jokeText: String = "",
    @SerializedName("created_at") var jokeCreatedAt: String = "",
    @SerializedName("updated_at") var jokeUpdatedAt: String = "",
    @SerializedName("image") var jokeImage: String = ""
)