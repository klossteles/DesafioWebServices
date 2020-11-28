package com.klossteles.desafiowebservices.comic.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.klossteles.desafiowebservices.R
import com.klossteles.desafiowebservices.comic.model.ComicDate
import com.klossteles.desafiowebservices.comic.model.ComicPrice
import com.klossteles.desafiowebservices.comic.repository.ComicRepository
import com.klossteles.desafiowebservices.comic.viewmodel.ComicViewModel
import com.klossteles.desafiowebservices.data.model.ThumbnailModel
import com.squareup.picasso.Picasso
import java.util.*

class ComicFragment : Fragment() {
    private lateinit var _view: View
    private lateinit var _viewModel: ComicViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comic, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view
        _viewModel = ViewModelProvider(
            this,
            ComicViewModel.ComicViewModelFactory(ComicRepository())
        ).get(ComicViewModel::class.java)

        showLoading(true)
        val comicDescription = arguments?.getString(ComicListFragment.COMICS_DESCRIPTION)
        val comicTitle = arguments?.getString(ComicListFragment.COMICS_TITLE)
        val comicDates = arguments?.get(ComicListFragment.COMICS_DATES)
        val comicPrices = arguments?.get(ComicListFragment.COMICS_PRICE)
        val comicPages = arguments?.getInt(ComicListFragment.COMICS_PAGES)
        val comicImages = arguments?.get(ComicListFragment.COMICS_IMAGES)
        val comicThumbnail = arguments?.getString(ComicListFragment.COMICS_THUMBNAIL)

        val imgLandscape = _view.findViewById<ImageView>(R.id.imgLandscape)
        val imgComicCover = _view.findViewById<ImageView>(R.id.imgComicCover)
        val txtTitleComic = _view.findViewById<TextView>(R.id.txtTitleComic)
        val txtDescriptionComic = _view.findViewById<TextView>(R.id.txtDescriptionComic)
        val txtPublished = _view.findViewById<TextView>(R.id.txtPublished)
        val txtPrice = _view.findViewById<TextView>(R.id.txtPrice)
        val txtPage = _view.findViewById<TextView>(R.id.txtPage)

        showLoading(true)
        txtTitleComic.text = comicTitle
        if (comicDescription != null) {
                txtDescriptionComic.text = comicDescription
        }
        if (comicDates != null) {
            for (date in comicDates as List<ComicDate>) {
                if (date.type?.contains("onsaleDate") == true){
                    var calendar = Calendar.getInstance()
                    calendar.time = date.date!!
                    txtPublished.text = "${calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())} ${calendar.get(Calendar.DAY_OF_MONTH)}, ${calendar.get(Calendar.YEAR)}"
                }
            }
        }
        if (comicPrices != null) {
            txtPrice.text = "$ ${(comicPrices as List<ComicPrice>)[0].price.toString()}"
        }
        txtPage.text = comicPages?.toString()
        Picasso.get().load(comicThumbnail).into(imgComicCover)
        if (comicImages != null) {
            Picasso.get().load((comicImages as List<ThumbnailModel>)[(comicImages as List<ThumbnailModel>).size - 1].getImagePath("landscape_incredible")).into(imgLandscape)
        }
        showLoading(false)
        setBackNavigation()
    }

    private fun setBackNavigation() {
        _view.findViewById<ImageView>(R.id.imgBackComic).setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        val viewLoading = _view.findViewById<View>(R.id.comicLoading)

        if (isLoading) {
            viewLoading.visibility = View.VISIBLE
        } else {
            viewLoading.visibility = View.GONE
        }
    }
}