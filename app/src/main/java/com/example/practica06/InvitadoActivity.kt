package com.example.practica06

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@Suppress("NAME_SHADOWING")
class InvitadoActivity : AppCompatActivity() {
    //Instancias a componentes gráficos
    private lateinit var imgCasual1: ImageView
    private lateinit var imgCasual2: ImageView
    private lateinit var imgCasual3: ImageView
    private lateinit var imgCasual4: ImageView

    private lateinit var imgFormal1: ImageView
    private lateinit var imgFormal2: ImageView
    private lateinit var imgFormal3: ImageView
    private lateinit var imgFormal4: ImageView

    private lateinit var imgDeportiva1: ImageView
    private lateinit var imgDeportiva2: ImageView
    private lateinit var imgDeportiva3: ImageView
    private lateinit var imgDeportiva4: ImageView

    //Botón de regreso al Login
    private lateinit var btnRLogin: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_invitado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Asociación a componentes gráficos
        imgCasual1 = findViewById(R.id.imgCas1)
        imgCasual2 = findViewById(R.id.imgCas2)
        imgCasual3 = findViewById(R.id.imgCas3)
        imgCasual4 = findViewById(R.id.imgCas4)

        imgFormal1 = findViewById(R.id.imgFor1)
        imgFormal2 = findViewById(R.id.imgFor2)
        imgFormal3 = findViewById(R.id.imgFor3)
        imgFormal4 = findViewById(R.id.imgFor4)

        imgDeportiva1 = findViewById(R.id.imgDep1)
        imgDeportiva2 = findViewById(R.id.imgDep2)
        imgDeportiva3 = findViewById(R.id.imgDep3)
        imgDeportiva4 = findViewById(R.id.imgDep4)

        btnRLogin = findViewById(R.id.btnReturn)

        //Atributos a ver a detalle de cada imagen
        var nombreRop: String = "Camisa vaquera de bolsillo para hombre"
        var marcaRop: String = "Fit Button Down"
        var modeloRop: String = "Casual Slim"
        var precioRop: Double = 204.00
        var intent: Intent

        //Eventos al dar click hacia cualquiera de las imagenes

        //Ropa Casual
        imgCasual1.setOnClickListener {
            Toast.makeText(this@InvitadoActivity, "Nombre: $nombreRop",
                Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext, ConsultaActivity::class.java)
            intent.putExtra("nombre", nombreRop)
            intent.putExtra("marca", marcaRop)
            intent.putExtra("modelo", modeloRop)
            intent.putExtra("precio", precioRop)
            intent.putExtra("numRopa", 1)
            startActivity(intent)
        }
        imgCasual2.setOnClickListener {
            //Atributos actualizados
            nombreRop = "Sudadera Casual de Moda"
            marcaRop = "Big City"
            modeloRop = "Hoodie con Capucha"
            precioRop = 200.00
            Toast.makeText(this@InvitadoActivity, "Nombre: $nombreRop",
                Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext, ConsultaActivity::class.java)
            intent.putExtra("nombre", nombreRop)
            intent.putExtra("marca", marcaRop)
            intent.putExtra("modelo", modeloRop)
            intent.putExtra("precio", precioRop)
            intent.putExtra("numRopa", 2)
            startActivity(intent)
        }
        imgCasual3.setOnClickListener {
            //Atributos actualizados
            nombreRop = "Camisa Casual de Manga Larga para Hombre"
            marcaRop = "Manfinity Homme"
            modeloRop = "Con bolsillos de Patchwork"
            precioRop = 350.00
            Toast.makeText(this@InvitadoActivity, "Nombre: $nombreRop",
                Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext, ConsultaActivity::class.java)
            intent.putExtra("nombre", nombreRop)
            intent.putExtra("marca", marcaRop)
            intent.putExtra("modelo", modeloRop)
            intent.putExtra("precio", precioRop)
            intent.putExtra("numRopa", 3)
            startActivity(intent)
        }
        imgCasual4.setOnClickListener {
            //Atributos actualizados
            nombreRop = "Vestido de Punto Casual de Moda"
            marcaRop = "Women Knitted"
            modeloRop = "Con detalle de lazo y patchwork para mujer"
            precioRop = 500.00
            Toast.makeText(this@InvitadoActivity, "Nombre: $nombreRop",
                Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext, ConsultaActivity::class.java)
            intent.putExtra("nombre", nombreRop)
            intent.putExtra("marca", marcaRop)
            intent.putExtra("modelo", modeloRop)
            intent.putExtra("precio", precioRop)
            intent.putExtra("numRopa", 4)
            startActivity(intent)
        }

