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

            escrita.insert(DataBaseHelper.TABELA_TAREFA, null, value)

            Log.i("db_info", "Tarefa salva com sucesso!")

        } catch (e: Exception) {

            e.printStackTrace()
            Log.e("db_info", "Erro ao salvar tarefa!")

            return false
        }
        return true

    }

    override fun editar(tarefa: Tarefa): Boolean {

        val arg = arrayOf(tarefa.idTarefa.toString())
        val conteudo = ContentValues()
        conteudo.put(DataBaseHelper.DESCRICAO, tarefa.descricao)

        try {
            escrita.update(
                DataBaseHelper.TABELA_TAREFA,
                conteudo,
                "${DataBaseHelper.ID_TAREFA} = ?",
                arg
            )

            Log.i("db_info", "Tarefa edita com sucesso!")

        } catch (e: Exception) {

            e.printStackTrace()
            Log.e("db_info", "Erro ao editar tarefa!")

            return false
        }
        return true
    }

    override fun remover(idTarefa: Int): Boolean {

        val arg = arrayOf(idTarefa.toString())

        try {
            escrita.delete(DataBaseHelper.TABELA_TAREFA,
                "${DataBaseHelper.ID_TAREFA} = ?",
                arg
            )


            Log.i("db_info", "Tarefa removida com sucesso!")

        } catch (e: Exception) {

            e.printStackTrace()
            Log.e("db_info", "Erro ao remover tarefa!")

            return false
        }
        return true

    }

    override fun listar(): List<Tarefa> {
        val listaTarefa = mutableListOf<Tarefa>()

        val sql = """
            SELECT ${DataBaseHelper.ID_TAREFA}, 
                   ${DataBaseHelper.DESCRICAO}, 
                   strftime('%d/%m/%Y %H:%M', ${DataBaseHelper.DATA_CADASTRO}) as data_cadastro 
            FROM ${DataBaseHelper.TABELA_TAREFA}
        """.trimIndent()

        val cursor = leitura.rawQuery(sql, null)

        try {
            if (cursor.moveToFirst()) {
                val indiceId = cursor.getColumnIndexOrThrow(DataBaseHelper.ID_TAREFA)
                val indiceDescricao = cursor.getColumnIndexOrThrow(DataBaseHelper.DESCRICAO)
                val indiceDataCadastro = cursor.getColumnIndexOrThrow("data_cadastro")
                do {
                    val idTarefa = cursor.getInt(indiceId)
                    val descricao = cursor.getString(indiceDescricao)
                    val dataCadastro = cursor.getString(indiceDataCadastro)

                    listaTarefa.add(Tarefa(idTarefa, descricao, dataCadastro))
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("db_info", "Erro ao listar tarefas!")
        } finally {
            cursor.close()
        }
        return listaTarefa
    }
}