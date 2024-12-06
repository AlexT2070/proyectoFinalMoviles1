package com.example.practica06.adaptador
import com.example.practica06.Ropa

interface OnRopaClickListener {
    fun onButtonWishList(ropa: Ropa) //agregar un elemento a wishlist
    fun onButtonRemoveFromWishList(ropa: Ropa) // eliminar un elemento de la wishlist
}