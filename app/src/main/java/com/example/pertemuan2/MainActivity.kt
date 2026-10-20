package com.example.pertemuan2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.pertemuan2.databinding.ActivityMainBinding
import com.example.pertemuan2.home.HomeFragment
import com.example.pertemuan2.notifikasi.NotifikasiFragment
import com.example.pertemuan2.peta.PetaFragment
import com.example.pertemuan2.posting.PostingFragment
import com.example.pertemuan2.profil.ProfilFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bikin padding status bar di atas, tapi hilangkan padding sistem di bawah root layout
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Atur padding atas seukuran status bar agar Home tidak menabrak jam
            binding.main.setPadding(0, systemBars.top, 0, 0)

            // Atur padding bawah khusus untuk BottomNavigationView agar posisinya pas flush di dasar layar
            binding.bottomNavigationView.setPadding(0, 0, 0, systemBars.bottom)

            insets
        }

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.nav_map -> {
                    replaceFragment(PetaFragment())
                    true
                }
                R.id.nav_add -> {
                    replaceFragment(PostingFragment())
                    true
                }
                R.id.nav_notif -> {
                    replaceFragment(NotifikasiFragment())
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(ProfilFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}