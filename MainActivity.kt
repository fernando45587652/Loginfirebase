package com.example.guia_03_de_carnet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance() // Initialize Firebase Auth
        // Load the login fragment by default
        loadFragment(LoginFragment())
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // User is signed in, navigate to HomeFragment
            loadFragment(HomeFragment())
        }
    }
    private fun loadFragment(fragment: Fragment) {
        val transaction =
            supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        //transaction.addToBackStack(null) // Remove addToBackStack from MainActivity
        transaction.commit()
    }
}