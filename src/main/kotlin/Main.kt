fun main() {
    val machineRes = machineResourses()
    var firstStart = true

    mainMenu(machineRes, firstStart)

}


fun mainInfo(machineResourses: machineResourses) {
    println(
        "\nThe coffee machine has:\n" +
                "${machineResourses.waterMl} ml of water\n" +
                "${machineResourses.milkMl} ml of milk\n" +
                "${machineResourses.coffeeGrams} g of coffee beans\n" +
                "${machineResourses.cups} disposable cups\n" +
                "\$${machineResourses.money} of money\n"
    )
}

fun mainMenu(machineResourses: machineResourses, firstStart: Boolean) {
    while (true) {
        if (!firstStart) println()
        println("Write action (buy, fill, take, remaining, exit): ")
        when (readln()) {
            "buy" -> userBuy(machineResourses)
            "fill" -> userFill(machineResourses)
            "take" -> userTake(machineResourses)
            "remaining" -> mainInfo(machineResourses)
            "exit" -> return
        }
    }
}

fun userBuy(machineResourses: machineResourses) {
    println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:")
    when (readln()) {
        "1" -> {
            if (machineResourses.waterMl - 250 >= 0 &&
                machineResourses.coffeeGrams - 16 >= 0 &&
                machineResourses.cups - 1 >= 0
            ) {
                machineResourses.waterMl -= 250
                machineResourses.coffeeGrams -= 16
                machineResourses.money += 4
                machineResourses.cups -= 1
            } else print("I have enough resources, making you a coffee!\n")

        }

        "2" -> {
            if (machineResourses.waterMl - 350 >= 0 &&
                machineResourses.milkMl - 75 >= 0 &&
                machineResourses.coffeeGrams - 20 >= 0 &&
                machineResourses.cups - 1 >= 0
            ) {
                machineResourses.waterMl -= 350
                machineResourses.milkMl -= 75
                machineResourses.coffeeGrams -= 20
                machineResourses.money += 7
                machineResourses.cups -= 1
            } else print("I have enough resources, making you a coffee!\n")
        }

        "3" -> {
            if (machineResourses.waterMl - 200 >= 0 &&
                machineResourses.milkMl - 100 >= 0 &&
                machineResourses.coffeeGrams - 12 >= 0 &&
                machineResourses.cups - 1 >= 0
            ) {
                machineResourses.waterMl -= 200
                machineResourses.milkMl -= 100
                machineResourses.coffeeGrams -= 12
                machineResourses.money += 6
                machineResourses.cups -= 1
            } else print("I have enough resources, making you a coffee!\n")
        }

        "back" -> {
            println()
            return
        }
    }
    println()
}

fun userFill(machineResourses: machineResourses) {
    println("\nWrite how many ml of water you want to add: ")
    machineResourses.waterMl += readln().toInt()
    println("Write how many ml of milk you want to add: ")
    machineResourses.milkMl += readln().toInt()
    println("Write how many grams of coffee beans you want to add: ")
    machineResourses.coffeeGrams += readln().toInt()
    println("Write how many disposable cups you want to add: ")
    machineResourses.cups += readln().toInt()
    println()
}

fun userTake(machineResourses: machineResourses) {
    println("\nI gave you ${machineResourses.money}\n")
    machineResourses.money = 0
}