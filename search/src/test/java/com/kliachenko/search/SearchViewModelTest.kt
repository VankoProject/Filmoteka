package com.kliachenko.search

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.kliachenko.core.RunAsync
import com.kliachenko.core.modules.Clear
import com.kliachenko.domain.FilmSearchDomain
import com.kliachenko.domain.SearchItem
import com.kliachenko.search.domain.LoadResult
import com.kliachenko.search.domain.SearchInteractor
import com.kliachenko.search.presentation.SearchCommunication
import com.kliachenko.search.presentation.SearchUiState
import com.kliachenko.search.presentation.SearchViewModel
import com.kliachenko.search.presentation.adapter.SearchUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class SearchViewModelTest {

    private lateinit var interactor: FakeSearchInteractor
    private lateinit var runAsync: FakeRunAsync
    private lateinit var communication: FakeCommunication
    private lateinit var uiMapper: FakeUiMapper
    private lateinit var itemMapper: FakeSearchItemMapper
    private lateinit var clear: FakeClear
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setup() {
        interactor = FakeSearchInteractor()
        communication = FakeCommunication()
        uiMapper = FakeUiMapper(itemMapper)
        clear = FakeClear()
        runAsync = FakeRunAsync()
        viewModel = SearchViewModel(
            interactor = interactor,
            communication = communication,
            uiMapper = uiMapper,
            clear = clear,
            runAsync = runAsync
        )
    }

    @Test
    fun testEmptyHistoryThenErrorThenSuccess() {
        interactor.emptyHistory()
        viewModel.init()
        communication.checkUpdateState(SearchUiState.Initial(titleMessage = "Start searching to see your history here"))
        runAsync.returnResult()
        communication.checkUpdateState(SearchUiState.Initial(titleMessage = "Start searching to see your history here"))
        interactor.hasError(error = "No internet connection")
        viewModel.search(query = "Film1")
        communication.checkUpdateState(SearchUiState.Progress(progressMessage = "Continue typing..."))
        runAsync.returnResult()
        communication.checkUpdateState(SearchUiState.Error(message = "No internet connection"))
        interactor.hasSearchResults(
            LoadResult.Success(
                listOf(
                    FilmSearchDomain(
                        filmId = 1,
                        posterPath = "film1",
                        title = "film1"
                    ),
                    FilmSearchDomain(
                        filmId = 2,
                        posterPath = "film21",
                        title = "film2"
                    )
                )
            )
        )
        viewModel.retry()
        communication.checkUpdateState(SearchUiState.Progress(progressMessage = "Searching..."))
        runAsync.returnResult()
        communication.checkUpdateState(
            SearchUiState.Success(
                listOf(
                    SearchUi.Success(
                        filmId = 1,
                        imageUrl = "film1",
                        title = "film1"
                    ),
                    SearchUi.Success(
                        filmId = 2,
                        imageUrl = "film2",
                        title = "film2"
                    ),
                )
            )
        )
    }

    @Test
    fun testInvalidTypeThenSuccess() {
        interactor.emptyHistory()
        viewModel.init()
        communication.checkUpdateState(SearchUiState.Initial(titleMessage = "Start searching to see your history here"))
        runAsync.returnResult()
        communication.checkUpdateState(SearchUiState.Initial(titleMessage = "Start searching to see your history here"))
        viewModel.search(query = "F")
        communication.checkUpdateState(SearchUiState.Progress(progressMessage = "Continue typing..."))
        viewModel.search(query = "Fi")
        communication.checkUpdateState(SearchUiState.Progress(progressMessage = "Continue typing..."))
        viewModel.search(query = "Fil")
        communication.checkUpdateState(SearchUiState.Progress(progressMessage = "Searching..."))
        runAsync.returnResult()
        communication.checkUpdateState(
            SearchUiState.Success(
                listOf(
                    SearchUi.Success(
                        filmId = 1,
                        imageUrl = "film1",
                        title = "film1"
                    ),
                    SearchUi.Success(
                        filmId = 2,
                        imageUrl = "film2",
                        title = "film2"
                    ),
                )
            )
        )
    }

    @Test
    fun testHasHistoryThenTapHistoryFilm() {

    }

    @Test
    fun clearViewModel() {
        viewModel.clear(this.viewModel.javaClass)
        clear.checkClearCalled(SearchViewModel::class.java)
    }

}

private class FakeCommunication : SearchCommunication {

    private var actualUiStateList: MutableList<SearchUiState> = mutableListOf()

    override fun liveData(): LiveData<SearchUiState> {
        throw IllegalStateException("not used in tests")
    }

    override fun update(value: SearchUiState) {
        actualUiStateList.add(value)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<SearchUiState>) {
        TODO("Not yet implemented")
    }

    fun checkUpdateState(vararg expected: SearchUiState) {
        assertEquals(expected.toList(), actualUiStateList.last())
    }

}

private class FakeSearchInteractor : SearchInteractor {

    private var actualLoadResult: LoadResult = LoadResult.Empty
    private var actualListHistoryFilms: MutableList<FilmSearchDomain> = mutableListOf()

    override suspend fun search(query: String): LoadResult {
        return actualLoadResult
    }

    override suspend fun loadHistory(): List<FilmSearchDomain> {
        return actualListHistoryFilms
    }

    override suspend fun saveFilmToHistory(filmSearchDomain: FilmSearchDomain) {
        actualListHistoryFilms.add(filmSearchDomain)
    }

    fun hasHistoryFilmsSearch(films: List<FilmSearchDomain>) {
        actualListHistoryFilms.addAll(films)
    }

    fun emptyHistory() {
        actualListHistoryFilms.clear()
    }

    fun hasSearchResults(results: LoadResult) {
        actualLoadResult = results
    }

    fun hasError(error: String) {
        actualLoadResult = LoadResult.Error(error)
    }

}

class FakeUiMapper(
    private val mapper: FakeSearchItemMapper,
) : LoadResult.Mapper<SearchUiState> {

    private var resultUiState: SearchUiState = SearchUiState.Empty

    override fun mapSuccess(items: List<FilmSearchDomain>): SearchUiState {
        val uiItems = items.map { filmDomain -> filmDomain.map(mapper) }
        resultUiState = SearchUiState.Success(uiItems)
        return resultUiState
    }

    override fun mapError(message: String): SearchUiState {
        resultUiState = SearchUiState.Error(message)
        return resultUiState
    }

    override fun mapEmpty(): SearchUiState {
        resultUiState = SearchUiState.Empty
        return resultUiState
    }
}

class FakeSearchItemMapper : SearchItem.Mapper<SearchUi> {

    override fun mapItem(filmId: Int, title: String, posterPath: String): SearchUi {
        return SearchUi.Success(
            filmId = filmId,
            title = title,
            imageUrl = posterPath
        )
    }

}

@Suppress("UNCHECKED_CAST")
private class FakeRunAsync : RunAsync {

    private var uiBlockCached: (Any) -> Unit = {}
    private var cache: Any = Unit

    override fun <T : Any> start(
        coroutineScope: CoroutineScope,
        background: suspend () -> T,
        uiBlock: (T) -> Unit,
    ) = runBlocking {
        val result = background.invoke()
        cache = result
        uiBlockCached = uiBlock as (Any) -> Unit
    }

    fun returnResult() {
        uiBlockCached.invoke(cache)
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
