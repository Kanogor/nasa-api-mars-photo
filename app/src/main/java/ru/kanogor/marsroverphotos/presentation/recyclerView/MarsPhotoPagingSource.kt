package ru.kanogor.marsroverphotos.presentation.recyclerView

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.kanogor.marsroverphotos.domain.GetMarsPhotoUseCase
import ru.kanogor.marsroverphotos.entity.Photos

class MarsPhotoPagingSource(private val getMarsPhotoUseCase: GetMarsPhotoUseCase) :
    PagingSource<Int, Photos>() {

    override fun getRefreshKey(state: PagingState<Int, Photos>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photos> {
        val page = params.key ?: 1
        return kotlin.runCatching {
                getMarsPhotoUseCase.execute(page)
            }.fold(
                onSuccess = {
                    LoadResult.Page(
                        data = it,
                        prevKey = null,
                        nextKey = if (it.isEmpty()) null else {
                            page + 1
                        }
                    )
                },
                onFailure = { LoadResult.Error(it) }
            )
    }

    private companion object {
        private const val FIRST_PAGE = 1
    }

}