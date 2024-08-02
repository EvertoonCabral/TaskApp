package com.everton.taskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.everton.taskapp.databinding.ActivityAdicionarTarefaBinding
import com.everton.taskapp.model.Tarefa
import com.everton.taskapp.model.TarefaDAO
import android.widget.Toast as tO

class adicionarTarefa : AppCompatActivity() {

    private val binding by lazy {

        ActivityAdicionarTarefaBinding.inflate(layoutInflater)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)




        binding.btnSalvar.setOnClickListener {

            val tarefa = Tarefa(
                -1, binding.editTarefa.text.toString(), "default..."
            )


            if (binding.editTarefa.text.toString().isNotEmpty()) {

                try {
                    val tarefaDAO = TarefaDAO(this)
                    tarefaDAO.salvar(tarefa)

                    Toast.makeText(
                        this,
                        "Tarefa salva com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.i("db_info", "Tarefa - ${tarefa.descricao} criada!")

                    finish()

                } catch (e: Exception) {

                    Toast.makeText(
                        this,
                        "ERRO ao salvar tarefa!",
                        Toast.LENGTH_SHORT
                    ).show()

                    e.printStackTrace()
                    Log.e("db_info", "Erro ao criar tabelas!")

                }
            }


        }


    }
}