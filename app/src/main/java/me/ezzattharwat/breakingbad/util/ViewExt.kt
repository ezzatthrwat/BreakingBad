package me.ezzattharwat.breakingbad.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import me.ezzattharwat.breakingbad.R


fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}


fun ImageView.loadImage(context: Context, url: String){
    Glide.with(context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun View.showSnackbar(snackbarText: String, timeLength: Int, action: View.OnClickListener) {
    Snackbar.make(this, snackbarText, timeLength).run {
       setAction(R.string.reload, action)
        show()
    }
}




