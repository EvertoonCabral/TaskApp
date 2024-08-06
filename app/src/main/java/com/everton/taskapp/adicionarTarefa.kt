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

        var tarefa : Tarefa? = null

        val bundle = intent.extras
        if(bundle!=null){
             tarefa = bundle.getSerializable("tarefa") as Tarefa
            binding.editTarefa.setText(tarefa.descricao)
        }


        binding.btnSalvar.setOnClickListener {

            val tarefa = Tarefa(
                -1, binding.editTarefa.text.toString(), "default..."
            )


            if (binding.editTarefa.text.toString().isNotEmpty()) {

                if(tarefa != null){
                    editarTarefa(tarefa)
                }else{
                    salvarTarefa(tarefa)

                }

            }


        }


    }

    private fun editarTarefa(tarefa: Tarefa) {

        val descricaoTarefa = binding.editTarefa.text.toString()
        val tarefaEditada = Tarefa(tarefa.idTarefa, descricaoTarefa, "default")

        val tarefaDAO = TarefaDAO(this)

        if(tarefaDAO.editar(tarefaEditada)){

            Toast.makeText(
                this,
                "Tarefa editada com sucesso!",
                Toast.LENGTH_SHORT
            ).show()
        }else{

            Toast.makeText(
                this,
                "ERRO ao editar tarefa!",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun salvarTarefa(tarefa: Tarefa) {
        try {
            val tarefaDAO = TarefaDAO(this)
            tarefaDAO.salvar(tarefa)

            Toast.makeText(
                this,
                "Tarefa salva com sucesso!",
                Toast.LENGTH_SHORT
            ).show()

            finish()

        } catch (e: Exception) {

            Toast.makeText(
                this,
                "ERRO ao salvar tarefa!",
                Toast.LENGTH_SHORT
            ).show()

        }
    }
}