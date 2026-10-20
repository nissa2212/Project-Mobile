package com.example.pertemuan2

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pertemuan2.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Atur inset agar Toolbar tidak menabrak status bar atas
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.main.setPadding(0, systemBars.top, 0, systemBars.bottom)
            insets
        }

        // Setup Toolbar & Tombol Back
        binding.toolbarWebView.setNavigationIcon(android.R.drawable.ic_menu_revert)
        binding.toolbarWebView.setNavigationOnClickListener {
            finish()
        }

        // Setup WebView
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.useWideViewPort = true
        binding.webView.settings.loadWithOverviewMode = true
        binding.webView.settings.builtInZoomControls = true
        binding.webView.settings.displayZoomControls = false
        binding.webView.settings.domStorageEnabled = true

        // Membuka situs web yang responsif
        binding.webView.loadUrl("https://www.google.com")
    }
}