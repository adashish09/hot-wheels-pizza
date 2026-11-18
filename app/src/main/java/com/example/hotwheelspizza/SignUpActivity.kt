package com.example.hotwheelspizza

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Toast
import com.example.hotwheelspizza.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpActivity : AppCompatActivity() {
        private lateinit var binding: ActivitySignUpBinding
        private lateinit var firebaseAuth: FirebaseAuth

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivitySignUpBinding.inflate(layoutInflater)
            setContentView(binding.root)

            firebaseAuth = FirebaseAuth.getInstance()

            binding.btnSignUp.setOnClickListener {
                val name = binding.etName.text.toString().trim()
                val email = binding.etEmail.text.toString().trim()
                val phone = binding.etPhone.text.toString().trim()
                val password = binding.etPassword.text.toString()
                val confirmPassword = binding.etConfirmPassword.text.toString()
                val isTermsAccepted = binding.checkboxTerms.isChecked

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() ||
                    password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (!isTermsAccepted) {
                    Toast.makeText(this, "You must agree to the Terms and Conditions", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (password != confirmPassword) {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Firebase sign up
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, SignInActivity::class.java))
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
            }

            binding.tvAlreadyHaveAccount.setOnClickListener {
                startActivity(Intent(this, SignInActivity::class.java))
                finish()
            }
        }
}