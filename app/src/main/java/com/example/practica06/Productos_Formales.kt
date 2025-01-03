package com.example.practica06

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica06.R.menu.menu_administrar_productos
import com.example.practica06.adaptador.CarritoManager
import com.example.practica06.adaptador.OnRopaClickListener
import com.example.practica06.adaptador.RopaAdaptador_Productos
import com.example.practica06.adaptador.ListaDeseos

class Productos_Formales : AppCompatActivity(), OnRopaClickListener {
    //Instancias
    //private lateinit var buscar3: SearchView
    private lateinit var adaptador: RopaAdaptador_Productos
    private lateinit var listaRopa: MutableList<Ropa>
    private lateinit var regresoMenu: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_productos_formales)
        // Inicializa la lista y el adaptador
        listaRopa = ListadoRopa.listadoRopaForm
        adaptador = RopaAdaptador_Productos(listaRopa, this)
        val buscar3 = findViewById<androidx.appcompat.widget.SearchView>(R.id.search3)
        //Asociación al botón
        regresoMenu = findViewById(R.id.btnMenu2)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.opciones2)
        setSupportActionBar(toolbar)
        //Llamada a la inicialización del Recycler
        inicializarRecycler()

        //Evento para el regreso al Menú
        regresoMenu.setOnClickListener {
            finish()
        }

        // Configuramos el listener para el SearchView
        buscar3.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // No se requiere acción aquí, pero puede hacerse algo cuando se envía la consulta (ej., mostrar un mensaje de búsqueda)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Filtra la lista de productos a medida que el texto cambia
                newText?.let {
                    filterProducts(it)
                }
                return true
            }

            // Méthod para filtrar la lista de productos
            private fun filterProducts(query: String) {
                adaptador.filtrarProductos(query)
            }
        })//setOnQueryTextListener

    }//onCreate

    private fun inicializarRecycler(){
//Variable para acceder al componente Recycler
        val recyclerView = findViewById<RecyclerView>(R.id.recile2)
//Administrador del componente
        recyclerView.layoutManager = LinearLayoutManager(this)
//Establecer en el adaptador la lista de productos
        recyclerView.adapter = adaptador
    }
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

    //Función del botón que envía a la Lista de Deseos
    override fun onButtonWishList(ropa: Ropa) {
        // Validar si el producto ya existe en la lista
        val existe = ListaDeseos.lista.any { it.nombre.equals(ropa.nombre, ignoreCase = true) }
        if (existe) {
            // Mostrar mensaje si el producto ya está en la lista
            Toast.makeText(this, "${ropa.nombre} ya está en la lista de deseos.", Toast.LENGTH_SHORT).show()
        } else {
            // Agregar el producto a la lista si no existe
            ListaDeseos.lista.add(ropa)

            // Mostrar mensaje de éxito
            Toast.makeText(this, "${ropa.nombre} agregado a la lista de deseos.", Toast.LENGTH_SHORT).show()

            // Crea el Intent para abrir el segundo Activity
            val intent = Intent(this, ListaDeseosActivity::class.java)

            // Pasa el objeto Ropa como extra hacia el otro Activity
            intent.putExtra("ropaSeleccionada", ropa)

            // Inicia el segundo Activity
            startActivity(intent)
        }
    }

    override fun onButtonRemoveFromWishList(ropa: Ropa) {
        // Eliminar el producto de la lista
        ListaDeseos.lista.removeIf { it.nombre.equals(ropa.nombre, ignoreCase = true) }
        adaptador.notifyDataSetChanged() // Notificar cambios al adaptador
        Toast.makeText(this, "${ropa.nombre} eliminado de la lista de deseos.", Toast.LENGTH_SHORT).show()
    }

    override fun onButtonCarrito(ropa: Ropa) {
        ListaDeseos.carrito.add(ropa)
        Toast.makeText(this, "${ropa.nombre} agregado al carrito.", Toast.LENGTH_SHORT).show()
        //Llama a la función del contador (para ir sumando el numero de elementos)
        CarritoManager.agregarProducto(ropa)
        adaptador.notifyDataSetChanged() //Notifica al Adaptador que hubo un cambio en el carrito
    }

}