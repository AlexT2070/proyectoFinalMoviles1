package com.example.practica06.adaptador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practica06.R
import com.example.practica06.Ropa

class RopaAdaptador_Productos(private val listaRopa: List<Ropa>):
    RecyclerView.Adapter<RopaVista>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RopaVista {
        //Define el contexto de la vista para el Recycler
        val layoutInflater = LayoutInflater.from(parent.context)
        //Devuelve la vista del CardView
        return RopaVista(layoutInflater.inflate(R.layout.cardview_productos_login, parent, false))
    }

    //Devolver la cantidad de elementos de la lista de Accesorios
    override fun getItemCount(): Int = listaRopa.size

    override fun onBindViewHolder(holder: RopaVista, position: Int) {
        //Indicamos el Accesorio(item) de la lista de Accesorios
        val item = listaRopa[position]
        //Devolver el CardView
        holder.devolver(item)
    }

}//Recycler


