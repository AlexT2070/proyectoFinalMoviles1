package com.example.practica06

import Paciente
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {
    private lateinit var Nombre: EditText
    private lateinit var Contrasena: EditText
    private lateinit var Correo: EditText
    private lateinit var Telefono: EditText
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var notificaciones: Switch

    private val pacientes = mutableListOf<Paciente>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        Nombre = findViewById(R.id.edtNombre)
        Contrasena = findViewById(R.id.edtPassword)
        Correo = findViewById(R.id.edtCorreo)
        Telefono = findViewById(R.id.edtNumeroTel)
        notificaciones = findViewById(R.id.swNotificaciones)

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)

        // Datos para el ListView de correos
        val correos = arrayOf("alex22@gmail.com", "juan22@gmail.com", "fernando22@gmail.com")
        val adapterCorreos = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, correos)
        correo.adapter = adapterCorreos
        correo.choiceMode = ListView.CHOICE_MODE_SINGLE

        btnRegistrar.setOnClickListener {
            val nombre = edtNombre.text.toString()
            val domicilio = edtDomicilio.text.toString()
            val correoSeleccionadoIndex = correo.checkedItemPosition

            // Validaciones
            if (nombre.isEmpty() || domicilio.isEmpty() || correoSeleccionadoIndex == ListView.INVALID_POSITION) {
                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Obtener el correo seleccionado
            val correoSeleccionado = correos[correoSeleccionadoIndex]

            // Obtener los teléfonos seleccionados
            val telefono1 = if (telefonos1.isChecked) "123456789" else null
            val telefono2 = if (telefonos2.isChecked) "987654321" else null
            val telefono3 = if (telefonos3.isChecked) "555555555" else null

            // Crear y agregar paciente al arreglo
            val paciente = Paciente(nombre, domicilio, correoSeleccionado, telefono = telefono1 ?: telefono2 ?: telefono3 ?: "Sin teléfono")
            pacientes.add(paciente)

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

            // Limpiar campos
            limpiarCampos()

            // Ir a ConsultaActivity y pasar el arreglo de pacientes
            val intent = Intent(this, ConsultaActivity::class.java)
            intent.putExtra("pacientes", ArrayList(pacientes))
            startActivity(intent)
        }

        btnLimpiar.setOnClickListener {
            limpiarCampos()
        }

        btnRegresar.setOnClickListener {
            finish() // Regresa a la actividad anterior
        }
    }

    private fun limpiarCampos() {
        edtNombre.text.clear()
        edtDomicilio.text.clear()
        correo.clearChoices()
        telefonos1.isChecked = false
        telefonos2.isChecked = false
        telefonos3.isChecked = false
    }
}
