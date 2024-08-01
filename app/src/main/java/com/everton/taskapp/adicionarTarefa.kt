package com.everton.taskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.everton.taskapp.databinding.ActivityAdicionarTarefaBinding

class adicionarTarefa : AppCompatActivity() {

    private val binding by lazy {

        ActivityAdicionarTarefaBinding.inflate(layoutInflater)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}