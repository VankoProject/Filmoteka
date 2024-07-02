package com.kliachenko.filmoteka.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.kliachenko.filmoteka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = MainViewModel()

//        viewModel.init(savedInstanceState == null)

        binding.bottomNavMenu.setOnItemSelectedListener { item ->
            val selectedItem = binding.bottomNavMenu.menu.children.indexOf(item)
//            viewModel.switchTab(selectedItem)
            true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(BOTTOM_TAB_KEY, binding.bottomNavMenu.id)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val selectedTabId = savedInstanceState.getInt(BOTTOM_TAB_KEY)
        binding.bottomNavMenu.selectedItemId = selectedTabId
    }

    companion object {
        private const val BOTTOM_TAB_KEY = "selectedTab"
    }
}