package com.example.hotwheelspizza

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotwheelspizza.databinding.ActivityCartBinding
class CartActivity : AppCompatActivity() {

        private lateinit var binding: ActivityCartBinding
        private lateinit var cartAdapter: CartAdapter
        private val cartItems = mutableListOf<Pizza>()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityCartBinding.inflate(layoutInflater)
            setContentView(binding.root)

            cartItems.addAll(PizzaData.cartList)

            setupRecyclerView()
            updateTotalPrice()

            binding.btnPlaceOrder.setOnClickListener {
                Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        private fun setupRecyclerView() {
            cartAdapter = CartAdapter(cartItems)
            binding.cartRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@CartActivity)
                adapter = cartAdapter
            }
        }

        private fun updateTotalPrice() {
            val total = cartItems.sumOf { it.price * it.quantity }
            binding.tvTotalAmount.text = "Total: ₹%.2f".format(total)
        }

}