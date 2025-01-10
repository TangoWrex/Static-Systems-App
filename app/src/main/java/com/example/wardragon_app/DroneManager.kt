package com.example.wardragon_app

object DroneManager {
    private val droneList = mutableListOf<Drone>()

    fun addDrone(drone: Drone) {
        droneList.add(drone)
    }

    fun removeDroneById(droneId: String) {
        droneList.removeAll { it.id == droneId }
    }

    fun getDroneList(): List<Drone> {
        return droneList
    }
}
