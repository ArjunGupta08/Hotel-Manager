package com.arjungupta08.hotelmanager.onboarding.authentication

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arjungupta08.hotelmanager.DashboardActivity
import com.arjungupta08.hotelmanager.databinding.FragmentLoginBinding
import com.arjungupta08.hotelmanager.utils.shakeAnimation
import com.arjungupta08.hotelmanager.utils.showProgressDialog
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    private lateinit var bindingMobile : FragmentLoginBinding

    private lateinit var email : String
    private lateinit var password : String

    private lateinit var progressDialog : Dialog

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

            if (bindingMobile.emailText.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.emailLayout, requireContext())
                bindingMobile.passwordLayout.isErrorEnabled = false
                bindingMobile.emailLayout.error = ("Please enter your email")
            } else if (bindingMobile.passwordText.text!!.isEmpty()) {
                shakeAnimation(bindingMobile.passwordLayout, requireContext())
                bindingMobile.emailLayout.isErrorEnabled = false
                bindingMobile.passwordLayout.error = ("Please enter your password")
            } else {
                bindingMobile.emailLayout.isErrorEnabled = false
                bindingMobile.passwordLayout.isErrorEnabled = false

                email = bindingMobile.emailText.text.toString()
                password = bindingMobile.passwordText.text.toString()

                progressDialog = showProgressDialog(requireContext())
                login()
            }
        }
    }

    private fun login() {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                startActivity(Intent(context, DashboardActivity::class.java))
                activity?.finish()
            }
            .addOnFailureListener {
                progressDialog.dismiss()
                Log.e("LogIN", it.message.toString())
                Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()
            }
    }
}