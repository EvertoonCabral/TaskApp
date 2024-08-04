package com.everton.taskapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.everton.taskapp.adapter.TarefaAdapter
import com.everton.taskapp.database.DataBaseHelper
import com.everton.taskapp.databinding.ActivityMainBinding
import com.everton.taskapp.model.Tarefa
import com.everton.taskapp.model.TarefaDAO

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var listaTarefa = emptyList<Tarefa>()
    private lateinit var tarefaAdapter: TarefaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        tarefaAdapter = TarefaAdapter()
        binding.rvTarefas.adapter = tarefaAdapter
        binding.rvTarefas.layoutManager = LinearLayoutManager(this)

        binding.fabAdicionar.setOnClickListener {

            intent = Intent(this, adicionarTarefa::class.java)
            startActivity(intent)

        }

    }

    private fun atualizarListaTarefa(){

        val tarefaDAO = TarefaDAO(this)

        listaTarefa = tarefaDAO.listar()

        tarefaAdapter.atualizarLista(listaTarefa)

    }


    override fun onStart() {
        super.onStart()

        atualizarListaTarefa()


    }

}