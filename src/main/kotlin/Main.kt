fun menu(){
    var opcion: Int = -1
    var nombre: String = ""
    var numeroDeAlumnos = 0
    var evaluacion = ""
    var nota = 0.0F
    var idAlumno = ""
    var comprobacion = false

    print("Escoja el número de alumnos: ")
    numeroDeAlumnos = readLine()!!.toInt()
    print("Escoja un nombre para el modulo: ")
    nombre = readLine().toString() ?: ""
    var modulo = Modulo(nombre,numeroDeAlumnos)

    while (opcion != 14) {
        println("*".repeat(18) + " MENU DE MODULO " + "*".repeat(18))
        println("1. Establecer nota.")
        println("2. Calculo de la evaluacion final.")
        println("3. Listado de notas")
        println("4. Numero de aprobados")
        println("5. Nota mas baja")
        println("6. Nota mas alta")
        println("7. Nota media")
        println("8. Comprobar si existen alumnos con 10.")
        println("9. Comprobar si hay alumnos aprobados.")
        println("10. Primera nota no aprobada.")
        println("11. Listado de notas ordenadas.")
        println("12. Matricular alumno.")
        println("13. Dar de baja a un alumno.")
        println("14. Salir")
        print("Escoja una opción del menú: ")
        opcion = readLine()!!.toInt()
        when (opcion) {
            1 -> {
                print("Escoja el id del alumno: ")
                idAlumno = readLine().toString() ?: ""
                print("Escoja la evaluacion en la que desea introducir la nota (Ej: primera): ")
                evaluacion = readLine().toString()
                print("Escoja la nota a introducir(Ej: 0.0): ")
                nota = readLine()?.toFloat() ?: 0.0F
                modulo.establecerNota(idAlumno,evaluacion,nota)

            }
            2 -> {}
            3 -> {}
            4 -> {}
            5 -> {}
            6 -> {}
            7 -> {}
            8 -> {}
            9 -> {}
            10 -> {}
            11 -> {}
            12 -> {}
            13 -> {}
            14 -> {}

        }
    }
}


fun main() {
    /*
    val listado: List<Alumno> = listOf( Alumno("A1","Dorian","Leal","Manzorro"),
                                        Alumno("A2","Narciso","Benitez","Lopez"),
                                        Alumno("A3","Juan Jose","Perez","Martinez"),
                                        Alumno("A6","Paolo","Galindo",""),
                                        Alumno("A5","Manuel","Revuelta","Reyes"),
                                        Alumno("A4","Ismael","Garcia","Morillo"),
                                        Alumno("A7","Javier","Romero","Castellano"),
                                        Alumno("A9","Diego","Gonzalez","Elias"),
                                        Alumno("A8","Teodosio","Barba","Reyes"),
                                        Alumno("B1","Alvaro","Dominguez",""))
     */






}
