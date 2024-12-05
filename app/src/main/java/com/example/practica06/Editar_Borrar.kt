package com.example.practica06

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Editar_Borrar : AppCompatActivity() {
    //Instancias a componentes gráficos
    private lateinit var Borrar: Button
    private lateinit var Spin: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_borrar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Asociación
        Borrar = findViewById(R.id.btnBorrar)
        Spin = findViewById(R.id.spinRopa)
        //Vinculación del Spinner
        val listado = ListadoRopa.listadoRopaCas
        val adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, listado.map { it.nombre })
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        Borrar.setOnClickListener() {

            //listado.removeIf { it.nombre == "Sudadera Casual de Moda" }

        }
    }
}