package com.arjungupta08.hotelmanager.onboarding.authentication

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arjungupta08.hotelmanager.databinding.FragmentSignUpBinding
import com.arjungupta08.hotelmanager.onboarding.FirstOnboarding
import com.arjungupta08.hotelmanager.utils.shakeAnimation
import com.arjungupta08.hotelmanager.utils.showProgressDialog

class SignUpFragment : Fragment() {
    private lateinit var bindingMobile : FragmentSignUpBinding

    private lateinit var progressDialog : Dialog

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
            } else if (bindingMobile.passwordText.text.toString() != bindingMobile.confirmPasswordText.text.toString()) {
                shakeAnimation(bindingMobile.passwordLayout, requireContext())
                bindingMobile.emailLayout.isErrorEnabled = false
                bindingMobile.passwordLayout.error = ("Password is not matching")
                bindingMobile.confirmPasswordLayout.error = ("Password is not matching")
            } else {
                bindingMobile.emailLayout.isErrorEnabled = false
                bindingMobile.passwordLayout.isErrorEnabled = false
                bindingMobile.confirmPasswordLayout.isErrorEnabled = false

                progressDialog = showProgressDialog(requireContext())

                val intent = Intent(context, FirstOnboarding::class.java)
                intent.putExtra("email", bindingMobile.emailText.text.toString())
                intent.putExtra("password", bindingMobile.passwordText.text.toString())
                startActivity(intent)
            }
        }
    }
}