package com.klossteles.desafiowebservices.comic.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.klossteles.desafiowebservices.R
import com.squareup.picasso.Picasso

class ComicCoverFullscreenFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_comic_cover_fullscreen, container, false)
        val image = view.findViewById<ImageView>(R.id.imgCoverFullscreen)
        val coverThumbnail = arguments?.getString(ComicFragment.COMIC_IMAGE)
        Picasso.get().load(coverThumbnail).into(image)
        onCloseDialog(view)

        return view
    }

    private fun onCloseDialog(view: View) {
        view.findViewById<ImageView>(R.id.icCloseDialog).setOnClickListener {
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog =  super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }
}