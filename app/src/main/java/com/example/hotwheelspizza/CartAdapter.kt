package com.example.hotwheelspizza

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotwheelspizza.databinding.ItemCartBinding

class CartAdapter(private val cartList: List<Pizza>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = cartList[position]
        holder.binding.cartItemName.text = item.name
        holder.binding.cartItemQty.text = "x${item.quantity}"
        val subtotal = item.price * item.quantity
        holder.binding.cartItemPrice.text = "₹%.2f".format(subtotal)
    }

    override fun getItemCount(): Int = cartList.size
}
