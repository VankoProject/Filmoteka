package com.kliachenko.dashboard

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.kliachenko.core.modules.Clear
import com.kliachenko.dashboard.domain.DashboardInteractor
import com.kliachenko.dashboard.domain.LoadResult
import com.kliachenko.dashboard.presentation.DashboardCommunication
import com.kliachenko.dashboard.presentation.DashboardUiState
import com.kliachenko.dashboard.presentation.DashboardViewModel
import com.kliachenko.dashboard.presentation.TabIdToCategoryMapper
import com.kliachenko.dashboard.presentation.adapter.DashboardUi
import com.kliachenko.domain.DashboardItem
import com.kliachenko.domain.FilmDashboardDomain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DashboardViewModelTest {

    private lateinit var interactor: FakeDashboardInteractor
    private lateinit var communication: FakeDashboardCommunication
    private lateinit var clear: FakeClear
    private lateinit var viewModel: DashboardViewModel
    private lateinit var uiMapper: FakeDashboardResultMapper
    private lateinit var itemMapper: FakeDashboardItemMapper
    private lateinit var categoryMapper: TabIdToCategoryMapper
    private lateinit var runAsync: FakeRunAsync

    @Before
    fun setup() {
        interactor = FakeDashboardInteractor()
        communication = FakeDashboardCommunication()
        clear = FakeClear()
        itemMapper = FakeDashboardItemMapper()
        uiMapper = FakeDashboardResultMapper(itemMapper)
        categoryMapper = TabIdToCategoryMapper.Base
        runAsync = FakeRunAsync()
        viewModel = DashboardViewModel(
            interactor = interactor,
            communication = communication,
            clear = clear,
            uiMapper = uiMapper,
            categoryMapper = categoryMapper,
            runAsync = runAsync
        )
    }

    @Test
    fun successWhenFirstRun() {
        interactor.hasData()
        viewModel.init(firstRun = true, tabPosition = 0)
        runAsync.returnLoadResult()
        communication.checkUpdatedState(
            DashboardUiState.Progress,
            DashboardUiState.FilmsList(
                listOf(
                    DashboardUi.Film(filmId = 0, overView = "film0", imageUrl = "film0", releaseDate = "0.0", title = "film0", rate = 0.0, isFavorite = false),
                    DashboardUi.Film(filmId = 1, overView = "film1", imageUrl = "film1", releaseDate = "1.0", title = "film1", rate = 1.0, isFavorite = false
                    )
                )
            )
        )
    }

    @Test
    fun errorThenSuccessWhenFirstRun() {
        interactor.hasError()
        viewModel.init(firstRun = true, tabPosition = 0)
        runAsync.returnLoadResult()
        communication.checkUpdatedState(
            DashboardUiState.Progress,
            DashboardUiState.Error("No internet connection")
        )
        interactor.hasData()
        viewModel.retry()
        runAsync.returnLoadResult()
        communication.checkUpdatedState(
            DashboardUiState.Progress,
            DashboardUiState.Error("No internet connection"),
            DashboardUiState.Progress,
            DashboardUiState.FilmsList(
                listOf(
                    DashboardUi.Film(filmId = 0, overView = "film0", imageUrl = "film0", releaseDate = "0.0", title = "film0", rate = 0.0, isFavorite = false
                    ),
                    DashboardUi.Film(filmId = 1, overView = "film1", imageUrl = "film1", releaseDate = "1.0", title = "film1", rate = 1.0, isFavorite = false
                    )
                )
            )
        )
    }

    @Test
    fun successLoadBetweenTabsNotFirstRun() {
        interactor.hasData()
        viewModel.init(firstRun = false, tabPosition = 1)
        runAsync.returnLoadResult()
        viewModel.loadData(tabPosition = 1)
        runAsync.returnLoadResult()
        communication.checkUpdatedState(
            DashboardUiState.Progress,
            DashboardUiState.FilmsList(
                listOf(
                    DashboardUi.Film(filmId = 2, overView = "film2", imageUrl = "film2", releaseDate = "2.0", title = "film2", rate = 2.0, isFavorite = false
                    ),
                    DashboardUi.Film(filmId = 3, overView = "film3", imageUrl = "film3", releaseDate = "3.0", title = "film3", rate = 3.0, isFavorite = false
                    )
                )
            )
        )
        viewModel.loadData(tabPosition = 0)
        runAsync.returnLoadResult()
        communication.checkUpdatedState(
            DashboardUiState.Progress,
            DashboardUiState.FilmsList(
                listOf(
                    DashboardUi.Film(filmId = 2, overView = "film2", imageUrl = "film2", releaseDate = "2.0", title = "film2", rate = 2.0, isFavorite = false
                    ),
                    DashboardUi.Film(filmId = 3, overView = "film3", imageUrl = "film3", releaseDate = "3.0", title = "film3", rate = 3.0, isFavorite = false
                    )
                )
            ),
            DashboardUiState.Progress,
            DashboardUiState.FilmsList(
                listOf(
                    DashboardUi.Film(filmId = 0, overView = "film0", imageUrl = "film0", releaseDate = "0.0", title = "film0", rate = 0.0, isFavorite = false
                    ),
                    DashboardUi.Film(filmId = 1, overView = "film1", imageUrl = "film1", releaseDate = "1.0", title = "film1", rate = 1.0, isFavorite = false
                    )
                )
            )
        )
    }

    @Test
    fun addToFavoriteThenRemove() {
        val favFilm = DashboardUi.Film(filmId = 0, overView = "film0", imageUrl = "film0", releaseDate = "0.0", title = "film0", rate = 0.0, isFavorite = false)
        viewModel.add(item = favFilm)
        interactor.checkAddedFilmToFavorite(expectedId = 0)
    }

    @Test
    fun removeFromFavorite() {
        viewModel.remove(item = DashboardUi.Film(filmId = 0, overView = "film0", imageUrl = "film0", releaseDate = "0.0", title = "film0", rate = 0.0, isFavorite = true))
        interactor.checkRemovedFilmId(expectedId = 0)
    }

    @Test
    fun clearViewModel() {
        viewModel.clear(this.viewModel.javaClass)
        clear.checkClearCalled(DashboardViewModel::class.java)
    }

}

