package com.example.guia_03_de_carnet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home,
            container, false)
        auth = FirebaseAuth.getInstance()
        val welcomeTextView =
            view.findViewById<TextView>(R.id.welcomeTextView)
        val logoutButton =
            view.findViewById<Button>(R.id.logoutButton)
        // Get the current user
        val user = auth.currentUser
        if (user != null) {
            welcomeTextView.text = "Welcome, ${user.email}!" // Display the user's email
        } else {
            welcomeTextView.text = "Welcome!" // Default welcome message
        }
        logoutButton.setOnClickListener {
            auth.signOut()
            // Navigate to LoginFragment
            val fragmentManager: FragmentManager =
                requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, LoginFragment())
                .addToBackStack(null)
                .commit()
        }
        return view
    }
}
