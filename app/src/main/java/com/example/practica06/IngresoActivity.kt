package com.example.practica06

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
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




        ingresar.setOnClickListener {
            ingresar()
        }

        limpiar.setOnClickListener {
            limpiar()
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
      if (usuario.text.toString().isEmpty() && usuario.text.toString().isNotBlank() &&
          contrasena.text.toString().isEmpty() && contrasena.text.toString().isNotBlank()) {
          val usr= Usuario(usuario.text.toString(), contrasena.text.toString(), guardarse = false)
          if(guardarse.isChecked){
              guardarPreferencias(usr)
          }

          val intent = Intent(this, MainActivity::class.java)
          startActivity(intent)
      }else
          Toast.makeText(this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show()
    }

    fun guardarPreferencias(usr: Usuario) {
        val preferences = getSharedPreferences("preferenciasUsuario", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString("usuario", usr.usuario)
        editor.putString("contrasena", usr.contrasena)
        editor.putBoolean("guardados", usr.guardarse)
        editor.apply()
    }
    private fun limpiar() {
        usuario.text.clear()
        usuario.requestFocus()
        contrasena.text.clear()
        contrasena.requestFocus()
        guardarse.isChecked = false
    }
}
