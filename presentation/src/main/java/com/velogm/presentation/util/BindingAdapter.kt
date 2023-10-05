package com.velogm.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.velogm.presentation.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    view.load(url)
}


@BindingAdapter("setCircleImage")
fun ImageView.setCircleImage(img: String?) {
    if (img.isNullOrEmpty()) {
        load(R.drawable.ic_follow_follower)
    } else {
        load(img) {
            transformations(RoundedCornersTransformation(1000f))
        }
    }

}
