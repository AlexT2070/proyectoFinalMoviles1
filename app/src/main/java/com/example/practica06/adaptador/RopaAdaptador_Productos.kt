package com.example.practica06.adaptador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practica06.R
import com.example.practica06.Ropa

class RopaAdaptador_Productos(private val listaRopa: MutableList<Ropa>, private val listener: OnRopaClickListener):
    RecyclerView.Adapter<RopaVista>() {

    // Lista original de productos (sin cambios)
    private var listaRopaOriginal: MutableList<Ropa> = listaRopa

    // Lista filtrada que se va a mostrar en el RecyclerView
    private var listaRopaMostrar: MutableList<Ropa> = listaRopa

    // Función para filtrar los productos
    fun filtrarProductos(query: String) {
        listaRopaMostrar = if (query.isEmpty()) {
            listaRopaOriginal // Si no hay texto de búsqueda, mostrar todos los productos
        } else {
            // Filtrar productos que coinciden con el nombre o la marca
            listaRopaOriginal.filter {
                it.nombre.contains(query, ignoreCase = true) ||
                        it.marca.contains(query, ignoreCase = true)
            }.toMutableList()
        }
        // Notificar al adaptador que los datos han cambiado
        notifyDataSetChanged()
    }

    // Crear el ViewHolder y asociar la vista del CardView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RopaVista {
        //Define el contexto de la vista para el Recycler
        val layoutInflater = LayoutInflater.from(parent.context)
        //Devuelve la vista del CardView
        return RopaVista(layoutInflater.inflate(R.layout.cardview_productos_login, parent, false))
    }

    // Devolver la cantidad de elementos visibles (filtrados o no)
    override fun getItemCount(): Int = listaRopaMostrar.size

    // Asignar los datos a cada item del RecyclerView
    override fun onBindViewHolder(holder: RopaVista, position: Int) {
        //Indicamos el Accesorio(item) de la lista de Accesorios
        val item = listaRopaMostrar[position] // Usa la lista filtrada
        //Devolver el CardView
        holder.devolver(item)
        holder.configurarEvento(item, listener)
    }

}//Recycler


