package com.kliachenko.dashboard

import com.kliachenko.core.RunAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

@Suppress("UNCHECKED_CAST")
class FakeRunAsync : RunAsync {

    private var actualCacheUiBlock: (Any) -> Unit = {}
    private var actualCacheResult: Any = ""

    override fun <T : Any> start(
        coroutineScope: CoroutineScope,
        background: suspend () -> T,
        uiBlock: (T) -> Unit,
    ) = runBlocking {
        val result = background.invoke()
        actualCacheResult = result
        actualCacheUiBlock = uiBlock as (Any) -> Unit
    }

    fun returnLoadResult() {
        actualCacheUiBlock.invoke(actualCacheResult)
    }
}