package com.example.practica06

import Paciente
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConsultaActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta)

        val info= findViewById<TextView>(R.id.txtInfo)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)

        // Obtener el arreglo de objetos desde el Intent
        val pacientes: ArrayList<Paciente>? = intent.getSerializableExtra("pacientes") as? ArrayList<Paciente>

        // Verificar que el arreglo no esté vacío y mostrar la información
        if (!pacientes.isNullOrEmpty()) {
            val informacion = StringBuilder()
            for (paciente in pacientes) {
                informacion.append("Nombre: ${paciente.nombre}\n")
                informacion.append("Domicilio: ${paciente.domicilio}\n")
                informacion.append("Correo: ${paciente.correo}\n")
                informacion.append("Teléfono: ${paciente.telefono}\n\n")
            }
            info.text = informacion.toString()
        } else {
            info.text = "No hay información disponible."
        }

        // Acción del botón Regresar
        btnRegresar.setOnClickListener {
            finish() // Regresa al menú principal
        }
    }
}
