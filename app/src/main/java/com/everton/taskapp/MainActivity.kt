package com.everton.taskapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        tarefaAdapter = TarefaAdapter(
            {
                id -> confirmarExclusao(id)
            }

        )
        binding.rvTarefas.adapter = tarefaAdapter
        binding.rvTarefas.layoutManager = LinearLayoutManager(this)

        binding.fabAdicionar.setOnClickListener {

            intent = Intent(this, adicionarTarefa::class.java)
            startActivity(intent)

        }

    }

    private fun confirmarExclusao(id: Int) {

        val alertBuilder = AlertDialog.Builder(this)

        alertBuilder.setTitle("Confirmar Exclusão?")
        alertBuilder.setMessage("Confirmar a exclusão do item selecionado?")

        alertBuilder.setPositiveButton("Sim"){_,_, ->

            val tarefaDAO = TarefaDAO(this)

            if(tarefaDAO.remover(id)){
             atualizarListaTarefa()
                Toast.makeText(this,
                    "Tarefa removida com sucesso!",
                    Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,
                    "Erro ao  remover tarefa!",
                    Toast.LENGTH_SHORT).show()
            }
        }
        alertBuilder.setNegativeButton("Não"){_,_, ->


        }

        alertBuilder.create().show()

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