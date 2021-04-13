fun main() {
    var rows: String
    var seats: String
    while (true) {
        println("Enter the number of rows (maximum is 9):")
        rows = readLine()!!
        if (rows in "0".."9") {
            if (rows.toInt() in 1..9) break else println("Wrong input!")
        } else println("Wrong input!")
    }
    while (true) {
        println("Enter the number of seats in each row (maximum is 9):")
        seats = readLine()!!
        if (seats in "0".."9") {
            if (seats.toInt() in 1..9) break else println("Wrong input!")
        } else println("Wrong input!")

    }
    var row: String
    var seat: String
    var income = 0
    var tickets = 0
    var percentage = 0.0
    val cinema = rows.toInt() * seats.toInt()
    val total =
        if (cinema < 61) {
            cinema * 10
        } else {
            ((rows.toInt() + 1) * seats.toInt() / 2) * 8 + ((rows.toInt() / 2) * seats.toInt()) * 10
        }
    val booked = Array(rows.toInt() + 1) { IntArray(seats.toInt() + 1) }

    fun menu () {
        println(
            "1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit"
        )
    }

    fun showSeats () {
        println()
        print("Cinema:")
        for (i in 0..rows.toInt()) {
            println()
            for (u in 0..seats.toInt()) {
                if (i == 0 && u == 0) {
                    print("  ")
                } else if (i == 0 && u in 0..seats.toInt()) {
                    print("$u ")
                } else if (u == 0 && i in 0..rows.toInt()) {
                    print("$i ")
                } else if (booked[i][u] == 1) {
                    print("B ")
                } else {
                    print("S ")
                }
            }
        }
        println("\n")
    }

    fun buyTicket () {
        if (percentage != 100.0) {
            ++tickets
            percentage = (tickets.toDouble() / cinema * 100.0)
            println()
            while (true) {
                while (true) {
                    println("Enter a row number:")
                    row = readLine()!!
                    if (row in "0".."9") {
                        if (row.toInt() >= rows.toInt() + 1) {
                            println("Wrong input!")
                            continue
                        } else break
                    } else {
                        println("Wrong input!")
                        continue
                    }
                }
                while (true) {
                    println("Enter a seat number in that row:")
                    seat = readLine()!!
                    if (seat in "0".."9") {
                        if (seat.toInt() >= seats.toInt() + 1) {
                            println("Wrong input!")
                            continue
                        } else break
                    } else {
                        println("Wrong input!")
                        continue
                    }
                }
                if (booked[row.toInt()][seat.toInt()] == 1) {
                    println("That ticket has already been purchased!")
                } else break
            }
            booked[row.toInt()][seat.toInt()] = 1
            val price =
                if (cinema < 61) {
                    10
                } else {
                    if (row.toInt() >= (rows.toInt() + 1) / 2) {
                        8
                    } else {
                        10
                    }
                }
            income += price
            println("Ticket price: $$price")
            println()
        } else println("All seats are already taken!\n")
    }

    fun statistics () {
        println("Number of purchased tickets: $tickets\n" +
                "Percentage: ${String.format("%.2f", percentage)}%\n" +
                "Current income: $$income\n" +
                "Total income: $$total")
        println()
    }
    println()
    while (true) {
        menu()
        when (readLine()!!.toInt()) {
            0 -> break
            1 -> showSeats()
            2 -> buyTicket()
            3 -> statistics()
        }
    }
}
