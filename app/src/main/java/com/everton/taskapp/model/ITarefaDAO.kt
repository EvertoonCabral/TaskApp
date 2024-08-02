package com.everton.taskapp.model

interface ITarefaDAO {

     fun salvar(tarefa: Tarefa) : Boolean
     fun editar(tarefa: Tarefa) : Boolean
     fun remover(idTarefa: Int) : Boolean
     fun listar() : List<Tarefa>

}