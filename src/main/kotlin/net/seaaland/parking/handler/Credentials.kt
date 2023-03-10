package net.seaaland.parking.handler

import net.seaaland.parking.handler.type.Model

class Credentials {
    private val owner: String
    private val type: Model
    private val plate: String

    constructor(owner: String, type: Model, plate: String) {
        this.owner = owner
        this.type = type
        this.plate = plate
    }

    fun getOwner(): String {
        return owner
    }

    fun getType(): Model {
        return type
    }

    fun getPlate(): String {
        return plate
    }
}