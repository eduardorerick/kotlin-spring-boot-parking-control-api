package com.api.parkingcontrol

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ParkingControlApplication

fun main(args: Array<String>) {
	runApplication<ParkingControlApplication>(*args)
}
