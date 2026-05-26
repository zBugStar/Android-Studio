package com.ejemplo.listadetareas
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Tarea(
    val id: Int,
    val titulo: String,
    var completada: Boolean = false
)
