package net.seaaland.parking.handler.type

import java.util.*
import kotlin.collections.HashMap

enum class Model(val modified: String) {
    CAR("Car"),
    MOTORCYCLE("Motor");

    open val search: MutableMap<String, Model> = HashMap()

    companion object {
        init {
            for (model in values()) {
                model.search[model.name] = model
                model.search[model.name.lowercase(Locale.getDefault())] = model

                model.search[model.modified] = model
            }
        }

        fun declare(use: String?): Model? {
            for (model in values()) {
                return model.search.get(use)
            }

            return null
        }
    }
}