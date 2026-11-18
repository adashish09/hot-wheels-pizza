package com.example.hotwheelspizza

data class Pizza(
    val name: String,
    val price: Double,
    val imageResId: Int,  // Drawable resource ID (e.g., R.drawable.pizza_margherita)
    var quantity: Int = 0
)
