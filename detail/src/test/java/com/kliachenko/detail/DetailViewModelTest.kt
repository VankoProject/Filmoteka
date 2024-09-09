package com.kliachenko.detail

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.kliachenko.core.RunAsync
import com.kliachenko.core.modules.Clear
import com.kliachenko.detail.domain.DetailInteractor
import com.kliachenko.detail.domain.LoadResult
import com.kliachenko.detail.presentation.DetailCommunication
import com.kliachenko.detail.presentation.DetailUiState
import com.kliachenko.detail.presentation.DetailViewModel
import com.kliachenko.detail.presentation.FilmDetailUi
import com.kliachenko.detail.presentation.NavigationCommunication
import com.kliachenko.domain.FilmDetailDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var interactor: FakeDetailInteractor
    private lateinit var communication: FakeDetailCommunication
    private lateinit var navigation: FakeNavigationCommunication
    private lateinit var uiMapper: FakeDetailResultMapper
    private lateinit var clear: FakeClear
    private lateinit var runAsync: FakeRunAsync
    private lateinit var viewModel: DetailViewModel

    @Before
    fun setup() {
        interactor = FakeDetailInteractor()
        communication = FakeDetailCommunication()
        uiMapper = FakeDetailResultMapper()
        clear = FakeClear()
        runAsync = FakeRunAsync()
        viewModel = DetailViewModel(
            interactor,
            communication,
            navigation,
            uiMapper,
            clear,
            runAsync
        )
    }

    @Test
    fun error_then_success_then_add_then_remove_then_goBack() {
        val filmDetail = FilmDetailUi.FilmDetail(
            filmId = 0,
            adult = false,
            genres = listOf("Action", "Comedy"),
            homePage = "https://www.amazon.com/",
            productionCountries = listOf("USA"),
            originalLanguage = "en",
            title = "Film#0",
            overview = "Overview#0",
            posterPath = "path#0",
            releaseDate = "2020",
            runtime = 100,
            tagline = "Tagline#0",
            voteAverage = 5.0,
            voteCount = 10,
        )
        interactor.expectedResult(LoadResult.Error("No internet connection"))
        viewModel.init(filmId = 0)
        communication.checkUpdateStates(
            DetailUiState.Progress
        )
        runAsync.returnLoadResult()
        communication.checkUpdateStates(
            DetailUiState.Progress,
            DetailUiState.Error("No internet connection")
        )
        viewModel.retry()
        interactor.expectedResult(LoadResult.Success(
            FilmDetailDomain(
                filmId = 0,
                adult = false,
                genres = listOf("Action", "Comedy"),
                homePage = "https://www.amazon.com/",
                productionCountries = listOf("USA"),
                originalLanguage = "en",
                title = "Film#0",
                overview = "Overview#0",
                posterPath = "path#0",
                releaseDate = "2020",
                runtime = 100,
                tagline = "Tagline#0",
                voteAverage = 5.0,
                voteCount = 10,
        ), isFavorite = false))
        runAsync.returnLoadResult()
        communication.checkUpdateStates(
            DetailUiState.Progress,
            DetailUiState.Error("No internet connection"),
            DetailUiState.Progress,
            DetailUiState.Success(filmDetail, false)
        )

        viewModel.changeStatus(filmId = 0)
        interactor.checkAddToFavoriteCalled()

        viewModel.changeStatus(filmId = 0)
        interactor.checkRemoveFromFavoritesCalled()

        clear.checkClearCalled(this.viewModel.javaClass)
    }
}

class FakeNavigationCommunication: NavigationCommunication {

    override fun liveData(): LiveData<Unit> {
        TODO("Not yet implemented")
    }

    override fun update(value: Unit) {
        TODO("Not yet implemented")
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<Unit>) {
        TODO("Not yet implemented")
    }

}

private class FakeDetailResultMapper : LoadResult.Mapper<DetailUiState> {

    val filmDetail = FilmDetailUi.FilmDetail(
        filmId = 0,
        adult = false,
        genres = listOf("Action", "Comedy"),
        homePage = "https://www.amazon.com/",
        productionCountries = listOf("USA"),
        originalLanguage = "en",
        title = "Film#0",
        overview = "Overview#0",
        posterPath = "path#0",
        releaseDate = "2020",
        runtime = 100,
        tagline = "Tagline#0",
        voteAverage = 5.0,
        voteCount = 10,
    )


    override fun mapSuccess(item: FilmDetailDomain, isFavorite: Boolean): DetailUiState {
        TODO("Not yet implemented")
    }

    override fun mapError(message: String): DetailUiState {
        return DetailUiState.Error(message)
    }

    override fun mapEmpty(): DetailUiState {
        return DetailUiState.Empty
    }

}

private class FakeDetailCommunication : DetailCommunication {

    private var actualUiStateList: MutableList<DetailUiState> = mutableListOf()

    override fun liveData(): LiveData<DetailUiState> {
        throw IllegalStateException("not used in tests")
    }

    override fun update(value: DetailUiState) {
        actualUiStateList.add(value)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<DetailUiState>) {}

    fun checkUpdateStates(vararg expected: DetailUiState) {
        assertEquals(expected.toList(), actualUiStateList)
    }

}

@Suppress("UNCHECKED_CAST")
private class FakeRunAsync : RunAsync {

    private var actualCacheUiBlock: (Any) -> Unit = {}
    private var actualCacheResult: Any = ""

    override fun <T : Any> start(
        coroutineScope: CoroutineScope,
        background: suspend () -> T,
        uiBlock: (T) -> Unit,
    ) = runBlocking {
        actualCacheResult = background.invoke()
        actualCacheUiBlock = uiBlock as (Any) -> Unit
    }

    fun returnLoadResult() {
        actualCacheUiBlock.invoke(actualCacheResult)
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

private class FakeDetailInteractor : DetailInteractor {

    private var actual: LoadResult = LoadResult.Empty
    private var addFavoriteCalled: Boolean = false
    private var removeFavoriteCalled: Boolean = false


    override suspend fun filmDetail(filmId: Int): LoadResult {
        return actual
    }

    override suspend fun addToFavorite(filmId: Int) {
        addFavoriteCalled = true
    }

    override suspend fun removeFromFavorite(filmId: Int) {
        removeFavoriteCalled = true
    }

    override suspend fun isFavorite(filmId: Int): Boolean {
        // TODO: add code
    }

    fun expectedResult(result: LoadResult) {
        actual = result
    }

    fun checkAddToFavoriteCalled() {
        assertTrue(addFavoriteCalled)
    }

    fun checkRemoveFromFavoritesCalled() {
        assertTrue(removeFavoriteCalled)
    }

}
