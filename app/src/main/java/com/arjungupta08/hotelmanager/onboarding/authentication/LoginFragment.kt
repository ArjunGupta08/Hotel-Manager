package com.arjungupta08.hotelmanager.onboarding.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arjungupta08.hotelmanager.databinding.FragmentLoginBinding
import com.arjungupta08.hotelmanager.utils.shakeAnimation
import java.lang.Exception

class LoginFragment : Fragment() {
    private lateinit var bindingMobile : FragmentLoginBinding

    private lateinit var email : String
    private lateinit var password : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingMobile = FragmentLoginBinding.inflate(inflater, container, false)
        return bindingMobile.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingMobile.signInCard.setOnClickListener {

            if (bindingMobile.emailET.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.emailLayout, requireContext())
                bindingMobile.emailLayout.error = "Please enter username"

            } else if (bindingMobile.passwordET.text!!.isEmpty()) {
                bindingMobile.passwordLayout.error = "Please enter password"
                shakeAnimation(bindingMobile.passwordLayout, requireContext())
                bindingMobile.emailLayout.isErrorEnabled = false

            } else {
                bindingMobile.emailLayout.isErrorEnabled = false
                bindingMobile.passwordLayout.isErrorEnabled = false
                email = bindingMobile.emailET.text.toString()
                password = bindingMobile.passwordET.text.toString()

                try {
//                    login()
                } catch (e: Exception) {
                    println(e.toString())
                }
            }
        }
    }
}