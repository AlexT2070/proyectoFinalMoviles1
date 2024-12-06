package com.example.practica06

import android.os.Bundle
import android.widget.Button
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CarritoActivity : AppCompatActivity() {
    private lateinit var comprar: Button
    private lateinit var regresar: Button
    private lateinit var calculoT: TextView
    private lateinit var fila1: TableRow
    private lateinit var nombre1: TextView
    private lateinit var marca1: TextView
    private lateinit var precio1: TextView
    private lateinit var eliminar1: Button
    private lateinit var fila2: TableRow
    private lateinit var nombre2: TextView
    private lateinit var marca2: TextView
    private lateinit var precio2: TextView
    private lateinit var eliminar2: Button
    private lateinit var fila3: TableRow
    private lateinit var nombre3: TextView
    private lateinit var marca3: TextView
    private lateinit var precio3: TextView
    private lateinit var eliminar3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        comprar = findViewById(R.id.btnComprar)
        regresar = findViewById(R.id.btnBack)
        calculoT = findViewById(R.id.txtCalculo)
        eliminar1= findViewById(R.id.btnEliminarF1)

        /*
        fila1 = findViewById(R.id.Fila1)
        nombre1= findViewById(R.id.NombreF1)
        marca1= findViewById(R.id.MarcaF1)
        precio1= findViewById(R.id.PrecioF1)
        eliminar1= findViewById(R.id.btnEliminarF1)
        fila2 = findViewById(R.id.Fila2)
        nombre2= findViewById(R.id.NombreF2)
        marca2= findViewById(R.id.MarcaF2)
        precio2= findViewById(R.id.PrecioF2)
        eliminar2= findViewById(R.id.btnEliminarF2)
        fila3 = findViewById(R.id.Fila3)
        nombre3= findViewById(R.id.NombreF3)
        marca3= findViewById(R.id.MarcaF3)
        precio3= findViewById(R.id.PrecioF3)
        eliminar3= findViewById(R.id.btnEliminarF3)*/

        comprar.setOnClickListener{

            Toast.makeText(this, "Compra realizada", Toast.LENGTH_SHORT).show()

        }
    }
}