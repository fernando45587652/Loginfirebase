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

class RegisterFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register,
            container, false)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        val emailEditText =
            view.findViewById<EditText>(R.id.emailEditText)
        val passwordEditText =
            view.findViewById<EditText>(R.id.passwordEditText)
        val registerButton =
            view.findViewById<Button>(R.id.registerButton)
        val loginTextView =
            view.findViewById<TextView>(R.id.loginTextView)
        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            // Create user with email and password
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Registration success
                        Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()
                            // Navigate to LoginFragment
                            val fragmentManager: FragmentManager =
                        requireActivity().supportFragmentManager
                        fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer,
                                LoginFragment())
                            .addToBackStack(null)
                            .commit()
                    } else {
                        // If registration fails, display a message to the user.
                        Toast.makeText(context, "Registration failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        loginTextView.setOnClickListener {
            // Navigate to LoginFragment
            val fragmentManager: FragmentManager =
                requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer,
                    LoginFragment())
                .addToBackStack(null) // Add to back stack so the user can navigate back
                .commit()
        }
        return view
    }
}
