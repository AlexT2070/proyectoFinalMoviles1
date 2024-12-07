package com.example.practica06

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica06.adaptador.CarritoManager
import com.example.practica06.adaptador.OnRopaClickListener
import com.example.practica06.adaptador.RopaAdaptador_Productos
import com.example.practica06.adaptador.ListaDeseos

class ListaDeseosActivity : AppCompatActivity() {
    private lateinit var adaptador: RopaAdaptador_Productos

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
            //Llamado a los métodos de la clase de los Productos
            override fun onButtonWishList(ropa: Ropa) {
                onButtonWishList(ropa)
            }
            override fun onButtonRemoveFromWishList(ropa: Ropa) {
                // Eliminar el producto de la lista
                ListaDeseos.lista.removeIf { it.nombre.equals(ropa.nombre, ignoreCase = true) }
                adaptador.notifyDataSetChanged() // Notificar cambios al adaptador
                Toast.makeText(this@ListaDeseosActivity, "${ropa.nombre} eliminado de la lista de deseos.", Toast.LENGTH_SHORT).show()
            }
            override fun onButtonCarrito(ropa: Ropa) {
                ListaDeseos.carrito.add(ropa)
                Toast.makeText(this@ListaDeseosActivity, "${ropa.nombre} agregado al carrito.", Toast.LENGTH_SHORT).show()
                adaptador.notifyDataSetChanged() //Notifica al Adaptador que hubo un cambio en el carrito
                //Llama a la función del contador (para ir sumando el numero de elementos)
                CarritoManager.agregarProducto(ropa)
                adaptador.notifyDataSetChanged() //Notifica al Adaptador que hubo un cambio en el carrito
            }
        })

        // Establecemos el adaptador con la lista del Singleton
        recyclerView.adapter = adaptador
    }
}