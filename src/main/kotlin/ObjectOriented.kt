class Car(
    val wheelRightFront: Wheel,
    val wheelLeftFront: Wheel,
    val wheelRightRear: Wheel,
    val wheelLeftRear: Wheel,
    var remainingKilometersToService: Long = 20000
) {
    fun drive(kilometers: Long) {
        remainingKilometersToService -= kilometers
    }

    fun service(): String {
        remainingKilometersToService = 20000
        return "Service completed!"
    }
}


data class Wheel(
    val make: String,
    val size: Int,
    val season: Season,
)

enum class Season {
    WINTER,
    SUMMER
}



