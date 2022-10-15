package ru.kanogor.marsroverphotos.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.kanogor.marsroverphotos.entity.Camera
import ru.kanogor.marsroverphotos.entity.MarsPhotos
import ru.kanogor.marsroverphotos.entity.Photos
import ru.kanogor.marsroverphotos.entity.Rover


@JsonClass(generateAdapter = true)
data class MarsPhotosDto(
    @Json(name = "photos")
    override val photos: List<ru.kanogor.marsroverphotos.data.Photos>
) : MarsPhotos

@JsonClass(generateAdapter = true)
data class Photos(
    @Json(name = "id")
    override val id: Int,
    @Json(name = "sol")
    override val sol: Int,
    @Json(name = "camera")
    override val camera: ru.kanogor.marsroverphotos.data.Camera,
    @Json(name = "img_src")
    override val img_src: String,
    @Json(name = "earth_date")
    override val earth_date: String,
    @Json(name = "rover")
    override val rover: ru.kanogor.marsroverphotos.data.Rover
) : Photos

@JsonClass(generateAdapter = true)
data class Rover(
    @Json(name = "id")
    override val id: Int,
    @Json(name = "name")
    override val name: String,
    @Json(name = "landing_date")
    override val landing_date: String,
    @Json(name = "launch_date")
    override val launch_date: String,
    @Json(name = "status")
    override val status: String
) : Rover

@JsonClass(generateAdapter = true)
data class Camera(
    @Json(name = "id")
    override val id: Int,
    @Json(name = "name")
    override val name: String,
    @Json(name = "rover_id")
    override val rover_id: Int,
    @Json(name = "full_name")
    override val full_name: String
) : Camera