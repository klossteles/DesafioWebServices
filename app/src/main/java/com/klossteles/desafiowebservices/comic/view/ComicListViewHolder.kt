package com.klossteles.desafiowebservices.comic.view

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.klossteles.desafiowebservices.R
import com.klossteles.desafiowebservices.comic.model.ComicsModel
import com.squareup.picasso.Picasso

class ComicListViewHolder(_view: View): RecyclerView.ViewHolder(_view) {
    private val number = _view.findViewById<TextView>(R.id.txtComicNumber)
    private val image = _view.findViewById<ImageView>(R.id.comicImage)

    @SuppressLint("SetTextI18n")
    fun bind(comicModel: ComicsModel) {
        number.text = "# ${comicModel.id}"
        val imagePath = comicModel.thumbnail?.getImagePath()
        Picasso.get().load(imagePath).into(image)
    }
}