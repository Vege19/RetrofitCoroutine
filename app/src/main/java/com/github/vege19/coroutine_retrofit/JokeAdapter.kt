package com.github.vege19.coroutine_retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_joke.view.*

class JokeAdapter(private var jokes: List<JokeModel>, private var context: Context) :
    RecyclerView.Adapter<JokeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_joke, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    override fun onBindViewHolder(holder: JokeAdapter.ViewHolder, position: Int) {
        val joke = jokes[position]

        //Set data
        holder.name.text = joke.jokeName
        holder.text.text = joke.jokeText
        //Set image
        Glide.with(context).load(joke.jokeImage).into(holder.image)

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.jokeNameTxt
        val image: ImageView = view.jokeImageView
        val text: TextView = view.jokeNameTxt
        val container: ConstraintLayout = view.jokeTextContainerLayout
    }

}