package com.example.practica06

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.practica06.R.menu.menu_desplegable
import com.example.practica06.adaptador.CarritoManager
import com.example.practica06.adaptador.ListaDeseos

class MainActivity : AppCompatActivity() {

    private var isAdmin: Boolean = false


    val ropa: Array<Ropa?> = arrayOfNulls(30)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Recibir información del tipo de usuario
        val isAdmin = intent.getBooleanExtra("isAdmin", false)

        // Guardar esta información para uso posterior (si es necesario)
        this.isAdmin = isAdmin
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_desplegable, menu)

        // Ocultar la opción para usuarios normales
        val isAdmin = intent.getBooleanExtra("isAdmin", false)
        if (!isAdmin) {
            menu?.findItem(R.id.itemAdmin)?.isVisible = false
        }

        /*Configuración de un contador sobre el icono de carrito*/

        // Encuentra el ítem del carrito
        val menuItem = menu?.findItem(R.id.itemCarrito)
        // Asigna un diseño personalizado al ítem
        val actionView = layoutInflater.inflate(R.layout.menu_item_badge, null)
        menuItem?.actionView = actionView

        // Encuentra el TextView del contador
        val badgeTextView = actionView.findViewById<TextView>(R.id.badgeTextView)

        // Actualiza el contador dinámicamente
        badgeTextView?.let { observarCarrito(it) }

        // Maneja clics en el ítem personalizado
        actionView.setOnClickListener {
            menuItem?.let { nonNullMenuItem ->
                onOptionsItemSelected(nonNullMenuItem)
            }
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemProductos -> startActivity(Intent(this, Productos::class.java))
            R.id.itemLDeseos -> startActivity(Intent(this, ListaDeseosActivity::class.java))
            R.id.itemComentarios -> startActivity(Intent(this, Comentarios::class.java))
            R.id.itemCarrito -> startActivity(Intent(this, CarritoActivity::class.java))

            R.id.itemAdmin -> {
                if (isAdmin) {
                    startActivity(Intent(this, Editar_Borrar::class.java))
                } else {
                    Toast.makeText(this, "Acceso denegado", Toast.LENGTH_SHORT).show()
                }
            }

            R.id.itemSalir -> {
                val intent = Intent(this, IngresoActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }//onOptionsItemSelected

    //Función para ir actualizando el numero de elementos en el carrito
    private fun observarCarrito(badgeTextView: TextView) {
        CarritoManager.carritoLiveData.observe(this) { carrito ->
            val cantidadProductos = carrito.size
            if (cantidadProductos > 0) {
                badgeTextView.text = cantidadProductos.toString()
                badgeTextView.visibility = View.VISIBLE
            } else {
                badgeTextView.visibility = View.GONE
            }
        }
    }//observarCarrito
}

