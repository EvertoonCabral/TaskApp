package com.everton.taskapp.model

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.everton.taskapp.database.DataBaseHelper

class TarefaDAO(context: Context) : ITarefaDAO {

    private val escrita = DataBaseHelper(context).writableDatabase
    private val leitura = DataBaseHelper(context).readableDatabase


    override fun salvar(tarefa: Tarefa): Boolean {

        val value = ContentValues()
        value.put(DataBaseHelper.DESCRICAO, tarefa.descricao)


        try {

            escrita.insert(DataBaseHelper.NOME_TABELA, null, value)

            Log.i("db_info", "Tabela de tarefa criada!")

        } catch (e: Exception) {

            e.printStackTrace()
            Log.e("db_info", "Erro ao criar tabelas!")

            return false
        }
        return true

    }

    override fun editar(tarefa: Tarefa): Boolean {
        TODO("Not yet implemented")
    }

    override fun remover(idTarefa: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun listar(): List<Tarefa> {
        TODO("Not yet implemented")
    }
}