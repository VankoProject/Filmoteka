package com.kliachenko.filmoteka.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.kliachenko.core.modules.ProvideViewModel
import com.kliachenko.core.navigation.NavigationActions
import com.kliachenko.filmoteka.R
import com.kliachenko.filmoteka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProvideViewModel, NavigationActions.FromDashboard,
    NavigationActions.FromSearch {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = viewModel(MainViewModel::class.java)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHost.navController

        NavigationUI.setupWithNavController(binding.bottomNavMenu, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.detailFragment -> {
                    viewModel.secondaryUiState()
                }

                else -> viewModel.mainUiState()
            }
        }

        viewModel.init()

        viewModel.liveData().observe(this) {
            it.updateState(binding.bottomNavMenu)
        }
    }

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return (application as ProvideViewModel).viewModel(viewModelClass)
    }

    override fun navigateFromDashboardToDetail(filmId: Int, filmTitle: String) {
        val bundle = Bundle().apply {
            putInt("filmId", filmId)
            putString("filmTitle", filmTitle)
        }
        navController.navigate(R.id.action_dashboardFragment_to_detailFragment, bundle)
    }

    override fun navigateFromSearchToDetail(filmId: Int, filmTitle: String) {
        val bundle = Bundle().apply {
            putInt("filmId", filmId)
            putString("filmTitle", filmTitle)
        }
        navController.navigate(R.id.action_searchFragment_to_detailFragment, bundle)
    }


}