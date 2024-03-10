package com.arjungupta08.hotelmanager.onboarding.authentication

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.arjungupta08.hotelmanager.R
import com.arjungupta08.hotelmanager.databinding.ActivityLoginBinding
import com.arjungupta08.hotelmanager.utils.leftInAnimation
import com.arjungupta08.hotelmanager.utils.rightInAnimation

class LoginActivity : AppCompatActivity() {
    private lateinit var bindingMobile : ActivityLoginBinding

    // Fonts
    private lateinit var roboto : Typeface
    private lateinit var robotoBold : Typeface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMobile = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bindingMobile.root)

        roboto = ResourcesCompat.getFont(applicationContext, R.font.roboto)!!
        robotoBold = ResourcesCompat.getFont(applicationContext, R.font.roboto_bold)!!

        replaceMobileFragment(LoginFragment())
        leftInAnimation( bindingMobile.authFragContainerMobile, applicationContext)

        bindingMobile.openLoginScreen.setOnClickListener {
            replaceMobileFragment(LoginFragment())
            leftInAnimation( bindingMobile.authFragContainerMobile, applicationContext)
            setText(bindingMobile.openSignUpScreen,"New here?\nSign up",12f,roboto,R.color.black,R.drawable.rounded_corner_right_grey_background)

            setText(bindingMobile.openLoginScreen,"Log in",14f,robotoBold,R.color.white,R.drawable.rounded_corner_left_black_background)
            bindingMobile.openLoginScreen.elevation = 9f
            bindingMobile.openSignUpScreen.elevation = 0f
        }

        bindingMobile.openSignUpScreen.setOnClickListener {
            replaceMobileFragment(SignUpFragment())
            rightInAnimation(bindingMobile.authFragContainerMobile, applicationContext)
            setText(bindingMobile.openSignUpScreen,"Sign up",14f,robotoBold,R.color.white,R.drawable.rounded_corner_left_black_background)
            bindingMobile.openSignUpScreen.elevation = 9f
            bindingMobile.openLoginScreen.elevation = 0f

            setText(bindingMobile.openLoginScreen,"Existing User?\nLog in",12f,roboto,R.color.black,R.drawable.rounded_corner_right_grey_background)

        }
    }

    private fun replaceMobileFragment(fragment: Fragment) {
        if (fragment !=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.authFragContainerMobile,fragment)
            transaction.commit()
        }
    }

    private fun setText(textView: TextView, text:String, size: Float, typFace: Typeface, color:Int, setBackground:Int ){
        textView.text = text
        textView.textSize = size
        textView.typeface = typFace
        textView.setTextColor(ContextCompat.getColor(applicationContext,color))
        textView.background = ContextCompat.getDrawable(applicationContext,setBackground)
    }

}