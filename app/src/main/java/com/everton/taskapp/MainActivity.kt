package com.everton.taskapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.everton.taskapp.database.DataBaseHelper
import com.everton.taskapp.databinding.ActivityMainBinding
import com.everton.taskapp.model.Tarefa
import com.everton.taskapp.model.TarefaDAO

class MainActivity : AppCompatActivity() {

    private val binding by lazy {

        ActivityMainBinding.inflate(layoutInflater)
    }

    private var listaTarefa = emptyList<Tarefa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fabAdicionar.setOnClickListener {

            intent = Intent(this, adicionarTarefa::class.java)
            startActivity(intent)

        }
    }

    override fun onStart() {
        super.onStart()

        val tarefaDAO = TarefaDAO(this)

        listaTarefa = tarefaDAO.listar()

        if(listaTarefa.isNotEmpty()){

            listaTarefa.forEach {tarefa ->

                Log.i("db_info","Tarefa: ID: ${tarefa.idTarefa} - ${tarefa.descricao}.")

            }


        }else{
            Log.e("db_info","Erro ao listar tarefas - MainActivity")
        }
    }

}