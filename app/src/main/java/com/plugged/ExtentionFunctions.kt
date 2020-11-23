package com.plugged

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


@BindingAdapter(value = ["setImageUrl"])
    fun ImageView.bindImageUrl(url: String?) {
        if (url != null && url.isNotBlank()) {

//        Glide.with(this).load(url).into(this)

            Picasso.get()
                .load(url)
                .into(this)
        }
    }
