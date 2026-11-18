package com.example.hotwheelspizza

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Toast
import com.example.hotwheelspizza.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
class SignInActivity : AppCompatActivity() {
        private lateinit var binding: ActivitySignInBinding
        private lateinit var firebaseAuth: FirebaseAuth

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivitySignInBinding.inflate(layoutInflater)
            setContentView(binding.root)

            firebaseAuth = FirebaseAuth.getInstance()

            binding.btnSignIn.setOnClickListener {
                val email = binding.etEmail.text.toString().trim()
                val password = binding.etPassword.text.toString().trim()

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
            }

            binding.tvCreateAccount.setOnClickListener {
                startActivity(Intent(this, SignUpActivity::class.java))
                finish()
            }
        }
    }