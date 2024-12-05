package com.example.practica06.adaptador

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practica06.R
import com.example.practica06.Ropa

class RopaVista(view: View): RecyclerView.ViewHolder(view) {
    //Variables para acceder a los atributos del Cardview
    private val nombre = view.findViewById<TextView>(R.id.txtName)
    private val marca = view.findViewById<TextView>(R.id.txtBrand)
    private val modelo = view.findViewById<TextView>(R.id.txtModel)
    private val precio = view.findViewById<TextView>(R.id.txtPrice)
    private val imagen = view.findViewById<ImageView>(R.id.imgProd)

    //Se ejecutará por cada producto del RecyclerView
    fun devolver(ropaModelo: Ropa){
        nombre.text = ropaModelo.nombre
        marca.text = ropaModelo.marca
        modelo.text = ropaModelo.modelo
        precio.text = ropaModelo.precio.toString()
        when(ropaModelo.imagen){
            1 -> imagen.setImageResource(R.drawable.casual01)
            2 -> imagen.setImageResource(R.drawable.casual02)
            3 -> imagen.setImageResource(R.drawable.casual03)
            4 -> imagen.setImageResource(R.drawable.casual04)
            else -> imagen.setImageResource(R.drawable.casual01)
        }//when

    }//devolver
}