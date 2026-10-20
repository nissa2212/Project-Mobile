package com.example.pertemuan2.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.pertemuan2.R

class AnakAdapter(
    private val context: Context,
    private val listAnak: List<AnakModel>
) : BaseAdapter() {

    override fun getCount(): Int = listAnak.size

    override fun getItem(position: Int): Any = listAnak[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_anak_hilang, parent, false)

        // ID menggunakan akhiran Item sesuai file XML kamu
        val imgAnak = view.findViewById<ImageView>(R.id.imgAnakItem)
        val tvNama = view.findViewById<TextView>(R.id.tvNamaAnakItem)
        val tvDetail = view.findViewById<TextView>(R.id.tvDetailAnakItem)
        val tvStatus = view.findViewById<TextView>(R.id.tvStatusItem)

        val item = listAnak[position]

        tvNama.text = item.nama
        tvDetail.text = item.detail
        tvStatus.text = item.status

        // Memuat gambar dari URL online pravatar.cc
        Glide.with(context)
            .load(item.imageUrl)
//            .placeholder(R.drawable.girl)
//            .error(R.drawable.girl)
            .into(imgAnak)

        return view
    }
}