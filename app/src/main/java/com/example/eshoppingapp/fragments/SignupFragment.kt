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
import com.example.eshoppingapp.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding?=null
    private lateinit var firebaseAuth: FirebaseAuth
    private val signupBinding get()=_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup,container,false)
        firebaseAuth = FirebaseAuth.getInstance()
        return signupBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth.currentUser?.let {
            findNavController().navigate(R.id.action_signupFragment_to_mainPageFragment)
        }

        signupBinding.tologin.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
        signupBinding.backbutton.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_firstPageFragment)
        }
        signupBinding.signupbutton.setOnClickListener {
            authenticationControl()
        }
    }

    private fun firebaseAuthSignup(){
        firebaseAuth.createUserWithEmailAndPassword(signupBinding.editTextTextEmailAddress.text.toString(),signupBinding.editTextTextPassword.text.toString()).addOnCompleteListener{

            task->
            if (task.isSuccessful){
                findNavController().navigate(R.id.action_signupFragment_to_mainPageFragment)
            }
            else{
                Toast.makeText(context,"Sign Up unsuccessful. Try again.",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun authenticationControl(){
        signupBinding.apply {
            when{
                TextUtils.isEmpty(editTextTextEmailAddress.text.toString().trim())->{
                    editTextTextEmailAddress.setError("Please enter an e-mail address.")
                }
                TextUtils.isEmpty(editTextTextPassword.text.toString().trim())->{
                    editTextTextPassword.setError("Please enter a password.")
                }

                editTextTextEmailAddress.text.toString().isNotEmpty() && editTextTextPassword.text.toString().isNotEmpty()->
                    if (editTextTextPassword.text.toString().length>5){
                    //add more conditions: lower case + upper case
                        firebaseAuthSignup()
                    }
                else{
                    editTextTextPassword.setError("Your password must be more than 5 characters long.")
                    }
            }
        }
    }
}