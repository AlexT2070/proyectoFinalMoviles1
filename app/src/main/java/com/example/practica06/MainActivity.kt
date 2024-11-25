package com.example.practica06

import Paciente
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.practica06.R.menu.menu_desplegable

class MainActivity : AppCompatActivity() {

    // Definimos el arreglo de pacientes
    private val pacientes = arrayListOf<Paciente>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar el Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar) // Establecer el Toolbar como ActionBar

        // Otras inicializaciones si son necesarias
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_desplegable, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemRegistrar -> startActivity(Intent(this, RegistroActivity::class.java))
            R.id.itemConsultar -> {
                // Crear el Intent para ConsultaActivity y pasar el arreglo de pacientes
                val intent = Intent(this, ConsultaActivity::class.java)
                intent.putExtra("pacientes", pacientes)
                startActivity(intent)
            }
            R.id.itemSalir -> {
                val intent = Intent(this, IngresoActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
