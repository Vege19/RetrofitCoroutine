package com.github.vege19.coroutine_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
        //Show progress bar while loading jokes
        progressBar.visibility = View.VISIBLE
        //Make api request with co-routine
        GlobalScope.launch(Dispatchers.Main) {
            try {
                //Save our jokes
                jokes = jokeApi.getJokes().await()
                //Set jokes in recyclerview
                setJokes()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun setJokes() {
        recyclerViewSetUp()
        //Hide progress bar until data is loaded
        progressBar.visibility =View.GONE
    }

}
