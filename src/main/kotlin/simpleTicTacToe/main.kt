package simpleTicTacToe

import java.util.Collections
import kotlin.math.abs

fun main() {
    val desk = mutableListOf(
        mutableListOf<Char>('-', '-', '-', '-', '-', '-', '-', '-', '-'),
        mutableListOf<Char>('|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'),
        mutableListOf<Char>('|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'),
        mutableListOf<Char>('|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'),
        mutableListOf<Char>('-', '-', '-', '-', '-', '-', '-', '-', '-')
    )
    liveGame(desk)
}

fun liveGame(desk: MutableList<MutableList<Char>>) {

    while (true) {
        if (winCheck(desk) == "Game not finished") {
            val user = 'X'
            printDesk(desk)
            userMove(desk, user)
        } else {
            printDesk(desk)
            println(winCheck(desk))
            break
        }
        if (winCheck(desk) == "Game not finished") {
            val user = 'O'
            printDesk(desk)
            userMove(desk, user)
        } else {
            printDesk(desk)
            println(winCheck(desk))
            break
        }

    }
}

fun userMove(desk: MutableList<MutableList<Char>>, user: Char) {
    while (true) {
        val move = readln()
        val (_x, _y) = move.split(" ")

        if (!isInteger(_x) || !isInteger(_y)) {
            println("You should enter numbers!")
            continue
        } else if (_x.toInt() !in 1..3 || _y.toInt() !in 1..3) {
            println("Coordinates should be from 1 to 3!")
            continue
        } else if ((desk[_x.toInt()][_y.toInt() * 2]) !== ' ') {
            println("This cell is occupied! Choose another one!")
            continue
        } else {
            desk[_x.toInt()][_y.toInt() * 2] = user
            break
        }
    }
}

fun isInteger(s: String): Boolean {
    return try {
        s.toInt()
        true
    } catch (e: NumberFormatException) {
        false
    }
}

fun winCheck(desk: MutableList<MutableList<Char>>): String {

    val winner = mutableListOf<Char>()
    for (i in 1..3) {
        if (desk[i][2] == desk[i][4] && desk[i][2] == desk[i][6] && desk[i][2] != ' ') winner.add(desk[i][2]) // in str
        if (desk[1][i * 2] == desk[2][i * 2] && desk[1][i * 2] == desk[3][i * 2] && desk[1][i * 2] != ' ') winner.add(
            desk[1][i * 2]
        )
    }
    if (desk[1][2] == desk[2][4] && desk[1][2] == desk[3][6] && desk[1][2] != ' ') winner.add(desk[1][2]) // победа в главной диагонали
    if (desk[1][6] == desk[2][4] && desk[1][6] == desk[3][2] && desk[1][6] != ' ') winner.add(desk[1][6]) // победа в побочной диагонали

    if (!isMovesReal(desk)) {
        return "Impossible"
    } else return when (winner.size) {
        1 -> "${winner[0]} wins"
        0 -> {
            when (emptyCheck(desk)) {
                0 -> "Draw"
                else -> "Game not finished"
            }
        }

        else -> "${winner[0]} wins"
    }

}

fun emptyCheck(desk: MutableList<MutableList<Char>>): Int {
    var emptyCell = 0
    for (i in 1..3)
        for (j in 1..3) {
            if (desk[i][j * 2] == ' ')
                emptyCell += 1
        }
    return emptyCell
}

fun isMovesReal(desk: MutableList<MutableList<Char>>): Boolean {
    val amountX = Collections.frequency(desk, 'X')
    val amountO = Collections.frequency(desk, 'O')
    return !(abs(amountX - amountO) > 1)
}

fun printDesk(desk: MutableList<MutableList<Char>>) {
    for (i in 0 until desk.size)
        println(desk[i].joinToString(""))
}

