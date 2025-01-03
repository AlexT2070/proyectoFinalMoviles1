package com.example.practica06.adaptador

import android.view.View
import android.widget.Button
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
    private val btnWishList = view.findViewById<Button>(R.id.btnDeseado) // Tu botón en el CardView
    private val btnCarrito = view.findViewById<Button>(R.id.btnComprar)

    //Se ejecutará por cada producto del RecyclerView
    fun devolver(ropaModelo: Ropa) {
        nombre.text = ropaModelo.nombre
        marca.text = ropaModelo.marca
        modelo.text = ropaModelo.modelo
        precio.text = ropaModelo.precio.toString()
        when (ropaModelo.imagen) {
            1 -> imagen.setImageResource(R.drawable.casual01)
            2 -> imagen.setImageResource(R.drawable.casual02)
            3 -> imagen.setImageResource(R.drawable.casual03)
            4 -> imagen.setImageResource(R.drawable.casual04)
            5 -> imagen.setImageResource(R.drawable.formal01)
            6 -> imagen.setImageResource(R.drawable.formal02)
            7 -> imagen.setImageResource(R.drawable.formal03)
            8 -> imagen.setImageResource(R.drawable.formal04)
            9 -> imagen.setImageResource(R.drawable.deportiva01)
            10 -> imagen.setImageResource(R.drawable.deportiva02)
            11 -> imagen.setImageResource(R.drawable.deportiva03)
            12 -> imagen.setImageResource(R.drawable.deportiva04)
            else -> imagen.setImageResource(R.drawable.casual01)
        }//when

    }//devolver

    //Metódo para llamar a la interfaz OnRopaClickListener
    fun configurarEvento(ropa: Ropa, listener: OnRopaClickListener) {
        // Determina si el producto ya está en la lista de deseos
        val enLista = ListaDeseos.lista.any { it.nombre.equals(ropa.nombre, ignoreCase = true) }
        btnWishList.text = if (enLista) "Eliminar elemento" else "Marcar Deseado"

        // Configura el listener del botón
        btnWishList.setOnClickListener {
            if (enLista) {
                // Si el producto está en la lista, llama al méthod para eliminar
                listener.onButtonRemoveFromWishList(ropa)
            } else {
                // Si no está en la lista, llama al méthod para agregar
                listener.onButtonWishList(ropa)
            }
        }
    }//configurarEvento-lista

    fun configurarEventoCarrito(ropa: Ropa, listener: OnRopaClickListener) {
        btnCarrito.setOnClickListener {
            listener.onButtonCarrito(ropa) // Llama al method para agregar al carrito
        }
    }

}//class