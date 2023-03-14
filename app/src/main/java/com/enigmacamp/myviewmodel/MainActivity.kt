package com.enigmacamp.myviewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.simpleviewmodel.ViewStatus

/*
Penggunaan umum yang biasanya dilakukan dengan viewmodel
1. Mempersiapkan data untuk layer UI (Pemisahan antara UI dan data bisnis proses)
2. Menangani perubahan konfigurasi karena rotasi layar

Ketika device dilakukan rotasi orientasi, maka UI akan di-recreate, sehingga
data sementara yang ada di memory akan dihapus
ViewModel memiliki lifecycle nya sendiri, terlepas dari lifecycle Activity/Fragment
dimana lifecycle dari viewmodel, tetap mempertahankan siklus hidupnya ketika terjadi
screen rotation

Bagaimana apabila class viewmodel kita memiliki dependency di constructor nya,
kita harus membuat sebuah object yang meng-implementasi-kan ViewModelProvider.Factory interface

Ada juga kegunaan viewmodel untuk sharing state antar fragment

LiveData merupakan salah satu komponen jetpack di bagian arsitektur
Sebuah class observable yang berfungsi sebagai data holder
LiveData dapat menangkap perubahan lifecycle (dapat melakukan perubahan data, ketika terjadi
perubahan lifecycle)

Didalam livedata, ada 2 hal yang perlu diperhatikan
1. Observer => melakukan perubahan data di UI
2. LifecycleOwner => pemilik dari lifecycle, apakah itu activity / fragment

Jika activity/fragment tidak aktif, maka oberserver tidak akan aktif

Bisa beradaptasi ketika terjadi screen rotation

Ada 2 jenis liveData, LiveData tidak bisa diubah value nya, MutableLiveData, bisa diubah value nya
Untuk merubah data digunakan function setValue() & postValue(), bedanya postValue digunakan untuk
merubah value ketika proses nya di background thread, sedangkan setValue untuk merubah data ketika
proses di main thread

ViewModel yang berfungsi mempertahankan state ketika terjadi screen rotation,
tidak bisa meng-handle data holder ketika terjadi killed system oleh android OS

Kita bisa memanfaatkan SavedInstanceState untuk mempertahankan state di saat killed system
simulasi process of death
1. Klik home di emulator, aplikasi akan pause di background
2. Buka terminal
 adb shell am kill com.enigmacamp.myviewmodel
3. Pastikan process aplikasi tidak ada
 adb shell ps | grep enigma
4. Masuk emulator lagi, jalankan ke foreground

Untuk menyimpan data, yang sederhana (Key-Value) kita bisa menggunakan
SharedPreferences
Path shared preferences
/data/data/com.enigmacamp.myviewmodel/shared_prefs
 */
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityVM
    private lateinit var btnGet: Button
    private lateinit var prefRepo: SharedPrefRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefRepo = SharedPrefRepository(applicationContext)
        initUI()
        initViewModel()
        Log.d("Application ID", BuildConfig.APPLICATION_ID)
        subscribe()
        viewModel.restoreState(savedInstanceState)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.saveState(outState)
    }


    private fun initUI() {
        val btnSet = findViewById<Button>(R.id.btnSet)
        btnGet = findViewById(R.id.btnGet)

        btnSet.setOnClickListener {
            viewModel.printStarRating()
            viewModel.saveUserPref()
            val isLoggedIn = viewModel.getUserPref()
            Log.d("Is-LoggedIn", isLoggedIn)
        }
    }

    private fun initViewModel() {

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainActivityVM(12, prefRepo) as T
            }
        })[MainActivityVM::class.java]
    }

    private fun subscribe() {
        viewModel.totalBlogsLiveData.observe(this) {
            when (it.status) {
                ViewStatus.SUCCESS -> btnGet.text = "Star Rating ${it.data}"
                ViewStatus.LOADING -> btnGet.text = "Loading..."
                ViewStatus.ERROR -> btnGet.text = "Oops"
            }
        }
    }
}