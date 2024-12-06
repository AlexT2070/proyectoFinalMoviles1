package com.example.practica06

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica06.adaptador.OnRopaClickListener
import com.example.practica06.adaptador.RopaAdaptador_Productos
import com.example.practica06.adaptador.ListaDeseos

class ListaDeseosActivity : AppCompatActivity() {
    private lateinit var adaptador: RopaAdaptador_Productos
    private val listaRopa = ListaDeseos.lista // Apunta directamente al Singleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_deseos)
        // Recibe el objeto Ropa enviado
        val ropaSeleccionada = intent.getSerializableExtra("ropaSeleccionada") as? Ropa
        //Validamos que no llegue el objeto nulo
        if (ropaSeleccionada != null) {
            // Verifica si el producto ya está en la lista global
            val existe = ListaDeseos.lista.any { it.nombre.equals(ropaSeleccionada.nombre, ignoreCase = true) }
            if (!existe) {
                ListaDeseos.lista.add(ropaSeleccionada) // Añade el objeto a la lista global
            }
        }
        //Inicializar el RecyclerView
        inicializarRecycler()

    }
    private fun inicializarRecycler() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvElementos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Aquí estamos usando la lista del Singleton
        adaptador = RopaAdaptador_Productos(ListaDeseos.lista, object : OnRopaClickListener {
            override fun onButtonWishList(ropa: Ropa) {
                //Validar si el producto ya existe en la lista
                val existe = ListaDeseos.lista.any { it.nombre.equals(ropa.nombre, ignoreCase = true) }
                if (existe) {
                    // Mostrar mensaje si el producto ya está en la lista
                    Toast.makeText(this@ListaDeseosActivity, "${ropa.nombre} ya está en la lista de deseos.", Toast.LENGTH_SHORT).show()
                } else {
                    // Agregar el producto a la lista si no existe
                    ListaDeseos.lista.add(ropa)

                    // Notificar al adaptador que se insertó un nuevo elemento
                    adaptador?.notifyItemInserted(ListaDeseos.lista.size - 1)

                    // Mostrar mensaje de éxito
                    Toast.makeText(this@ListaDeseosActivity, "${ropa.nombre} agregado a la lista de deseos.", Toast.LENGTH_SHORT).show()
                }
            }
        })

        // Establecemos el adaptador con la lista del Singleton
        recyclerView.adapter = adaptador
    }
}