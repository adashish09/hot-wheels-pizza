package com.example.hotwheelspizza

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotwheelspizza.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
        private lateinit var binding: ActivityMainBinding
        private lateinit var pizzaAdapter: PizzaAdapter

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            setupPizzaList()
            setupRecyclerView()

            binding.topAppBar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.cart -> {
                        val intent = Intent(this, CartActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
        }

        private fun setupPizzaList() {
            PizzaData.pizzaList.clear()
            PizzaData.pizzaList.addAll(
                listOf(
                    Pizza("Margherita", 249.0, R.drawable.pizza_m),
                    Pizza("Farmhouse", 299.0, R.drawable.pizza_f),
                    Pizza("Pepperoni", 319.0, R.drawable.pizza_p),
                    Pizza("Veggie Deluxe", 279.0, R.drawable.pizza_vd),
                    Pizza("Cheese Corn", 279.0, R.drawable.pizza_cc),
                    Pizza("Tandoori Paneer", 339.0, R.drawable.pizza_tp),
                    Pizza("Veggie Delight", 229.0, R.drawable.pizza_vdt),
                    Pizza("Cheese Burst", 269.0, R.drawable.pizza_cb),
                    Pizza("Paneer Tikka", 259.0, R.drawable.pizza_pt)
                )
            )
        }


    private fun setupRecyclerView() {
//        pizzaAdapter = PizzaAdapter(PizzaData.pizzaList) { updatedPizza: Pizza ->
//            Toast.makeText(this, "${updatedPizza.name} updated in cart", Toast.LENGTH_SHORT).show()
//        }

        pizzaAdapter = PizzaAdapter(
            pizzaList = PizzaData.pizzaList,
            onCartUpdated = { updatedPizza ->
                Toast.makeText(this, "${updatedPizza.name} updated in cart", Toast.LENGTH_SHORT).show()
            }
        )

        binding.pizzaRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = pizzaAdapter
        }
    }



}