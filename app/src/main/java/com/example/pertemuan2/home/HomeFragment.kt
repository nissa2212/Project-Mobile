package com.example.pertemuan2.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pertemuan2.DetailActivity
import com.example.pertemuan2.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // SharedPreferences & Nama User
        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val nama = requireActivity().intent.getStringExtra("nama") ?: sharedPref.getString("username", "User")
        Log.w("hasil", "nama: $nama")

        binding.txtNama.text = "Selamat Datang, $nama!"

        // Poin 2: Menampilkan data Anak Hilang ke ListView
        val dataAnak = listOf(
            AnakModel("Aisyah Putri", "7 tahun • Perempuan\nJakarta Selatan", "Hilang 2 hari lalu", "https://picsum.photos/id/1025/200/200"),
            AnakModel("Budi Santoso", "9 tahun • Laki-laki\nBandung", "Hilang 1 hari lalu", "https://picsum.photos/id/1012/200/200"),
            AnakModel("Citra Lestari", "5 tahun • Perempuan\nSurabaya", "Hilang 3 hari lalu", "https://picsum.photos/id/1027/200/200")
        )

        val adapter = AnakAdapter(requireContext(), dataAnak)
        binding.listViewAnak.adapter = adapter

        // BARIS PENTING: Mencegah ScrollView luar memotong usapan jari saat menggeser ListView
        binding.listViewAnak.setOnTouchListener { v, _ ->
            v.parent.requestDisallowInterceptTouchEvent(true)
            false
        }

        // Klik item di ListView untuk membuka DetailActivity
        binding.listViewAnak.setOnItemClickListener { _, _, _, _ ->
            val intentDetail = Intent(requireContext(), DetailActivity::class.java)
            startActivity(intentDetail)
        }

        // Tombol Snackbar
        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(binding.root, "Anda akan dialihkan ke aplikasi pihak ketiga untuk melanjutkan proses.", Snackbar.LENGTH_SHORT)
                .setAction("Buka") {
                    Log.e("Info Snackbar", "Snackbar ditutup")
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}