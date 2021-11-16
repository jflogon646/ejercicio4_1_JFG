class Modulo(_nombre: String = "", _numeroAlumnos: Int) {
    /**
     * Clase Modulo
     *
     * La clase Modulo está conformada por un número de alumnos y un array bidimensional de las notas.
     * El resto de parámetros son opcionales e incluidos por mí.
     * @param nombre es el nombre del módulo
     * @param alumnos es el array conformado por Alumno
     * @param numeroAlumnos es el número de alumnos que tiene el módulo
     * @param evaluaciones es un array bidimensional formado por 4 arrays de los distintos
     *  trimestres (uno por cada trimestre y un cuarto para la nota final) que tienen el tamaño establecido
     *  por el número de alumnos.
     *
     */


    /* Propiedades */
    private val nombre: String = _nombre
    private var alumnos: Array<Alumno?> = arrayOfNulls<Alumno?>(_numeroAlumnos)

    private var numeroAlumnos = _numeroAlumnos
    private var evaluaciones: Array<FloatArray> = Array(4) { FloatArray(_numeroAlumnos) { -1.0F } }


    /* Funciones auxiliares */

    //Usada en apartado 3
    private fun listadoPorTrimestre(trimestre: Int): MutableList<Pair<String, Float>> {
        var lista: MutableList<Pair<String, Float>> = mutableListOf(Pair("", 0.0F))
        var contador: Int = 0
        for (alumno in alumnos) {
            lista.add(Pair(alumno?.id.toString(), evaluaciones[trimestre][contador]))
        }
        lista.remove(Pair("", 0.0F))
        return lista
    }

    //Usada en apartado 4, 8 y 9
    private fun contadorAprobados(evaluacion: Int, balizaComprobacion: Boolean): Int {
        var contador = 0
        var x = 0
        if (balizaComprobacion) {
            while (x < numeroAlumnos) {
                if (evaluaciones[evaluacion][x] == 10.0F) {
                    contador++
                }
                x++
            }
        }
        else {
            while (x < numeroAlumnos) {
                if (evaluaciones[evaluacion][x] >= 5.0F) {
                    contador++
                }
                x++
            }
        }
        return contador
    }

    //Usada en apartado 5 y 6
    private fun ordenaNotas(orden: Boolean, trimestre: Int): Float {
        var listado = evaluaciones[trimestre].sort()
        return if (orden) {
            evaluaciones[trimestre][0]
        } else {
            evaluaciones[trimestre][numeroAlumnos - 1]
        }
    }


    /* Métodos */

    //Apartado 1
    fun establecerNota(idAlumno: String, evaluacion: String, nota: Float): Boolean {
        val trimestre: Int
        var posicion: Int
        when (evaluacion.uppercase()) {
            "PRIMERA" -> {
                trimestre = 0

            }
            "SEGUNDA" -> {
                trimestre = 1
            }
            "TERCERA" -> {
                trimestre = 2
            }
            else -> {
                return false
            }
        }
        return true
    }

    //Apartado 2
    fun calculaEvaluacionFinal() {
        var i: Int = 0
        while (i < numeroAlumnos) {
            evaluaciones[3][i] = (evaluaciones[0][i] + evaluaciones[1][i] + evaluaciones[2][i]) / 3.0F
        }
    }

    //Apartado 3
    fun listaNotas(evaluacion: String): List<Pair<String, Float>> {
        var listanotas: List<Pair<String, Float>>
        when (evaluacion.uppercase()) {
            "PRIMERA" -> { listanotas = listadoPorTrimestre(0) }
            "SEGUNDA" -> { listanotas = listadoPorTrimestre(1) }
            "TERCERA" -> { listanotas = listadoPorTrimestre(2) }
            "FINAL" -> { listanotas = listadoPorTrimestre(3) }
            else -> { listanotas = listOf(Pair("Evaluacion fallida", 0.0F)) }
        }
        return listanotas
    }

    //Apartado 4
    fun numeroAprobados(evaluacion: String): Int {
        when (evaluacion.uppercase()) {
            "PRIMERA" -> { return contadorAprobados(0,false) }
            "SEGUNDA" -> { return contadorAprobados(1,false) }
            "TERCERA" -> { return contadorAprobados(2,false) }
            "FINAL" -> { return contadorAprobados(3,false) }
            else -> { return -1 }
        }
    }


    //Apartado 5
    fun notaMasBaja(evaluacion: String): Float {
        when (evaluacion.uppercase()) {
            "PRIMERA" -> {
                return ordenaNotas(true, 0)
            }
            "SEGUNDA" -> {
                return ordenaNotas(true, 1)
            }
            "TERCERA" -> {
                return ordenaNotas(true, 2)
            }
            "FINAL" -> {
                return ordenaNotas(true, 3)
            }
            else -> {
                return -1.0F
            }
        }
    }

    //Apartado 6
    fun notaMasAlta(evaluacion: String): Float {
        when (evaluacion.uppercase()) {
            "PRIMERA" -> {
                return ordenaNotas(false, 0)
            }
            "SEGUNDA" -> {
                return ordenaNotas(false, 1)
            }
            "TERCERA" -> {
                return ordenaNotas(false, 2)
            }
            "FINAL" -> {
                return ordenaNotas(false, 3)
            }
            else -> {
                return -1.0F
            }
        }
    }

    //Apartado 7
    fun notaMedia(evaluacion: String): Float {
        when (evaluacion.uppercase()) {
            "PRIMERA" -> {
                return evaluaciones[0].average().toFloat()
            }
            "SEGUNDA" -> {
                return evaluaciones[0].average().toFloat()
            }
            "TERCERA" -> {
                return evaluaciones[0].average().toFloat()
            }
            "FINAL" -> {
                return evaluaciones[0].average().toFloat()
            }
            else -> {
                return -1.0F
            }
        }
    } //Float

    //Apartado 8
    fun hayAlumnosConDiez(evaluacion: String): Boolean {
        var comprobador: Int
        when (evaluacion.uppercase()) {
            "PRIMERA" -> { comprobador = contadorAprobados(0,true) }
            "SEGUNDA" -> { comprobador = contadorAprobados(1,true) }
            "TERCERA" -> { comprobador = contadorAprobados(2,true) }
            "FINAL" -> { comprobador = contadorAprobados(3,true) }
            else -> { return false }
        }
        return (comprobador != 0)
    } //Boolean

    //Apartado 9
    fun hayAlumnosAprobados(evaluacion: String): Boolean {
        var comprobador: Int
        when (evaluacion.uppercase()) {
            "PRIMERA" -> { comprobador = contadorAprobados(0,false) }
            "SEGUNDA" -> { comprobador = contadorAprobados(1,false) }
            "TERCERA" -> { comprobador = contadorAprobados(2,false) }
            "FINAL" -> { comprobador = contadorAprobados(3,false) }
            else -> { return false }
        }
        return (comprobador != 0)
    } //Boolean

    //Apartado 10
    fun primeraNotaAprobada() {} //Float

    //Apartado 11
    fun listaNotasOrdenadas() {} //list<pair>

    //Apartado 12
    fun matricularAlumno() {} //Boolean

    //Apartado 13
    fun bajaAlumno() {} //Boolean

}