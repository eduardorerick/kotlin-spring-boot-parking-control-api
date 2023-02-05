package com.api.parkingcontrol.models

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Entity
@Table(name = "TB_PARKING_SPOT")
class ParkingSpotModel(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: UUID = UUID.randomUUID(),

    @Column(nullable = false) var registrationDate: LocalDateTime = LocalDateTime.now(ZoneId.of("UTC")),
) {

    companion object {
        const val serialVersionUID: Long = 1L
    }


    @Column(nullable = false, unique = true, length = 10)
    var parkingSpotNumber: String? = null

    @Column(nullable = false, unique = true, length = 7)
    var licensePlateCar: String? = null

    @Column(nullable = false, length = 70)
    var brandCar: String? = null

    @Column(nullable = false, length = 70)
    var modelCar: String? = null

    @Column(nullable = false, length = 70)
    var colorCar: String? = null


    @Column(nullable = false, length = 130)
    var responsibleName: String? = null

    @Column(nullable = false, length = 30)
    var apartment: String? = null

    @Column(nullable = false, length = 30)
    var block: String? = null
}