        //Ropa Formal
        imgFormal1.setOnClickListener {
            //Atributos actualizados
            nombreRop = "Saco para Hombre"
            marcaRop = "Manfinity Mode"
            modeloRop = "Con cuello de pistola de bordado de pájaro"
            precioRop = 600.00
            Toast.makeText(this@InvitadoActivity, "Nombre: $nombreRop",
                Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext, ConsultaActivity::class.java)
            intent.putExtra("nombre", nombreRop)
            intent.putExtra("marca", marcaRop)
            intent.putExtra("modelo", modeloRop)
            intent.putExtra("precio", precioRop)
            intent.putExtra("numRopa", 5)
            startActivity(intent)
        }
        imgFormal2.setOnClickListener {
            //Atributos actualizados
            nombreRop = "Vestido Elegante de Mujer"
            marcaRop = "Shein Sweetro"
            modeloRop = "Con bloques de color, cuello alto y cinturón para otoño"
            precioRop = 350.00
            Toast.makeText(this@InvitadoActivity, "Nombre: $nombreRop",
                Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext, ConsultaActivity::class.java)
            intent.putExtra("nombre", nombreRop)
            intent.putExtra("marca", marcaRop)
            intent.putExtra("modelo", modeloRop)
            intent.putExtra("precio", precioRop)
            intent.putExtra("numRopa", 6)
            startActivity(intent)
        }
        imgFormal3.setOnClickListener {
            //Atributos actualizados
            nombreRop = "Vestido Largo Elegante para Mujer"
            marcaRop = "Shein Lune"
            modeloRop = "De manga larga, cuello redondo y estampado floral"
            precioRop = 800.00
            Toast.makeText(this@InvitadoActivity, "Nombre: $nombreRop",
                Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext, ConsultaActivity::class.java)
            intent.putExtra("nombre", nombreRop)
            intent.putExtra("marca", marcaRop)
            intent.putExtra("modelo", modeloRop)
            intent.putExtra("precio", precioRop)
            intent.putExtra("numRopa", 7)
            startActivity(intent)
        }
        imgFormal4.setOnClickListener {
            //Atributos actualizados
            nombreRop = "Blazer de Manga Larga"
            marcaRop = "Manfinity Mode"
            modeloRop = "Con Solapa en V para hombre"
            precioRop = 750.00
            Toast.makeText(this@InvitadoActivity, "Nombre: $nombreRop",
                Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext, ConsultaActivity::class.java)
            intent.putExtra("nombre", nombreRop)
            intent.putExtra("marca", marcaRop)
            intent.putExtra("modelo", modeloRop)
            intent.putExtra("precio", precioRop)
            intent.putExtra("numRopa", 8)
            startActivity(intent)
        }

        //Ropa Deportiva
        imgDeportiva1.setOnClickListener {
            //Atributos actualizados
            nombreRop = "Sudadera para Hombre"
            marcaRop = "Puma"
            modeloRop = "Con capucha y bicolor: blanco/negro"
            precioRop = 500.00
            Toast.makeText(this@InvitadoActivity, "Nombre: $nombreRop",
                Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext, ConsultaActivity::class.java)
            intent.putExtra("nombre", nombreRop)
            intent.putExtra("marca", marcaRop)
            intent.putExtra("modelo", modeloRop)
            intent.putExtra("precio", precioRop)
            intent.putExtra("numRopa", 9)
            startActivity(intent)
        }
        imgDeportiva2.setOnClickListener {
            //Atributos actualizados
            nombreRop = "Playera Deportiva para Hombre"
            marcaRop = "Adidas"
            modeloRop = "Playera blanca con franjas negras"
            precioRop = 550.00
            Toast.makeText(this@InvitadoActivity, "Nombre: $nombreRop",
                Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext, ConsultaActivity::class.java)
            intent.putExtra("nombre", nombreRop)
            intent.putExtra("marca", marcaRop)
            intent.putExtra("modelo", modeloRop)
            intent.putExtra("precio", precioRop)
            intent.putExtra("numRopa", 10)
            startActivity(intent)
        }
        imgDeportiva3.setOnClickListener {
            //Atributos actualizados
            nombreRop = "Playera Deportiva para Mujer"
            marcaRop = "Puma"
            modeloRop = "Color Morado y muy fresca al usarla"
            precioRop = 280.00
            Toast.makeText(this@InvitadoActivity, "Nombre: $nombreRop",
                Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext, ConsultaActivity::class.java)
            intent.putExtra("nombre", nombreRop)
            intent.putExtra("marca", marcaRop)
            intent.putExtra("modelo", modeloRop)
            intent.putExtra("precio", precioRop)
            intent.putExtra("numRopa", 11)
            startActivity(intent)
        }
        imgDeportiva4.setOnClickListener {
            //Atributos actualizados
            nombreRop = "Short efecto doble para Mujer"
            marcaRop = "Puma"
            modeloRop = "Prenda Puma para correr"
            precioRop = 640.00
            Toast.makeText(this@InvitadoActivity, "Nombre: $nombreRop",
                Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext, ConsultaActivity::class.java)
            intent.putExtra("nombre", nombreRop)
            intent.putExtra("marca", marcaRop)
            intent.putExtra("modelo", modeloRop)
            intent.putExtra("precio", precioRop)
            intent.putExtra("numRopa", 12)
            startActivity(intent)
        }
        //Evento del botón de regreso al primer Activity
        btnRLogin.setOnClickListener {
            val intent = Intent(applicationContext, IngresoActivity::class.java)
            startActivity(intent)
        }

    }//onCreate
}//class