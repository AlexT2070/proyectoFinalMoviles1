package com.example.practica06

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Comentarios : AppCompatActivity() {
    private lateinit var video: VideoView
    private lateinit var rating: RatingBar
    private lateinit var registrar: Button
    private lateinit var regresar: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comentarios)

        video = findViewById(R.id.videoView)
        rating = findViewById(R.id.ratingBar)
        registrar = findViewById(R.id.btnRegistrar)
        regresar = findViewById(R.id.btnRegresar)

        video.setVideoPath("android.resource://" + packageName + "/" + R.raw.video)
        video.start()

        rating.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            if (fromUser) {
                Toast.makeText(this, "Calificación: $rating estrellas", Toast.LENGTH_SHORT).show()
            }
        }
        registrar.setOnClickListener {
            Toast.makeText(this, "Gracias por tu comentario", Toast.LENGTH_SHORT).show()
        }
        regresar.setOnClickListener {
            finish()
        }
    }
}