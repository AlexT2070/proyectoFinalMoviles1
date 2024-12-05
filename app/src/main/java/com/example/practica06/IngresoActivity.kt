package com.example.practica06

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class IngresoActivity : AppCompatActivity() {
    private lateinit var usuario: EditText
    private lateinit var contrasena: EditText
    private lateinit var ingresar: Button
    private lateinit var limpiar: Button
    private lateinit var invitado: Button
    private lateinit var registrarse: Button
    private lateinit var guardarse: Switch
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingreso)

        usuario = findViewById(R.id.edtUsuario)
        contrasena = findViewById(R.id.edtContrasena)
        ingresar = findViewById(R.id.btnIngresar)
        limpiar = findViewById(R.id.btnLimpiar)
        invitado = findViewById(R.id.btnInvitado)
        registrarse = findViewById(R.id.btnRegistrarse)
        guardarse = findViewById(R.id.swGuardado)

        preferences = getSharedPreferences("preferenciasUsuario", MODE_PRIVATE)

        ingresar.setOnClickListener {
            ingresar()
        }

        limpiar.setOnClickListener {
            limpiarCampos()
        }

        invitado.setOnClickListener {
            val intent = Intent(this, InvitadoActivity::class.java)
            startActivity(intent)
        }

        registrarse.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }

    private fun ingresar() {
        val usuarioIngresado = usuario.text.toString()
        val contrasenaIngresada = contrasena.text.toString()

        if (usuarioIngresado.isEmpty() || contrasenaIngresada.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (usuarioIngresado == "admin" && contrasenaIngresada == "12345") {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("isAdmin", true) // Usuario es administrador
            startActivity(intent)
        } else if (esUsuarioValido(usuarioIngresado, contrasenaIngresada)) {
            if (guardarse.isChecked) {
                guardarSesion(usuarioIngresado, contrasenaIngresada)
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun esUsuarioValido(usuario: String, contrasena: String): Boolean {
        val usuarioGuardado = preferences.getString("usuario_$usuario", null)
        val contrasenaGuardada = preferences.getString("contrasena_$usuario", null)
        return usuarioGuardado == usuario && contrasenaGuardada == contrasena
    }

    private fun guardarSesion(usuario: String, contrasena: String) {
        val editor = preferences.edit()
        editor.putString("lastUsuario", usuario)
        editor.putString("lastContrasena", contrasena)
        editor.apply()
    }

    private fun limpiarCampos() {
        usuario.text.clear()
        contrasena.text.clear()
        guardarse.isChecked = false
    }
}
