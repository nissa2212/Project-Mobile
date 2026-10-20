package com.example.pertemuan2.profil

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.pertemuan2.LoginActivity
import com.example.pertemuan2.WebViewActivity
import com.example.pertemuan2.databinding.FragmentProfilBinding

class ProfilFragment : Fragment() {

    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Memuat nama & email dari SharedPreferences[cite: 10]
        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val nama = sharedPref.getString("username", "Nanti")

        binding.tvNamaUserProfil.text = nama
        binding.tvEmailUserProfil.text = "${nama?.lowercase()?.replace(" ", "")}@email.com"

        // Event Klik Tiap Menu
        binding.btnMenuDataDiri.setOnClickListener {
            Toast.makeText(requireContext(), "Membuka Data Diri", Toast.LENGTH_SHORT).show()
        }

        binding.btnMenuLokasiKeamanan.setOnClickListener {
            Toast.makeText(requireContext(), "Membuka Lokasi & Keamanan", Toast.LENGTH_SHORT).show()
        }

        binding.btnMenuNotifikasi.setOnClickListener {
            Toast.makeText(requireContext(), "Membuka Pengaturan Notifikasi", Toast.LENGTH_SHORT).show()
        }

        binding.btnMenuBantuan.setOnClickListener {
            val intentWeb = Intent(requireContext(), WebViewActivity::class.java)
            startActivity(intentWeb)
        }

        binding.btnMenuTentang.setOnClickListener {
            Toast.makeText(requireContext(), "JejakAnak v1.0.0", Toast.LENGTH_SHORT).show()
        }

        // Tombol Keluar Merah[cite: 11]
        binding.btnKeluarProfil.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Konfirmasi Keluar")
                .setMessage("Apakah Anda yakin ingin keluar dari akun ini?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()

                    val intentLogin = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intentLogin)
                    requireActivity().finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}