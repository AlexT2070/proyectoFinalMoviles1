package com.example.practica06

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica06.adaptador.CarritoManager
import com.example.practica06.adaptador.ListaDeseos

class CarritoActivity : AppCompatActivity() {
    //Instancias a componentes
    private lateinit var tablaCarrito: TableLayout
    private lateinit var comprar: Button
    private lateinit var regresar: Button
    private lateinit var calculoT: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        //Asociación a componentes
        comprar = findViewById(R.id.btnComprar)
        regresar = findViewById(R.id.btnBack)
        calculoT = findViewById(R.id.txtCalculo)
        tablaCarrito = findViewById(R.id.tblCarrito)

        //Inicialzar la tabla con las cabeceras
        inicializarTabla()

        // Llenar el TableLayout con los productos del carrito que se seleccionen
        llenarCarrito()

        //Eventos de los botones después de que se tienen los productos a comprar
        comprar.setOnClickListener {
            Toast.makeText(this, "Compra realizada!", Toast.LENGTH_SHORT).show()
        }
        regresar.setOnClickListener {
            Toast.makeText(this, "Regreso a la ventana del Menú!", Toast.LENGTH_LONG).show()
            finish() //Regreso al menú
        }

    }//onCreate

    // Función para inicializar la tabla con una cabecera
    private fun inicializarTabla() {
        // Crea la fila de encabezados
        val headerRow = TableRow(this).apply {
            setBackgroundColor(Color.parseColor("#FF9800")) // Color de fondo del encabezado
            setPadding(8, 8, 8, 8) // Espaciado interno
        }

        // Agrega las columnas de la cabecera
        headerRow.addView(crearCabecera("Producto", Color.WHITE, Typeface.BOLD))
        headerRow.addView(crearCabecera("Precio", Color.WHITE, Typeface.BOLD))
        headerRow.addView(crearCabecera("Marca", Color.WHITE, Typeface.BOLD))
        headerRow.addView(crearCabecera("Acción", Color.WHITE, Typeface.BOLD))

        // Añade la cabecera a la tabla
        tablaCarrito.addView(headerRow)
    }//inicializarTabla

    // Función para crear un TextView estilizado
    private fun crearCabecera(text: String, textColor: Int, typeface: Int): TextView {
        return TextView(this).apply {
            this.text = text
            this.setTextColor(textColor) // Color del texto
            this.setTypeface(null, typeface) // Estilo de texto (normal, bold, italic)
            this.textSize = 16f // Tamaño del texto
            this.setPadding(16, 8, 16, 8) // Espaciado interno
            this.gravity = Gravity.CENTER // Alineación del texto
            val maxWidthInDp = 80 // Establece un ancho máximo (en píxeles) para la celda
            this.maxWidth = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, maxWidthInDp.toFloat(), resources.displayMetrics
            ).toInt()
            this.setSingleLine(false) // Permite múltiples líneas
            this.ellipsize = null // Deshabilita la elipsis (truncado con "...")
        }
    }//crearCabecera

    //Función para ir llenando el carrito de los productos que va recibiendo de los botones
    // Función para llenar la tabla con los elementos del carrito
    private fun llenarCarrito() {
        // Limpia las filas dinámicas, pero no borra la cabecera
        val childCount = tablaCarrito.childCount
        if (childCount > 1) {
            tablaCarrito.removeViews(1, childCount - 1)
        }

        // Itera sobre los productos en el carrito
        for (ropa in ListaDeseos.carrito) {
            // Crear una nueva fila
            val tableRow = TableRow(this).apply {
                setBackgroundColor(Color.parseColor("#F5F5F5")) // Fondo de las filas
                setPadding(8, 8, 8, 8) // Espaciado interno
            }

            // Crear las celdas para la fila
            val nombreCar = crearCabecera(ropa.nombre, Color.BLACK, Typeface.NORMAL)
            val marcaCar = crearCabecera(ropa.marca, Color.BLACK, Typeface.NORMAL)
            val precioCar = crearCabecera("$${ropa.precio}", Color.BLACK, Typeface.NORMAL)
            val eliminarProd = ImageButton(this).apply {
                setImageResource(R.drawable.baseline_remove_shopping_cart_24) // Cambia `ic_delete` por el nombre de tu ícono
                setBackgroundColor(Color.TRANSPARENT) // Fondo transparente
                setPadding(16, 8, 16, 8) // Espaciado interno
                setOnClickListener {
                    // Elimina el producto del carrito
                    ListaDeseos.carrito.remove(ropa)
                    llenarCarrito() // Actualiza la tabla
                    Toast.makeText(this@CarritoActivity, "${ropa.nombre} eliminado del carrito.", Toast.LENGTH_SHORT).show()

                    // Actualiza el LiveData del carrito (vinculado al badge)
                    CarritoManager.eliminarProducto(ropa)
                }
            }

            // Agregar las celdas a la fila
            tableRow.addView(nombreCar)
            tableRow.addView(marcaCar)
            tableRow.addView(precioCar)
            tableRow.addView(eliminarProd)

            // Agregar la fila al TableLayout
            tablaCarrito.addView(tableRow)
        }

        //Llamada a la función del cálculo del total
        actualizarTotal()
    }//llenarCarrito

    @SuppressLint("SetTextI18n")
    private fun actualizarTotal() {
        // Suma los precios de los productos en el carrito
        val total = ListaDeseos.carrito.sumOf { it.precio }

        // Actualiza el texto del TextView con el total
        calculoT.text = "$${"%.2f".format(total)}"
    }

}//class