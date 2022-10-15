package ru.kanogor.marsroverphotos.domain

import ru.kanogor.marsroverphotos.data.MarsPhotoRepository
import ru.kanogor.marsroverphotos.entity.Photos
import javax.inject.Inject

class GetMarsPhotoUseCase @Inject constructor(
    private val repository: MarsPhotoRepository
) {

    suspend fun execute(page: Int): List<Photos> {
        return repository.getPhoto(page).photos
    }
}