package cinema

fun main() {
    println("Enter the number of rows:")
    val rows = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val seats = readLine()!!.toInt()
    println()
    val booked = Array(rows + 1) { IntArray(seats + 1) }
    var row: Int
    var seat: Int
    var key : Int
    val cinema = rows * seats
    var income = 0
    var tickets = 0
    val total = if (cinema < 61) cinema * 10 else ((rows + 1) * seats / 2) * 8 + ((rows / 2) * seats) * 10
    var percentage = 0.0
    while (true) {
        println(
            "1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit"
        )
        key = readLine()!!.toInt()
        when (key) {
            0 -> break
            1 -> {
                println()
                print("Cinema:")
                for (i in 0..rows) {
                    println()
                    for (u in 0..seats) {
                        if (i == 0 && u == 0) {
                            print("  ")
                        } else if (i == 0 && u in 0..seats) {
                            print("$u ")
                        } else if (u == 0 && i in 0..rows) {
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
            2 -> {
                ++tickets
                percentage = (tickets.toDouble() / cinema * 100.0)
                println()
                while (true) {
                    println("Enter a row number:")
                    row = readLine()!!.toInt()
                    println("Enter a seat number in that row:")
                    seat = readLine()!!.toInt()
                    if (row >= rows + 1 || seat >= seats + 1) {
                        println()
                        println("Wrong input!")
                        println()
                    } else if (booked[row][seat] == 1) {
                    println()
                    println("That ticket has already been purchased!")
                    println()
                    } else break
                }
                booked[row][seat] = 1

                val price : Int = if (cinema < 61) {
                    10
                } else {
                    if (row >= (rows + 1) / 2) {
                        8
                    } else {
                        10
                    }
                }
                income += price

                println("Ticket price: $$price")
                println()
            }
            3 -> {
                println("Number of purchased tickets: $tickets\n" +
                        "Percentage: ${String.format("%.2f", percentage)}%\n" +
                        "Current income: $$income\n" +
                        "Total income: $$total")
                println()
            }
        }
    }
}
