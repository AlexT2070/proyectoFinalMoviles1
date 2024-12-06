package com.example.practica06

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica06.adaptador.OnRopaClickListener
import com.example.practica06.adaptador.RopaAdaptador
import com.example.practica06.adaptador.RopaAdaptador_Productos
import com.example.practica06.adaptador.Singleton

class ListaDeseosActivity : AppCompatActivity() {
    private lateinit var adaptador: RopaAdaptador_Productos
    private val listaRopa = mutableListOf<Ropa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_deseos)
        // Recibe el objeto Ropa enviado
        val ropaSeleccionada = intent.getSerializableExtra("ropaSeleccionada") as? Ropa
        if (ropaSeleccionada != null) {
            listaRopa.add(ropaSeleccionada) // Añade el objeto a la lista
        }
        //Inicializar el RecyclerView
        inicializarRecycler()

    }
    private fun inicializarRecycler() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvElementos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Aquí estamos usando la lista del Singleton
        adaptador = RopaAdaptador_Productos(Singleton.lista, object : OnRopaClickListener {
            override fun onButtonWishList(ropa: Ropa) {
                // Aquí puedes agregar el producto a la lista del Singleton
                Singleton.lista.add(ropa)

                // Notificamos al adaptador que se agregó un nuevo objeto dentro de la lista
                adaptador?.notifyItemInserted(Singleton.lista.size - 1)
            }
        })

        // Establecemos el adaptador con la lista del Singleton
        recyclerView.adapter = adaptador
    }
}