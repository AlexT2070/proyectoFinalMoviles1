package com.example.practica06

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {
    private lateinit var usuario: EditText
    private lateinit var contrasena: EditText
    private lateinit var correo: EditText
    private lateinit var numero: EditText
    private lateinit var registrar: Button
    private lateinit var limpiar: Button
    private lateinit var regresar: Button
    private lateinit var notificacion: Switch
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        usuario = findViewById(R.id.edtUsuario)
        contrasena = findViewById(R.id.edtContrasena)
        correo = findViewById(R.id.edtCorreo)
        numero = findViewById(R.id.edtnumero)
        registrar = findViewById(R.id.btnRegistrar)
        limpiar = findViewById(R.id.btnLimpiar)
        regresar = findViewById(R.id.btnRegresar)
        notificacion = findViewById(R.id.swt1)

        preferences = getSharedPreferences("preferenciasUsuario", MODE_PRIVATE)

        registrar.setOnClickListener {
            val nombreUsuario = usuario.text.toString()
            val contrasenaUsuario = contrasena.text.toString()
            val correoUsuario = correo.text.toString()
            val numeroUsuario = numero.text.toString()

            if (nombreUsuario.isNotEmpty() && contrasenaUsuario.isNotEmpty() && correoUsuario.isNotEmpty() && numeroUsuario.isNotEmpty()) {
                guardarUsuario(nombreUsuario, contrasenaUsuario)
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                finish() // Regresa a la pantalla de Login
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        if(notificacion.isChecked){
            Toast.makeText(this, "Notificaciones activadas", Toast.LENGTH_SHORT).show()
        }

        limpiar.setOnClickListener {
            limpiarCampos()
        }

        regresar.setOnClickListener {
            finish()
        }
    }

    private fun guardarUsuario(usuario: String, contrasena: String) {
        val editor = preferences.edit()
        editor.putString("usuario", usuario)
        editor.putString("contrasena", contrasena)
        editor.apply()
    }

    private fun limpiarCampos() {
        usuario.text.clear()
        contrasena.text.clear()
        correo.text.clear()
        numero.text.clear()
        notificacion.isChecked = false
    }
}
