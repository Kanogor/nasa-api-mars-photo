package ru.kanogor.marsroverphotos.data

import kotlinx.coroutines.delay
import ru.kanogor.marsroverphotos.entity.MarsPhotos

class MarsPhotoRepository(private val searchMarsPhoto: SearchMarsPhoto) {

    suspend fun getPhoto(page: Int): MarsPhotos {
        delay(2000)
        val getApi = searchMarsPhoto.getMarsPhoto(page)
        return getApi.body()!!
    }
}