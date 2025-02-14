package com.example.guia_03_de_carnet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login,
            container, false)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        val emailEditText =
            view.findViewById<EditText>(R.id.emailEditText)
        val passwordEditText =
            view.findViewById<EditText>(R.id.passwordEditText)
        val loginButton =
            view.findViewById<Button>(R.id.loginButton)
        val registerTextView =
            view.findViewById<TextView>(R.id.registerTextView)
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            // Sign in with email and password
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success
                        Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
                            // Navigate to HomeFragment
                                val fragmentManager: FragmentManager =
                        requireActivity().supportFragmentManager
                        fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer,
                                HomeFragment())
                            .addToBackStack(null) // Add to back stack so the user can navigate back
                            .commit()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        registerTextView.setOnClickListener {
            // Navigate to RegisterFragment
            val fragmentManager: FragmentManager =
                requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer,
                    RegisterFragment())
                .addToBackStack(null) // Add to back stack so the user can navigate back
                .commit()
        }
        return view
    }
}

