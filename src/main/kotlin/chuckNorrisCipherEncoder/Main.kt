package chuckNorrisCipherEncoder

import java.lang.Exception

fun main() {
    mainMenu()
}

fun mainMenu() {
    var action = ""
    while (action != "exit") {
        println("Please input operation (encode/decode/exit):")
        action = readln()
        when (action) {
            "encode" -> encoder()
            "decode" -> {
                try {
                    decoder()
                } catch (e: Exception) {
                    println("Encoded string is not valid.\n")
                }
            }

            "exit" -> {
                println("Bye!")
            }

            else -> println("There is no '$action' operation\n")
        }
    }
}

fun decoder() {
    println("Input encoded string:")
    val input = readln()
    if (input.first() != '0') throw Exception("StringIndexOutOfBoundsException")
    else
        println("Decoded string:\n${BinarToChar(ChuckToBinar(input))}\n")
}

fun BinarToChar(str: String): String {
    var result = ""
    var varstr = str
    var strBuff = ""
    while (varstr.isNotEmpty()) {
        strBuff = varstr.substring(0, 7)
        varstr = varstr.replaceFirst(strBuff, "")
        result += Integer.parseInt(strBuff, 2).toChar()
    }
    return result
}

fun ChuckToBinar(str: String): String {
    var result = ""
    val list = (str.split(" ").toMutableList())
    for (i in 1 until list.size step 2) {
        if (list[i - 1] == "0") {
            result += "1".repeat(list[i].length)
        }
        if (list[i - 1] == "00") {
            result += "0".repeat(list[i].length)
        }
    }
    return result
}

fun encoder() {
    println("Input string:")
    val str = readln()
    println("Encoded string:")
    print("${binarToChuckNorris(charToBinar(str))}\n\n")

}

fun charToBinar(str: String): String {
    var result = ""
    var int = 0
    for (ch in str) {
        int = (Integer.toBinaryString(ch.toInt())).toInt()
        result += (String.format("%07d", int)).toString()
    }
    return result
}

fun binarToChuckNorris(str: String): String {
    var result = ""
    var currentMatch = 1
    var strbuf = str + " "
    for (i in 0 until strbuf.length - 1) {
        if (strbuf[i] == strbuf[i + 1]) {
            currentMatch += 1
        } else {
            if (strbuf[i] == '1') {
                result += "0 "
                result += "0".repeat(currentMatch)
                result += " "
            } else {
                result += "00 "
                result += "0".repeat(currentMatch)
                result += " "
            }
            currentMatch = 1
        }
    }
    return result.replaceFirst(".$".toRegex(), "")
}
