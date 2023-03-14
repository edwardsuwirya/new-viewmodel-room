package com.enigmacamp.myviewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/*
Penggunaan umum yang biasanya dilakukan dengan viewmodel
1. Mempersiapkan data untuk layer UI (Pemisahan antara UI dan data bisnis proses)
2. Menangani perubahan konfigurasi karena rotasi layar

Ketika device dilakukan rotasi orientasi, maka UI akan di-recreate, sehingga
data sementara yang ada di memory akan dihapus
ViewModel memiliki lifecycle nya sendiri, terlepas dari lifecycle Activity/Fragment
dimana lifecycle dari viewmodel, tetap mempertahankan siklus hidupnya ketika terjadi
screen rotation

 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Cara yang salah melakukan create object viewmodel
//        val viewModel = MainActivityVM()

        val viewModel = ViewModelProvider(this)[MainActivityVM::class.java]

        val btnSet = findViewById<Button>(R.id.btnSet)
        val btnGet = findViewById<Button>(R.id.btnGet)

        btnSet.setOnClickListener {
            viewModel.totalBlogs += 1
        }
        btnGet.setOnClickListener {
            Log.d("Main-Activity", viewModel.totalBlogs.toString())
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
//            finish() akan menyebabkan viewmodel di clear
//            finish()
        }

    }
}