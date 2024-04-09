package com.arjungupta08.hotelmanager.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.ImageView
import com.arjungupta08.hotelmanager.R
import com.bumptech.glide.Glide


fun showProgressDialog (context: Context) : Dialog {
    val progressDialog = Dialog(context)
    progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    progressDialog.setCancelable(false)
    progressDialog.setContentView(R.layout.progress_dialog)
    progressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    val image = progressDialog.findViewById<ImageView>(R.id.imageview)
    Glide.with(context).load(R.drawable.animated_three_dot).into(image)
    progressDialog.show()
    return progressDialog
}
