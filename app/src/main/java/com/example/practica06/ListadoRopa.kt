package com.example.practica06

//Contiene los atributos de cada uno de los cardview´s
class ListadoRopa {
    companion object{
        val listadoRopaCas = mutableListOf<Ropa>(
            Ropa("Camisa vaquera de bolsillo", "Fit Button Down", "Casual Slim", 204.00, 10, "Ropa de vaquero", 1),
            Ropa("Sudadera Casual de Moda", "Big City", "Hoodie con Capucha", 200.00, 20, "Ropa de moda", 2),
            Ropa("Camisa Casual de Manga Larga", "Manfinity Homme", "Con bolsillos de Patchwork", 350.00, 30, "Ropa Casual", 3),
            Ropa("Vestido de Punto Casual de Moda", "Women Knitted", "Con detalle de lazo y patchwork para mujer", 500.00, 40, "Ropa casual de moda", 4)
        )

        val listadoRopaForm = mutableListOf<Ropa>(
            Ropa("Saco para Hombre", "Manfinity Mode", "Con cuello de pistola de bordado de pájaro", 600.00, 10, "Ropa Formal", 5),
            Ropa("Vestido Elegante de Mujer", "Shein Sweetro", "Con bloques de color, cuello alto y cinturón para otoño", 350.00, 20, "Ropa Formal", 6),
            Ropa("Vestido Largo Elegante para Mujer", "Shein Lune", "De manga larga, cuello redondo y estampado floral", 800.00, 30, "Ropa Formal", 7),
            Ropa("Blazer de Manga Larga", "Manfinity Mode", "Con Solapa en V para hombre", 750.00, 40, "Ropa Formal", 8)
        )

        val listadoRopaDep = mutableListOf<Ropa>(
            Ropa("Sudadera para Hombre", "Puma", "Con capucha y bicolor: blanco/negro", 500.00, 10, "Ropa Deportiva",9),
            Ropa("Playera Deportiva para Hombre", "Adidas", "Playera blanca con franjas negras", 550.00, 20, "Ropa Deportiva",10),
            Ropa("Playera Deportiva para Mujer", "Puma", "Color Morado y muy fresca al usarla", 280.00, 30, "Ropa Deportiva",11),
            Ropa("Short efecto doble para Mujer", "Puma", "Prenda Puma para correr", 640.00, 40, "Ropa Deportiva", 12)
        )
    }
}