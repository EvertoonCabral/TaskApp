package com.everton.taskapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.everton.taskapp.databinding.ItemTarefaBinding
import com.everton.taskapp.model.Tarefa

class TarefaAdapter(

    val onClickExcluir : (Int) -> Unit, //passando uma funçao no construtor
    val onClickEditar : (Tarefa) -> Unit

) : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    private var listaTarefas: List<Tarefa> = emptyList()

    inner class TarefaViewHolder(itemBinding: ItemTarefaBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        private val binding: ItemTarefaBinding = itemBinding

        fun bind(tarefa: Tarefa) {
            binding.textDescricao.text = tarefa.descricao
            binding.textData.text = tarefa.dataCadastro
            binding.btnExcluir.setOnClickListener {

                    onClickExcluir(tarefa.idTarefa)

            }
            binding.btnEditar.setOnClickListener {

                onClickEditar(tarefa)

            }

        }
    }

    fun atualizarLista(lista: List<Tarefa>) {
        this.listaTarefas = lista
        notifyDataSetChanged()
        Log.i("db_info", "Lista atualizada!")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarefaViewHolder {
        val itemTarefaBinding = ItemTarefaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TarefaViewHolder(itemTarefaBinding)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = listaTarefas[position]
        holder.bind(tarefa)
    }

    override fun getItemCount(): Int {
        return listaTarefas.size
    }
}
