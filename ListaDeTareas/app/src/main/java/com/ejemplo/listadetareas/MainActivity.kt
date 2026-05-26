package com.ejemplo.listadetareas

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.recyclerview.widget.LinearLayoutManager
import com.ejemplo.listadetareas.databinding.ActivityMainBinding
// SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listaTareas = mutableListOf<Tarea>()
    private lateinit var adapter: TareaAdapter
    private var contadorId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargarLista()
        configurarRecyclerView()
        configurarBotones()
        actualizarContador()
    }

    private fun configurarRecyclerView() {
        // TODO 1: Crear el adapter pasando listaTareas y una lambda para eliminar
        adapter = TareaAdapter(listaTareas) { posicion ->
            eliminarTarea(posicion)
        }

        // TODO 2: Asignar un LinearLayoutManager al RecyclerView
        binding.rvTareas.layoutManager = LinearLayoutManager(this)


        // TODO 3: Asignar el adapter al RecyclerView
        binding.rvTareas.adapter = adapter
    }

    private fun configurarBotones() {
        binding.btnAgregar.setOnClickListener {
            val texto = binding.etNuevaTarea.text.toString().trim()
            if (texto.isNotEmpty()) {
                agregarTarea(texto)
                binding.etNuevaTarea.text.clear()
            } else {
                Toast.makeText(this, "Escribe una tarea primero", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun agregarTarea(titulo: String) {
        // TODO 4: Incrementar contadorId
        contadorId++

        // TODO 5: Crear una nueva Tarea con el id y titulo
        val nuevaTarea = Tarea(contadorId, titulo)

        // TODO 6: Agregarla a listaTareas
        listaTareas.add(nuevaTarea)

        // TODO 7: Notificar al adapter con adapter.notifyItemInserted(listaTareas.size - 1)
        adapter.notifyItemInserted(listaTareas.size - 1)

        // TODO 8: Llamar a actualizarContador()
        actualizarContador()
        guardarLista()

    }

    private fun eliminarTarea(posicion: Int) {
        // TODO 9: Remover la tarea en la posición dada
        listaTareas.removeAt(posicion)

        // TODO 10: Notificar al adapter
        adapter.notifyItemRemoved(posicion)
        adapter.notifyItemRangeChanged(posicion, listaTareas.size - posicion)

        // TODO 11: Llamar a actualizarContador()
        actualizarContador()
        guardarLista()
    }

    private fun actualizarContador() {
        // TODO 12: Contar cuántas tareas tienen completada == false
        val pendientes = listaTareas.count { !it.completada }

        // TODO 13: Actualizar binding.tvContador.text
        binding.tvContador.text = getString(R.string.tareas_pendientes, pendientes)
    }

    // Crear funciones para que el SharedPreference guarde la lista de tareas y la cargue
    // Guardar Lista
    private fun guardarLista() {
        val prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(listaTareas)
        prefs.edit {
            putString("listaTareas", json)
        }
    }
    // Cargar lista
    private fun cargarLista() {
        val prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE)
        val gson = Gson()
        val json = prefs.getString("listaTareas", null)

        if (json != null) {
            val type = object : TypeToken<MutableList<Tarea>>() {}.type
            val listaGuardada: MutableList<Tarea> = gson.fromJson(json, type)
            listaTareas.clear()
            listaTareas.addAll(listaGuardada)
            contadorId = listaTareas.maxOfOrNull { it.id } ?: 0
        }
    }


}


