package com.arjungupta08.hotelmanager.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.arjungupta08.hotelmanager.R


fun leftInAnimation(view: View, context: Context) {
    // load the animation
    val animSlideIn: Animation = AnimationUtils.loadAnimation(
        context,
        R.anim.l_to_r_in_animation
    )
    // start the animation
    view.startAnimation(animSlideIn)
}

fun rightInAnimation(view: View, context: Context) {
    // load the animation
    val animSlideIn: Animation = AnimationUtils.loadAnimation(
        context,
        R.anim.right_in_animation
    )
    // start the animation
    view.startAnimation(animSlideIn)
}

fun bottomSlideInAnimation(view: View, context: Context) {
    // load the animation
    val animSlideIn: Animation = AnimationUtils.loadAnimation(
        context,
        R.anim.bottom_slide_in
    )
    // start the animation
    view.startAnimation(animSlideIn)
}

                        /* SHAKE ANIMATION*/
fun shakeAnimation(view: View, context: Context) {
    // load the animation
    val animSlideIn: Animation = AnimationUtils.loadAnimation(
        context,
        R.anim.shake_animation
    )
    // start the animation
    view.startAnimation(animSlideIn)
}

fun rightToLeftEditImageAnimation(view: View, context: Context) {
    // load the animation
    val animSlideIn: Animation = AnimationUtils.loadAnimation(
        context,
        R.anim.l_to_r_out_animation
    )
    // start the animation
    view.startAnimation(animSlideIn)
}

fun setMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
    if (view.layoutParams is ViewGroup.MarginLayoutParams) {
        val p = view.layoutParams as ViewGroup.MarginLayoutParams
        p.setMargins(left, top, right, bottom)
        view.requestLayout()
    }
}