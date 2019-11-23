package co.edu.unal.sienbici.models

import java.time.LocalDateTime

data class Robbery(
    val bikeId: String = "",
    val brand: String = "",
    val ref: String = "",
    val color: String = "",
    val place: String = "",
    val img: Int = -1,
    val date: LocalDateTime = LocalDateTime.now()
)