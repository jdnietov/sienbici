package co.edu.unal.sienbici.models

data class Bike(
    val type: String = "",
    val color: String = "",
    val brand: String = "",
    val ref: String = "",
    val serial: String = "",
    val diameter: Int = -1,
    val reported: Boolean = false
)