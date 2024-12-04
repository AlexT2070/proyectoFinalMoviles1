package com.example.practica06

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

//Clase que va a recibir los datos del Activity Invitado y los va a mostrar
class ConsultaActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta)

        //Instanciamiento y Asociación de componentes
        val info= findViewById<TextView>(R.id.txtInfo)
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)
        val imgRopa = findViewById<ImageView>(R.id.imgProducto)


        // Recibir la información del activity anterior (invitado)
        val infoRecibida = intent.extras

        //Variables que van a recibir los datos
        val nombreRec: String?
        val marcaRec: String?
        val modeloRec: String?
        val precioRec: Double?
        val num: Int?

        //Validar si se recibe la información
        if(infoRecibida != null){
            //Recibimos los datos de la ropa
            nombreRec = infoRecibida.getString("nombre")
            marcaRec = infoRecibida.getString("marca")
            modeloRec = infoRecibida.getString("modelo")
            precioRec = infoRecibida.getDouble("precio")
            num = infoRecibida.getInt("numRopa")

            //Insertamos información en el TextView
            info.text = "<Detalles de el artículo de ropa>\n\n"+"Nombre: ${nombreRec}\n\n" +
                    "Marca: ${marcaRec}\n\n" + "Modelo: ${modeloRec}\n\n" +
                    "Precio: ${precioRec}\n\n"

            //Cambio de imagen
            when(num){
                1 -> imgRopa.setImageResource(R.drawable.casual01)
                2 -> imgRopa.setImageResource(R.drawable.casual02)
                3 -> imgRopa.setImageResource(R.drawable.casual03)
                4 -> imgRopa.setImageResource(R.drawable.casual04)
                5 -> imgRopa.setImageResource(R.drawable.formal01)
                6 -> imgRopa.setImageResource(R.drawable.formal02)
                7 -> imgRopa.setImageResource(R.drawable.formal03)
                8 -> imgRopa.setImageResource(R.drawable.formal04)
                9 -> imgRopa.setImageResource(R.drawable.deportiva01)
                10 -> imgRopa.setImageResource(R.drawable.deportiva02)
                11 -> imgRopa.setImageResource(R.drawable.deportiva03)
                12 -> imgRopa.setImageResource(R.drawable.deportiva04)
            }//when
        }//if

        //Evento del botón de regresar
        btnRegresar.setOnClickListener {
            val intent = Intent(applicationContext, InvitadoActivity::class.java)
            startActivity(intent)
        }


    }//onCreate
}//class