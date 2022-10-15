package ru.kanogor.marsroverphotos.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import ru.kanogor.marsroverphotos.domain.GetMarsPhotoUseCase
import ru.kanogor.marsroverphotos.entity.Photos
import ru.kanogor.marsroverphotos.presentation.recyclerView.MarsPhotoPagingSource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getMarsPhotoUseCase: GetMarsPhotoUseCase) :
    ViewModel() {

    var pagedPhotos: Flow<PagingData<Photos>> = Pager(
        config = PagingConfig(pageSize = 5),
        pagingSourceFactory = {
            MarsPhotoPagingSource(getMarsPhotoUseCase)
        }
    ).flow
}