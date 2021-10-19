package com.example.mvipattern.utilities

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.mvipattern.utilities.extensions.imageview.load


class DatabindingAttributes {
    companion object {
        @JvmStatic
        @BindingAdapter("android:src")
        fun setImageView(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl)
        }
    }


}