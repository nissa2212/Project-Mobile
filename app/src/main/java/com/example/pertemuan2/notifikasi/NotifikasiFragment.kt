package com.example.pertemuan2.notifikasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pertemuan2.databinding.FragmentNotifikasiBinding

class NotifikasiFragment : Fragment() {

    private var _binding: FragmentNotifikasiBinding? = null
    private val binding get() = _binding!!

    private val notifList = listOf(
        "Laporan Baru: Anak hilang di wilayah Jakarta Selatan",
        "Pemberitahuan: Aisyah Putri telah ditemukan!",
        "Update Informasi: Ciri-ciri anak hilang diperbarui",
        "Edukasi: Tips menjaga anak di tempat keramaian",
        "Pengingat: Verifikasi akun pelapor Anda"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotifikasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, notifList)
        binding.listNotifItems.adapter = adapter

        binding.listNotifItems.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(requireContext(), "Membuka: ${notifList[position]}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}