//package com.kliachenko.filmoteka
//
//import androidx.lifecycle.LiveData
//import androidx.navigation.NavDirections
//import com.kliachenko.core.Communication
//import com.kliachenko.filmoteka.main.MainCommunication
//import com.kliachenko.filmoteka.main.MainUiState
//import com.kliachenko.filmoteka.main.MainViewModel
//import com.kliachenko.filmoteka.navigation.NavigationCommunication
//import org.junit.Assert
//import org.junit.Before
//import org.junit.Test
//
//class MainViewModelTest {
//
//    private lateinit var viewModel: MainViewModel
//    private lateinit var navigationCommunication: FakeNavigationCommunication
//    private lateinit var communication: FakeCommunication
//
//    @Before
//    fun setup() {
//        navigationCommunication = FakeNavigationCommunication()
//        communication = FakeCommunication()
//        viewModel = MainViewModel(
//            navigationCommunication, communication,
//            listOf(R.id.dashboardFragment, R.id.searchFragment, R.id.favoritesFragment)
//        )
//    }
//
//    @Test
//    fun firstRun() {
//        viewModel.init(firstRun = true)
//        navigationCommunication.checkNavigate(expectedScreen = R.id.dashboardFragment)
//    }
//
//    @Test
//    fun notFirstRun() {
//        navigationCommunication.navigateTo(R.id.favoritesFragment)
//        viewModel.init(firstRun = false)
//        navigationCommunication.checkNavigate(expectedScreen = R.id.favoritesFragment)
//    }
//
//    @Test
//    fun navigateToSearchScreen() {
//        viewModel.switchTab(selectedItem = INDEX_SEARCH)
//        navigationCommunication.checkNavigate(expectedScreen = R.id.searchFragment)
//    }
//
//    @Test
//    fun navigateToFavorites() {
//        viewModel.switchTab(selectedItem = INDEX_FAVORITES)
//        navigationCommunication.checkNavigate(expectedScreen = R.id.favoritesFragment)
//    }
//
//    @Test
//    fun navigateToDashboard() {
//        viewModel.switchTab(selectedItem = INDEX_DASHBOARD)
//        navigationCommunication.checkNavigate(expectedScreen = R.id.dashboardFragment)
//    }
//
//    companion object {
//        private const val INDEX_DASHBOARD = 0
//        private const val INDEX_SEARCH = 1
//        private const val INDEX_FAVORITES = 2
//    }
//}
//
//private class FakeCommunication : MainCommunication {
//
//    override fun liveData(): LiveData<MainUiState> {
//
//    }
//
//    override fun update(value: MainUiState) {
//
//    }
//
//}
//
//private class FakeNavigationCommunication : NavigationCommunication {
//
//    private var actualScreen: Int? = null
//    private val navigationHistory = mutableListOf<Int>()
//
//    override fun navigateTo(destinationId: Int) {
//        actualScreen = destinationId
//        navigationHistory.add(destinationId)
//    }
//
//    override fun navigateTo(directions: NavDirections) {
//        actualScreen = directions.actionId
//        navigationHistory.add(directions.actionId)
//    }
//
//    override fun popBackStack() = true
//
//    fun actualScreen() = actualScreen
//
//    fun clearNavigationHistory() {
//        navigationHistory.clear()
//    }
//
//    fun checkNavigate(expectedScreen: Int) {
//        Assert.assertEquals(expectedScreen, actualScreen())
//    }
//
//}
