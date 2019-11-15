package com.github.vege19.coroutine_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var jokes = arrayListOf<JokeModel>()
    private lateinit var retrofit: Retrofit
    private val baseUrl = "http://34.211.243.185:8080/"
    private lateinit var jokeApi: JokeApi
    private lateinit var jokeAdapter: JokeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeRetrofit()
        recyclerViewSetUp()
        getJokes()

    }

    private fun recyclerViewSetUp() {
        jokeAdapter = JokeAdapter(jokes, this)
        //Setting recycler view
        jokesRecyclerView.layoutManager = LinearLayoutManager(this)
        jokesRecyclerView.adapter = jokeAdapter
    }

    private fun initializeRetrofit() {
        //Build retrofit
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Get api interface
        jokeApi = retrofit.create(JokeApi::class.java)
    }

    private fun getJokes() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                jokes = jokeApi.getJokes().await()
                setJokes()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun setJokes() {
        recyclerViewSetUp()
    }

}
