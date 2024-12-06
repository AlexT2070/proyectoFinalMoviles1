package com.example.practica06

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica06.R.menu.menu_administrar_productos
import com.example.practica06.adaptador.OnRopaClickListener
import com.example.practica06.adaptador.RopaAdaptador_Productos
import com.example.practica06.adaptador.ListaDeseos

class Productos_Deportivos : AppCompatActivity(), OnRopaClickListener {
    private lateinit var adaptador: RopaAdaptador_Productos
    private lateinit var listaRopa: MutableList<Ropa>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_productos_deportivos)
        // Inicializa la lista y el adaptador
        listaRopa = ListadoRopa.listadoRopaDep
        adaptador = RopaAdaptador_Productos(listaRopa, this)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.opciones3)
        setSupportActionBar(toolbar)

        inicializarRecycler()

    }
    private fun inicializarRecycler(){
//Variable para acceder al componente Recycler
        val recyclerView = findViewById<RecyclerView>(R.id.recicle3)
//Administrador del componente
        recyclerView.layoutManager = LinearLayoutManager(this)
//Establecer en el adaptador la lista de productos
        recyclerView.adapter = adaptador
    }//inicializarRecycler
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_administrar_productos, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itmRopaCas -> {
                val intent = Intent(applicationContext, Productos::class.java)
                startActivity(intent)
            }
            R.id.itmRopaForm -> {
                val intent = Intent(applicationContext, Productos_Formales::class.java)
                startActivity(intent)
            }
            R.id.itmRopaDep -> {
                val intent = Intent(applicationContext, Productos_Deportivos::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)

    }//onOptionsItemSelected

    override fun onButtonWishList(ropa: Ropa) {
        // Aquí manejas el evento del botón
        Toast.makeText(this, "Clic en: ${ropa.nombre}", Toast.LENGTH_SHORT).show()
        // Agrega el producto a la lista
        ListaDeseos.lista.add(ropa)
        // Crea el Intent para abrir el segundo Activity
        val intent = Intent(this, ListaDeseosActivity::class.java)

        // Pasa el objeto Ropa como extra hacia el otro Activity
        intent.putExtra("ropaSeleccionada", ropa)

        // Inicia el segundo Activity
        startActivity(intent)
    }
}