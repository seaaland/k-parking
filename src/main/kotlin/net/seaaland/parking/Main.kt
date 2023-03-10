package net.seaaland.parking

import net.seaaland.parking.handler.Credentials
import net.seaaland.parking.handler.type.Model
import java.util.Scanner
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val handler = Parking()

    /* Program */
    var option = 0
    while (option != 5) {
        for (i in 0..100)
            println()

        println("-> Type 1 to view a specific vehicle;\n-> Type 2 to add a vehicle;\n-> Type 3 to remove a vehicle;\n-> Type 4 to view all parked vehicles;\n-> Type 5 to end the program.")
        option = scanner.nextInt()

        for (i in 0..100)
            println()

        when (option) {
            1 -> {
                println("-> Insert the license plate number of the vehicle you want to consult:")
                val plate = scanner.next()

                if (handler.getVehicles().stream().noneMatch { p -> p.getPlate().equals(plate) }) {
                    println("\n[!] There is no parked vehicle corresponding to the inserted license plate.")
                } else {
                    val vehicle = handler.getVehicles().stream().filter { p -> p.getPlate().equals(plate) }.findAny().get()

                    println("\n-> Owner: ${vehicle.getOwner()} > Type: ${vehicle.getType().name} > Plate: ${vehicle.getPlate()}")
                }

                println("\n-> Do you want to return the application? [y/n]")
                if (scanner.next().equals("n"))
                    option = 5
            }

            2 -> {
                var owner = ""
                var type: Model? = null
                var plate = ""

                try {
                    println("-> Enter the name of the vehicle owner:")
                    owner = scanner.next()

                    println("-> Enter vehicle type: [Car, Motorcycle]")
                    type = Model.declare(scanner.next())

                    println("-> Enter the license plate:")
                    plate = scanner.next()
                } catch (ex: Exception) {
                    println("[!] There was an error processing the vehicle...")
                }

                val vehicle = type?.let { Credentials(owner, it, plate) } ?: continue

                if (handler.getVehicles().contains(vehicle)) {
                    println("\n[!] Already have a vehicle like this parked!")
                } else {
                    handler.import(vehicle)
                    println("-> Vehicle successfully parked!\n")
                }

                println("\n-> Do you want to return the application? [y/n]")
                if (scanner.next().equals("n"))
                    option = 5
            }

            3 -> {
                println("-> Enter the license plate of the vehicle you want to remove:")
                val plate = scanner.next()

                if (handler.getVehicles().stream().noneMatch { p -> p.getPlate().equals(plate) }) {
                    println("\n[!] You do not have a parked vehicle that corresponds to the inserted plate.")
                } else {
                    handler.unimport(plate)
                    println("-> Vehicle removed successfully!")
                }

                println("\n-> Do you want to return the application? [y/n]")
                if (scanner.next().equals("n"))
                    option = 5
            }

            4 -> {
                if (handler.getVehicles().isEmpty()) {
                    println("\n[!] There is no vehicle parked!")
                } else {
                    var index = 1

                    for (credentials in handler.getVehicles()) {
                        println("[$index] Owner: ${credentials.getOwner()} > Type: ${credentials.getType().name} > Plate: ${credentials.getPlate()}")

                        index += 1
                    }
                }

                println("\n-> Do you want to return the application? [y/n]")
                if (scanner.next().equals("n"))
                    option = 5
            }

            5 -> option = 5
        }
    }
}