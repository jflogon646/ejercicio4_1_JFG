data class Alumno(
    /**
     * Clase Alumno
     * Conformada únicamente por propiedades que se introducen por parámetro
     * @param id es el id del alumno
     * @param nombre es el nombre del alumno
     * @param ape1 es el primer apellido del alumno
     * @param ape2 es el segundo apellido del alumno
     */
    val id: String,
    val nombre: String = "",
    val ape1: String = "",
    val ape2: String = "",
) {
    /*val id: String = identificador
    private val ape1: String = surname ?: ""
    private val ape2: String = surname2 ?: ""
    private val nombre: String = name

    override fun toString(): String {
        return nombre + ape1 + ape2
    }*/
}