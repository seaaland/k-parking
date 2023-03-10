package net.seaaland.parking

import net.seaaland.parking.handler.Credentials

open class Parking {
    private val vehicles: MutableList<Credentials> = ArrayList()

    fun import(vararg credentials: Credentials) {
        vehicles.addAll(credentials)
    }

    fun unimport(plate: String) {
        vehicles.removeIf { vehiclePlate -> vehiclePlate.getPlate().equals(plate) }
    }

    fun getVehicles(): MutableList<Credentials> {
        return vehicles;
    }
}