package com.example.practica06

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica06.adaptador.RopaAdaptador

class Productos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_productos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
inicializarRecycler()
    }
    private fun inicializarRecycler(){
//Variable para acceder al componente Recycler
        val recyclerView = findViewById<RecyclerView>(R.id.Recicleproduc)
//Administrador del componente
        recyclerView.layoutManager = LinearLayoutManager(this)
//Establecer en el adaptador la lista de productos
        recyclerView.adapter =
            RopaAdaptador(ListadoRopa.listadoRopaCas)
    }//inicializarRecycler
}