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

        var tarefa: Tarefa? = null
        val bundle = intent.extras
        if (bundle != null) {
            tarefa = bundle.getSerializable("tarefa") as Tarefa
            binding.editTarefa.setText(tarefa.descricao)
        }


        binding.btnSalvar.setOnClickListener {


            //a tarefa estava sendo instanciada aqui, tornando o IF abaixo redundante
            //pois tarefa sempre seria diferente de null pois ela estava sendo instanciada aqui.

            if (binding.editTarefa.text.isNotEmpty()) {

                if (tarefa != null) { //se diferente de tulo o edittext recebeu os dados do putExtra, logo deve ser utilizado a ediçao
                    editarTarefa(tarefa)
                } else { // Se for vazio é uma tarefa nova.
                    salvarTarefa()

                }

            }


        }


    }

    private fun editarTarefa(tarefa: Tarefa) {

        val descricaoTarefa = binding.editTarefa.text.toString()
        val tarefaEditada = Tarefa(tarefa.idTarefa, descricaoTarefa, "default")

        val tarefaDAO = TarefaDAO(this)

        if (tarefaDAO.editar(tarefaEditada)) {

            Toast.makeText(
                this,
                "Tarefa editada com sucesso!",
                Toast.LENGTH_SHORT
            ).show()
        } else {

            Toast.makeText(
                this,
                "ERRO ao editar tarefa!",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
///Ajustado o metodo salvar que estava recebendo por parametro uma tarefa:Tarefa
///a tarefa é instanciada dentro do metodo e salvada no SQLite utilizando o DAO
    private fun salvarTarefa() {

            val descricao = binding.editTarefa.text.toString()

            val tarefa = Tarefa(-1, binding.editTarefa.text.toString(), "default...")

            val tarefaDAO = TarefaDAO(this)

            if(tarefaDAO.salvar(tarefa)) {

                Toast.makeText(
                    this,
                    "Tarefa salva com sucesso!",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }

    }
}