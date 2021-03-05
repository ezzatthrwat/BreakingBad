package me.ezzattharwat.breakingbad.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toInvisible() {
    this.visibility = View.GONE
}

fun AppCompatActivity.showToast(
       context: Context,
       msg: String
) {
    Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
}
