package com.example.practica06

data class Ropa (
    //Atributos de la clase "Ropa"
    var nombre: String = "none",
    var marca: String = "none",
    var modelo: String = "none",
    var precio: Double = 0.0,
    var cantidad: Int = 0,
    var descripcion: String = ""
){
    // Method para calcular el total de la compra
    fun calcularTotal(): Double {
        return precio * cantidad
    }
    //Validación de un atributo
    init {
        require(precio >= 0){"El precio no puede ser negativo"}
        require(cantidad >= 0){"La cantidad no puede ser negativa"}
    }
}