private class FakeDashboardInteractor : DashboardInteractor {

    private var actualLoadResult: LoadResult = LoadResult.Empty
    private var filmsByCategoryResult: MutableMap<String, LoadResult> = mutableMapOf()
    private var addedFilmId: Int? = null
    private var removedFilmsId: Int? = null

    override suspend fun addToFavorite(filmId: Int) {
        addedFilmId = filmId
    }

    override suspend fun removeFromFavorites(filmId: Int) {
        removedFilmsId = filmId
    }

    override suspend fun needToLoadMoreData(
        lastVisibleItemPosition: Int,
        category: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun loadDataByPage(category: String): LoadResult {
        TODO("Not yet implemented")
    }

    override suspend fun loadDataByCategory(category: String): List<FilmDashboardDomain> {
        TODO("Not yet implemented")
    }

    override suspend fun loadCacheByCategory(category: String): LoadResult {
        TODO("Not yet implemented")
    }

    override suspend fun loadMoreFilms(category: String): LoadResult {
        TODO("Not yet implemented")
    }

    override suspend fun loadPreviousFilms(category: String): LoadResult {
        TODO("Not yet implemented")
    }

    fun categoryResult(category: String, result: LoadResult) {
        filmsByCategoryResult[category] = result
    }

    fun hasData() {
        categoryResult(
            "popular", LoadResult.Success(
                items = listOf(
                    FilmDashboardDomain(id = 0, overview = "film0", posterPath = "film0", releaseDate = "0.0", title = "film0", voteAverage = 0.0
                    ),
                    FilmDashboardDomain(id = 1, overview = "film1", posterPath = "film1", releaseDate = "1.0", title = "film1", voteAverage = 1.0
                    )
                ), favoriteFilmIds = setOf()
            )
        )
        categoryResult(
            "top_rated",
            LoadResult.Success(
                items = listOf(
                    FilmDashboardDomain(id = 2, overview = "film2", posterPath = "film2", releaseDate = "2.0", title = "film2", voteAverage = 2.0
                    ),
                    FilmDashboardDomain(id = 3, overview = "film3", posterPath = "film3", releaseDate = "3.0", title = "film3", voteAverage = 3.0
                    )
                ), favoriteFilmIds = setOf()
            )
        )
    }

    fun hasError() {
        actualLoadResult = LoadResult.Error(message = "No internet connection")
    }

    fun checkAddedFilmToFavorite(expectedId: Int) {
        assertEquals(expectedId, addedFilmId)
    }

    fun checkRemovedFilmId(expectedId: Int) {
        assertEquals(expectedId, removedFilmsId)
    }

}

private class FakeDashboardCommunication : DashboardCommunication {

    private var actualUiStateList: MutableList<DashboardUiState> = mutableListOf()
    private var latestState: DashboardUiState = DashboardUiState.Empty

    override fun liveData(): LiveData<DashboardUiState> {
        throw IllegalStateException("not used in tests")
    }

    override fun update(value: DashboardUiState) {
        latestState = value
        actualUiStateList.add(value)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<DashboardUiState>) {
        TODO("Not yet implemented")
    }

    fun checkUpdatedState(vararg expected: DashboardUiState) {
        assertEquals(expected.toList(), actualUiStateList)
    }

    fun latestState(): DashboardUiState {
        return latestState
    }

}

private class FakeClear : Clear {

    private lateinit var actual: Class<out ViewModel>

    override fun clear(viewModelClass: Class<out ViewModel>) {
        actual = viewModelClass
    }

    fun checkClearCalled(expected: Class<out ViewModel>) {
        assertEquals(expected, actual)
    }

}

private class FakeDashboardResultMapper(
    private val mapper: FakeDashboardItemMapper,
) : LoadResult.Mapper<DashboardUiState> {

    private var resultUiState: DashboardUiState = DashboardUiState.Empty

    override fun mapSuccess(items: List<FilmDashboardDomain>, favoriteFilmIds: Set<Int>): DashboardUiState {
        val uiItems = items.map { filmDomain ->
            filmDomain.map(mapper, favoriteFilmIds.contains(filmDomain.id()))
        }
        resultUiState = DashboardUiState.FilmsList(uiItems)
        return resultUiState
    }

    override fun mapError(message: String): DashboardUiState {
        resultUiState = DashboardUiState.Error(message)
        return resultUiState
    }

    override fun mapEmpty(): DashboardUiState {
        resultUiState = DashboardUiState.Empty
        return resultUiState
    }

    override fun mapNoData(message: String): DashboardUiState {
        resultUiState = DashboardUiState.NoData
        return resultUiState
    }

}

private class FakeDashboardItemMapper : DashboardItem.Mapper<DashboardUi> {

    override fun mapItems(
        id: Int,
        overview: String,
        posterPath: String,
        releaseDate: String,
        title: String,
        voteAverage: Double,
        isFavorite: Boolean,
    ): DashboardUi {
        val uiItem = DashboardUi.Film(
            filmId = id,
            overView = overview,
            imageUrl = posterPath,
            releaseDate = releaseDate,
            title = title,
            rate = voteAverage,
            isFavorite = isFavorite
        )
        return uiItem
    }
}



