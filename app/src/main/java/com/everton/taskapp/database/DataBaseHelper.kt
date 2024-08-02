package com.everton.taskapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataBaseHelper(context:Context) : SQLiteOpenHelper(context, NOME_BANCO_DADOS,null ,VERSAO) {

    companion object{
        const val NOME_BANCO_DADOS = "taskApp.db"
        const val VERSAO = 1
        const val NOME_TABELA = "tarefa"
        const val ID_TAREFA = "id_tarefa"
        const val DESCRICAO = "decricao"
        const val DATA_CADASTRO = "data_cadastro"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val sql = "CREATE TABLE  IF NOT EXISTS $NOME_TABELA ("+
                "  $ID_TAREFA INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "  $DESCRICAO varchar(100)," +
                "  $DATA_CADASTRO DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP" +
                ");"


        try{
            db?.execSQL(sql)

            Log.i("db_info","Tabela de tarefa criada!")

        }catch (e:Exception){

            e.printStackTrace()
            Log.e("db_info","Erro ao criar tabelas!")

        }



    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}