package com.example.practica06

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica06.ListadoRopa
import com.example.practica06.R.menu.menu_administrar_productos

class Editar_Borrar : AppCompatActivity() {
    //Instancias a componentes gráficos
    private lateinit var Borrar: Button
    private lateinit var Spin: Spinner
    private lateinit var edit:Button
    private lateinit var Nombre:EditText
    private lateinit var preci:EditText
    private lateinit var Modelo:EditText
    private lateinit var marca:EditText


    @SuppressLint("MissingInflatedId")
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
        edit = findViewById(R.id.btnActualizar)
        Borrar = findViewById(R.id.btnBorrar)
        Spin = findViewById(R.id.spinRopa)
        Nombre = findViewById(R.id.edtNombre)
        preci = findViewById(R.id.edtPrecio)
        Modelo = findViewById(R.id.edtModelo)
        marca = findViewById(R.id.edtMarca)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.tbocategoria)
        setSupportActionBar(toolbar)



       val listado =ListadoRopa.listadoRopaCas
        val adaptadorRopa = ArrayAdapter(this, android.R.layout.simple_spinner_item, listado.map { it.nombre })
        adaptadorRopa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        // Adaptador para el Spinner
// Asignar el adaptador al Spinner
        Spin.adapter = adaptadorRopa

        Spin.setOnItemSelectedListener(object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                // Obtener el objeto Ropa correspondiente a la opción seleccionada
                val ropaSeleccionada = listado[position]
                Nombre.setText(ropaSeleccionada.nombre)
                preci.setText(ropaSeleccionada.precio.toString())
                Modelo.setText(ropaSeleccionada.modelo)
                marca.setText(ropaSeleccionada.marca)

            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>) {
                // No hacer nada si no hay selección
            }
        })
        Borrar.setOnClickListener() {

            val posicionSeleccionada = Spin.selectedItemPosition
            if (posicionSeleccionada >= 0) {
                listado.removeAt(posicionSeleccionada)  // Elimina el elemento del listado
                adaptadorRopa.clear()  // Limpiar adaptador actual
                adaptadorRopa.addAll(listado.map { it.nombre })  // Actualizar adaptador con elementos restantes
                adaptadorRopa.notifyDataSetChanged()  // Notificar al adaptador de los cambios
                Nombre.setText("")
                preci.setText("")
                Modelo.setText("")
                marca.setText("")
                Toast.makeText(this, "Elemento eliminado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No hay selección para eliminar", Toast.LENGTH_SHORT).show()
            }
            }
        edit.setOnClickListener {
            val posicionSeleccionada = Spin.selectedItemPosition
            if (posicionSeleccionada >= 0) {
                try {
                    // Obtener nuevos valores de los campos
                    val nuevoNombre = Nombre.text.toString()
                    val nuevoPrecio = preci.text.toString().toDouble()
                    val nuevoModelo = Modelo.text.toString()
                    val nuevaMarca = marca.text.toString()

                    // Actualizar el objeto en la lista
                    val ropa = listado[posicionSeleccionada]
                    ropa.nombre = nuevoNombre
                    ropa.precio = nuevoPrecio
                    ropa.modelo = nuevoModelo
                    ropa.marca = nuevaMarca

                    // Actualizar adaptador del Spinner
                    adaptadorRopa.clear()
                    adaptadorRopa.addAll(listado.map { it.nombre })
                    adaptadorRopa.notifyDataSetChanged()

                    // Notificar al usuario que la edición fue exitosa
                    Toast.makeText(this, "Elemento modificado con éxito", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(this, "Error al modificar: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No hay selección para modificar", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_administrar_productos, menu)
        return true
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itmRopaCas -> {
                val intent = Intent(applicationContext, Editar_Borrar::class.java)
                startActivity(intent)
            }
            R.id.itmRopaForm -> {
                val intent = Intent(applicationContext, Editar_Borrar_Formal::class.java)
                startActivity(intent)
            }
            R.id.itmRopaDep -> {
                val intent = Intent(applicationContext, Editar_Borrar_Deportiva::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)

    }//onOptionsItemSelected
}