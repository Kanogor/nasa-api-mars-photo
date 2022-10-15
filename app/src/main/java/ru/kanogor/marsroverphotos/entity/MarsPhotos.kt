package ru.kanogor.marsroverphotos.entity

interface MarsPhotos {
    val photos: List<Photos>
}

interface Photos {
    val id: Int
    val sol: Int
    val camera: Camera
    val img_src: String
    val earth_date: String
    val rover: Rover
}

interface Rover {
    val id: Int
    val name: String
    val landing_date: String
    val launch_date: String
    val status: String
}

interface Camera {
    val id: Int
    val name: String
    val rover_id: Int
    val full_name: String
}