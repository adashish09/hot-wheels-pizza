package com.example.hotwheelspizza

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.hotwheelspizza.databinding.ItemPizzaBinding

class PizzaAdapter(
    private val pizzaList: List<Pizza>,
    private val onCartUpdated: ((Pizza) -> Unit)? = null,
    private val isCartView: Boolean = false
) : RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder>() {

    inner class PizzaViewHolder(val binding: ItemPizzaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val binding = ItemPizzaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PizzaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        val pizza = pizzaList[position]
        val b = holder.binding

        b.pizzaName.text = pizza.name
        b.pizzaPrice.text = "₹${pizza.price}"
        b.pizzaImage.setImageResource(pizza.imageResId)

        if (isCartView) {
            // Cart mode - no buttons
            b.addToCartButton.isVisible = false
            b.quantityLayout.isVisible = true
            b.btnPlus.isVisible = false
            b.btnMinus.isVisible = false
            b.tvQuantity.text = pizza.quantity.toString()
        } else {
            // Home page logic - show add button or +/- buttons
            if (pizza.quantity == 0) {
                b.addToCartButton.isVisible = true
                b.quantityLayout.isVisible = false
            } else {
                b.addToCartButton.isVisible = false
                b.quantityLayout.isVisible = true
                b.btnPlus.isVisible = true
                b.btnMinus.isVisible = true
                b.tvQuantity.text = pizza.quantity.toString()
            }

            b.addToCartButton.setOnClickListener {
                pizza.quantity = 1
                notifyItemChanged(position)
                onCartUpdated?.invoke(pizza)
            }

            b.btnPlus.setOnClickListener {
                pizza.quantity++
                b.tvQuantity.text = pizza.quantity.toString()
                onCartUpdated?.invoke(pizza)
            }

            b.btnMinus.setOnClickListener {
                if (pizza.quantity > 1) {
                    pizza.quantity--
                    b.tvQuantity.text = pizza.quantity.toString()
                } else {
                    pizza.quantity = 0
                    notifyItemChanged(position)
                }
                onCartUpdated?.invoke(pizza)
            }
        }

    }

    override fun getItemCount(): Int = pizzaList.size
}
