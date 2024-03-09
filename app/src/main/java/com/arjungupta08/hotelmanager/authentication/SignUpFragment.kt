package com.arjungupta08.hotelmanager.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arjungupta08.hotelmanager.databinding.FragmentSignUpBinding
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

            if (bindingMobile.firstNameText.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.firstNameLayout, requireContext())
                bindingMobile.lastNameLayout.isErrorEnabled = false
                bindingMobile.phoneLayout.isErrorEnabled = false
                bindingMobile.emailLayout.isErrorEnabled = false
                bindingMobile.passwordLayout.isErrorEnabled = false
                bindingMobile.firstNameLayout.error = ("Please enter your First Name")
            } else if (bindingMobile.lastNameText.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.lastNameLayout, requireContext())
                bindingMobile.firstNameLayout.isErrorEnabled = false
                bindingMobile.phoneLayout.isErrorEnabled = false
                bindingMobile.emailLayout.isErrorEnabled = false
                bindingMobile.passwordLayout.isErrorEnabled = false
                bindingMobile.lastNameLayout.error = ("Please enter your Last Name")
            } else if (bindingMobile.phoneText.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.phoneLayout, requireContext())
                bindingMobile.firstNameLayout.isErrorEnabled = false
                bindingMobile.lastNameLayout.isErrorEnabled = false
                bindingMobile.emailLayout.isErrorEnabled = false
                bindingMobile.passwordLayout.isErrorEnabled = false
                bindingMobile.phoneLayout.error = ("Please enter your Phone number")
            } else if (bindingMobile.emailText.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.emailLayout, requireContext())
                bindingMobile.firstNameLayout.isErrorEnabled = false
                bindingMobile.lastNameLayout.isErrorEnabled = false
                bindingMobile.phoneLayout.isErrorEnabled = false
                bindingMobile.passwordLayout.isErrorEnabled = false
                bindingMobile.emailLayout.error = ("Please enter your email")
            } else if (bindingMobile.passwordText.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.passwordLayout, requireContext())
                bindingMobile.firstNameLayout.isErrorEnabled = false
                bindingMobile.lastNameLayout.isErrorEnabled = false
                bindingMobile.phoneLayout.isErrorEnabled = false
                bindingMobile.emailLayout.isErrorEnabled = false
                bindingMobile.passwordLayout.error = ("Please enter your password")
            } else {
                signUpMobile()
            }
        }
    }

    private fun signUpMobile() {

    }

}