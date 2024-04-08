package com.arjungupta08.hotelmanager.onboarding.authentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arjungupta08.hotelmanager.databinding.FragmentSignUpBinding
import com.arjungupta08.hotelmanager.onboarding.FirstOnboarding
import com.arjungupta08.hotelmanager.utils.shakeAnimation

class SignUpFragment : Fragment() {
    private lateinit var bindingMobile : FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingMobile = FragmentSignUpBinding.inflate(inflater, container, false)
        return bindingMobile.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingMobile.cardCreateAccount.setOnClickListener {
            if (bindingMobile.emailText.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.emailLayout, requireContext())
                bindingMobile.passwordLayout.isErrorEnabled = false
                bindingMobile.emailLayout.error = ("Please enter your email")
            } else if (bindingMobile.passwordText.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.passwordLayout, requireContext())
                bindingMobile.emailLayout.isErrorEnabled = false
                bindingMobile.passwordLayout.error = ("Please enter your password")
            } else {
                signUpMobile()
            }
        }
    }

    private fun signUpMobile() {
        startActivity(Intent(context, FirstOnboarding::class.java))
    }
}