package com.example.hotwheelspizza
object PizzaData {
    val pizzaList = mutableListOf(

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

    val cartList: List<Pizza>
        get() = pizzaList.filter { it.quantity > 0 }
}
