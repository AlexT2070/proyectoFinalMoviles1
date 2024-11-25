package com.example.practica06

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class IngresoActivity : AppCompatActivity() {
    private lateinit var usuario: Spinner
    private lateinit var edtContrasena: EditText
    private lateinit var btnIngresar: Button
    private lateinit var btnLimpiar: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso)

        edtContrasena = findViewById(R.id.edtContrasena)
        btnIngresar = findViewById(R.id.btnIngresar)
        btnLimpiar = findViewById(R.id.btnLimpiar)


        val usuarios = listOf("Alex", "Juan", "Fernando")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, usuarios)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        usuario.adapter = adapter

        btnIngresar.setOnClickListener {
            ingresar()
        }

        btnLimpiar.setOnClickListener {
            limpiar()
        }
    }

    private fun ingresar() {
        val contrasena = edtContrasena.text.toString()
        if (contrasena == "12345") {
            // Redirigir a la siguiente Activity de Menú
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            // Mostrar un Toast si la contraseña es incorrecta
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun limpiar() {
        // Borrar el contenido de la contraseña
        edtContrasena.text.clear()
        edtContrasena.requestFocus()
    }
}
