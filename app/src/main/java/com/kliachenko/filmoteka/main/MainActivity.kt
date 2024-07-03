package com.kliachenko.filmoteka.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.kliachenko.filmoteka.R
import com.kliachenko.filmoteka.databinding.ActivityMainBinding
import com.kliachenko.filmoteka.navigation.NavigationCommunication

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHost.navController

        NavigationUI.setupWithNavController(binding.bottomNavMenu, navController)

        val viewModel = MainViewModel(
            NavigationCommunication.Base(navController = navController),
            MainCommunication.Base,
            listOf(R.id.dashboard, R.id.search, R.id.favorites)
        )

        viewModel.init(savedInstanceState == null)

        binding.bottomNavMenu.setOnItemSelectedListener { item ->
            val selectedItem = binding.bottomNavMenu.menu.children.indexOf(item)
            viewModel.switchTab(selectedItem)
            true
        }

        viewModel.liveData().observe(this) {
            it.updateState(binding.bottomNavMenu)
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