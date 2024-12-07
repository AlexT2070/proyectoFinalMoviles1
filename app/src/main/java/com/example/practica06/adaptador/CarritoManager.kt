package com.example.practica06.adaptador

import androidx.lifecycle.MutableLiveData
import com.example.practica06.Ropa

object CarritoManager {
    val carritoLiveData = MutableLiveData<MutableList<Ropa>>(mutableListOf())

    fun agregarProducto(ropa: Ropa) {
        val carritoActual = carritoLiveData.value ?: mutableListOf()
        carritoActual.add(ropa)
        carritoLiveData.value = carritoActual // Notifica el cambio
    }

    fun eliminarProducto(ropa: Ropa) {
        val carritoActual = carritoLiveData.value ?: mutableListOf()
        carritoActual.remove(ropa)
        carritoLiveData.value = carritoActual // Notifica el cambio
    }

    fun obtenerCantidadProductos(): Int {
        return carritoLiveData.value?.size ?: 0
    }
}