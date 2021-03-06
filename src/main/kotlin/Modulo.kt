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
     * las funciones privadas están al final del fichero
     */


    /* Propiedades */
    private val nombre: String = _nombre
    private var alumnos: Array<Alumno?> = arrayOfNulls<Alumno?>(_numeroAlumnos)

    private var numeroAlumnos = _numeroAlumnos
    private var evaluaciones: Array<FloatArray> = Array(4) { FloatArray(_numeroAlumnos) { -1.0F } }



    /* Métodos */

    //Apartado 1
    fun establecerNota(idAlumno: String, evaluacion: String, nota: Float): Boolean {
        var posicion: Int = alumnos.indexOfFirst { it -> it?.id == idAlumno }
        var check = false
        when (evaluacion.uppercase()) {
            "PRIMERA" -> {
                evaluaciones[0][posicion] = nota
                check = true
            }
            "SEGUNDA" -> {
                evaluaciones[1][posicion] = nota
                check = true
            }
            "TERCERA" -> {
                evaluaciones[2][posicion] = nota
                check = true
            }
        }
        return check
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
        return listadoPorTrimestre(compruebaEvaluacion(evaluacion))
    }

    //Apartado 4
    fun numeroAprobados(evaluacion: String): Int {
        return contadorAprobados(compruebaEvaluacion(evaluacion),false)
    }


    //Apartado 5
    fun notaMasBaja(evaluacion: String): Float {
        return ordenaNotas(true,compruebaEvaluacion(evaluacion))
    }

    //Apartado 6
    fun notaMasAlta(evaluacion: String): Float {
        return ordenaNotas(false,compruebaEvaluacion(evaluacion))
    }

    //Apartado 7
    fun notaMedia(evaluacion: String): Float {
        return evaluaciones[compruebaEvaluacion(evaluacion)].average().toFloat()
    }

    //Apartado 8
    fun hayAlumnosConDiez(evaluacion: String): Boolean {
        var comprobador: Int = contadorAprobados(compruebaEvaluacion(evaluacion),true)
        return (comprobador != 0)
    }

    //Apartado 9
    fun hayAlumnosAprobados(evaluacion: String): Boolean {
        var comprobador: Int = contadorAprobados(compruebaEvaluacion(evaluacion),false)
        return (comprobador != 0)
    } //Boolean

    //Apartado 10
    fun primeraNotaNoAprobada(evaluacion: String): Float{
        return notaNoAprobada(compruebaEvaluacion(evaluacion))
    }

    //Apartado 11
    fun listaNotasOrdenadas(evaluacion: String): List<Pair<String,Float>> {
        return listaNotas(evaluacion).sortedBy { it.second }
    }

    //Apartado 12
    fun matricularAlumno(alumno: Alumno): Boolean {
        var contador: Int = 0
        var check = false
        while ((contador < numeroAlumnos || !check)) {
            if (alumnos[contador] == null) {
                alumnos[contador] = alumno
                check = true
            }
            contador++
        }
        return check
    }

    //Apartado 13
    fun bajaAlumno(idAlumno: String): Boolean {
        var check = false
        var contador = 0
        while((contador < numeroAlumnos || !check)) {
            if (alumnos[contador]?.id == idAlumno) {
                alumnos[contador] = null

                check = true
            }
            contador++
        }
        return check
    }


    /* Funciones auxiliares */

    // Usada en todos los métodos que tienen como parámetro la evaluación (3, 4, 5, 6, 7, 8, 9, 10, 11) excepto el 1.
    private fun compruebaEvaluacion(evaluacion: String): Int{
        return when (evaluacion.uppercase()) {
            "PRIMERA" -> { 0 }
            "SEGUNDA" -> { 1 }
            "TERCERA" -> { 2 }
            "FINAL" -> { 3 }
            else -> { 3 }
        }
    }


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

    //Usada en apartado 10
    private fun notaNoAprobada(trimestre: Int): Float{
        var nota: Float = - 1.0F
        var contador: Int = 0
        var check: Boolean = false
        while ((contador < numeroAlumnos || !check)) {
            if(evaluaciones[trimestre][contador] < 5.0F) {
                nota = evaluaciones[trimestre][contador]
                check = true
            }
            contador++
        }
        return nota
    }

    //Usada en apartado 11
    private fun listadoOrdenadoPorTrimestre(trimestre: Int,lista: Array<Alumno?> ): MutableList<Pair<String, Float>> {
        var listado: MutableList<Pair<String, Float>> = mutableListOf(Pair("", 0.0F))
        var contador: Int = 0
        for (alumno in lista) {
            listado.add(Pair(alumno?.id.toString(), evaluaciones[trimestre][contador]))
        }
        listado.remove(Pair("", 0.0F))
        return listado
    }



}