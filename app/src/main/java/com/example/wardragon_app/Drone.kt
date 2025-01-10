package com.example.wardragon_app

import org.json.JSONObject

data class Drone(
    val id: String,
    val latitude: Double,
    val longitude: Double,
    val horizontalSpeed: Double,
    val verticalSpeed: Double,
    val altitude: Double,
    val height: Double,
    val pilotLatitude: Double,
    val pilotLongitude: Double,
    val description: String
) {
    companion object {
        fun fromJSON(json: JSONObject): Drone {
            val location = json.getJSONObject("location")
            val speed = json.getJSONObject("speed")
            val pilotLocation = json.getJSONObject("pilot_location")
            return Drone(
                id = json.getString("id"),
                latitude = location.getDouble("latitude"),
                longitude = location.getDouble("longitude"),
                horizontalSpeed = speed.getDouble("horizontal"),
                verticalSpeed = speed.getDouble("vertical"),
                altitude = json.getDouble("altitude"),
                height = json.getDouble("height"),
                pilotLatitude = pilotLocation.getDouble("latitude"),
                pilotLongitude = pilotLocation.getDouble("longitude"),
                description = json.getString("description")
            )
        }
    }

    constructor(
        id: String,
        latitude: Double,
        longitude: Double,
        horizontalSpeed: Double,
        verticalSpeed: Double,
        altitude: Double,
        height: Double,
        description: String
    ) : this(
        id,
        latitude,
        longitude,
        horizontalSpeed,
        verticalSpeed,
        altitude,
        height,
        latitude, // Dummy values for pilot location
        longitude, // Dummy values for pilot location
        description
    )


}
