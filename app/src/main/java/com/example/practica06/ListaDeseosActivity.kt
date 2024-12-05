package com.example.practica06

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica06.adaptador.RopaAdaptador

class ListaDeseosActivity : AppCompatActivity() {
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_deseos)
        //Inicializar el RecyclerView
        inicializarRecycler()



    }
    private fun inicializarRecycler() {
        //Variable para acceder al componente Recycler
        recycler = findViewById<RecyclerView>(R.id.rvElementos)
        //Administrador del componente
        recycler.layoutManager = LinearLayoutManager(this)
        //Establecer en el adaptador la lista de productos
        recycler.adapter = RopaAdaptador(ListadoRopa.listadoRopaCas)
    }//inicializarRecycler
}