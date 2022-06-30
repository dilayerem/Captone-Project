package com.example.eshoppingapp.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.eshoppingapp.R
import com.example.eshoppingapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding?=null
    private lateinit var firebaseAuth: FirebaseAuth
    private val loginBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login,container,false)
        firebaseAuth = FirebaseAuth.getInstance()
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginBinding.tologin2.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }
        loginBinding.backbutton2.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
        loginBinding.loginbutton.setOnClickListener {
            authControlLogin()
        }
    }

    private fun firebaseAuthLogin(){
        //FirebaseUser currentUser = firebaseAuth.getCurrentUser()
        firebaseAuth.signInWithEmailAndPassword(loginBinding.editTextTextEmailAddress2.text.toString(),loginBinding.editTextTextPassword2.text.toString()).addOnCompleteListener {
                task->
            if(task.isSuccessful){
                findNavController().navigate(R.id.action_loginFragment_to_mainPageFragment)
                Toast.makeText(context,"Sign in successful.", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, "Sign in unsuccessful. Try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun authControlLogin(){
        loginBinding.apply {
            when{
                TextUtils.isEmpty(editTextTextEmailAddress2.text.toString().trim())->{
                    editTextTextEmailAddress2.setError("Invalid e-mail address.")
                }
                TextUtils.isEmpty(editTextTextPassword2.text.toString().trim())->{
                    editTextTextPassword2.setError("Invalid a password.")
                }
                editTextTextEmailAddress2.text.toString().isNotEmpty() && editTextTextPassword2.text.toString().isNotEmpty()->
                    if (editTextTextPassword2.text.toString().length>5){
                        //add more conditions: lower case + upper case
                        firebaseAuthLogin()
                    }
                    else{
                        editTextTextPassword2.setError("Your password must be more than 5 characters long.")
                    }
            }
        }
    }
}