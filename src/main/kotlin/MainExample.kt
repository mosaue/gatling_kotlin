fun main() {
    val standardWheel = Wheel(
        make = "NOKIAN",
        size = 18,
        season = Season.WINTER
    )

    val car = Car(
        wheelLeftFront = standardWheel,
        wheelRightFront = standardWheel.copy(),
        wheelLeftRear = standardWheel.copy(),
        wheelRightRear = standardWheel.copy()
    )

    car.drive(1000)
    println(car.remainingKilometersToService)
    car.service()
    println(car.remainingKilometersToService)
}