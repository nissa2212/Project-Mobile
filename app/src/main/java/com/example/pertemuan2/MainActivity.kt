package com.example.pertemuan2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pertemuan2.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- PENERAPAN TOOLBAR SESUAI CARA DOSEN ---
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Beranda Utama"
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }

        // PERBAIKAN: Menggunakan "user_pref" agar sinkron dengan SplashScreenActivity & LoginActivity
        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val nama = intent.getStringExtra("nama") ?: sharedPref.getString("username", "User")
        Log.w("hasil", "nama: $nama")

        binding.txtNama.text = "Selamat Datang, $nama!"

        binding.btnDetail.setOnClickListener {
            val intentDetail = Intent(this@MainActivity, DetailActivity::class.java)
            startActivity(intentDetail)
        }

        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi LogOut")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    // PERBAIKAN: Menghapus session dari "user_pref" secara permanen
                    val editor = sharedPref.edit()
                    editor.putBoolean("isLogin", false)
                    editor.clear()
                    editor.apply()

                    // Pindah ke LoginActivity
                    val intentLogin = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intentLogin)
                    finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(binding.root, "Anda akan dialihkan ke aplikasi pihak ketiga untuk melanjutkan proses.", Snackbar.LENGTH_SHORT)
                .setAction("Buka") {
                    Log.e("Info Snackbar", "Snackbar ditutup")
                }
                .show()
        }
    }
}