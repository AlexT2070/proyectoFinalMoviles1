package com.example.practica06

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica06.R.menu.menu_administrar_productos
import com.example.practica06.adaptador.CarritoManager
import com.example.practica06.adaptador.OnRopaClickListener
import com.example.practica06.adaptador.RopaAdaptador
import com.example.practica06.adaptador.RopaAdaptador_Productos
import com.example.practica06.adaptador.ListaDeseos


class Productos : AppCompatActivity(), OnRopaClickListener {
    private lateinit var buscar: androidx.appcompat.widget.SearchView
    private lateinit var regresoMenu: Button
    private lateinit var adaptador: RopaAdaptador_Productos
    private lateinit var listaRopa: MutableList<Ropa>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_productos)
        // Inicializa la lista
        listaRopa = ListadoRopa.listadoRopaCas //productos
        //Se inicializa el adaptador
        adaptador = RopaAdaptador_Productos(listaRopa, this)
        //Asociación a componentes
        regresoMenu = findViewById(R.id.btnMenu)
        buscar = findViewById(R.id.buscador)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.opciones)
        setSupportActionBar(toolbar)
        inicializarRecycler()

        //Evento para el regreso al Menú
        regresoMenu.setOnClickListener {
            finish()
        }

        // Configuramos el listener para el SearchView
        buscar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
        val recyclerView = findViewById<RecyclerView>(R.id.Recicleproduc)
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
            adaptador.notifyDataSetChanged() // Notificar cambios al adaptador

            // Crea el Intent para abrir el segundo Activity
            val intent = Intent(this, ListaDeseosActivity::class.java)
            // Pasa el objeto Ropa como extra hacia el otro Activity
            intent.putExtra("ropaSeleccionada", ropa)
            // Inicia el segundo Activity
            startActivity(intent)
        }
    }//onButtonWishList

    //Función que elimina de la Lista de Deseos
    override fun onButtonRemoveFromWishList(ropa: Ropa) {
        // Eliminar el producto de la lista
        ListaDeseos.lista.removeIf { it.nombre.equals(ropa.nombre, ignoreCase = true) }
        adaptador.notifyDataSetChanged() // Notificar cambios al adaptador
        Toast.makeText(this, "${ropa.nombre} eliminado de la lista de deseos.", Toast.LENGTH_SHORT).show()
    }

    //Función para enviar elementos al Activity del Carrito (TableLayout)
    override fun onButtonCarrito(ropa: Ropa) {
        //Se agrega el producto al carrito
        ListaDeseos.carrito.add(ropa)
        Toast.makeText(this, "${ropa.nombre} agregado al carrito.", Toast.LENGTH_SHORT).show()
        //Llama a la función del contador (para ir sumando el numero de elementos)
        CarritoManager.agregarProducto(ropa)
        adaptador.notifyDataSetChanged() //Notifica al Adaptador que hubo un cambio en el carrito
    }

}