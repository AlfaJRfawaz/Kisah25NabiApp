package com.fawaz.kisahnabiapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.fawaz.kisahnabiapp.R
import com.fawaz.kisahnabiapp.data.KisahNabiResponse
import com.fawaz.kisahnabiapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<KisahNabiResponse>(EXTRA_DATA)
        data?.let {
            binding.apply {
                Glide.with(this@DetailActivity,).load(data.imageUrl).into(detailImage)
                detailNama.text = data.name
                val lahir = "Lahir: " + data.thnKelahiran + "SM"
                detailTahun.text = lahir
                detailTempat.text = data.tmp
                val usia = "Usia: " + data.usia + " tahun"
                detailUsia.text = usia
                detailDesc.text = data.description
            }
        }
    }

    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}