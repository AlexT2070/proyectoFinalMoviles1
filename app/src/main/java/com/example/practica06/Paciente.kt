import java.io.Serializable

data class Paciente(
    val nombre: String,
    val domicilio: String,
    val correo: String,
    val telefono: String
) : Serializable
//Funciona el commit y reciben esto