import EncryptionDecryption.ASCII
import java.io.File

fun main(args: Array<String>) {
    var key = "0"
    var mode = "enc"
    var data = ""
    var input = ""
    var output = ""
    var alogorithm = "shift"

    for (i in 0 until args.size) {
        if (args[i] == "-mode") {
            mode = args[i + 1]
        }
        if (args[i] == "-key") {
            key = args[i + 1]
        }
        if (args[i] == "-data") {
            data = args[i + 1]
        }
        if (args[i] == "-in") {
            input = args[i + 1]
        }
        if (args[i] == "-out") {
            output = args[i + 1]
        }
        if (args[i] == "-alg") {
            alogorithm = args[i + 1]
        }
    }

    if (input != "") {
        val fileInpName = File(input)
        data = fileInpName.readText()
    }

    when (mode) {
        "enc" -> {
            if (output == "")
                println(decoder(data, key.toInt(), alogorithm))
            else {
                val fileOutName = output
                File(fileOutName).writeText(decoder(data, key.toInt(), alogorithm))
            }
        }

        "dec" -> {
            if (output == "")
                println(decoder(data, -key.toInt(), alogorithm))
            else {
                val fileOutName = output
                File(fileOutName).writeText(decoder(data, -key.toInt(), alogorithm))
            }
        }

    }
}

fun decoder(str: String, key: Int, alg: String): String {
    var result = ""
    val list = mutableListOf<String>()

    if (alg != "shift") {
        for (ch in str) {
            result += (ch.toInt() + key).toChar()
        }
    } else {
        for (ch in str) {
            if (ch == ' ' || ch == '!') {
                result += ch
            } else {
                result += asciiForShift(ch, key).toChar()
                list.add(asciiForShift(ch, key).toString())
            }
        }
    }
    return result
}

fun asciiForShift(ch: Char, key: Int): Int {
    val ascii = ASCII()
    if (ch.isUpperCase()) {
        val value = ch.toInt() + key
        if (value in ascii.upperStart..ascii.upperEnd)
            return value
        else
            return value % ascii.upperEnd + ascii.upperStart

    } else {
        val value = ch.toInt() + key % 25
        if (value in ascii.lowerStart..ascii.lowerEnd) {
            return value
        }
        return if (value < 97) {
            val a = 97 - value
            ascii.lowerEnd + 1 - a
        } else {
            value % (ascii.lowerEnd + 1) + ascii.lowerStart
        }
    }

}
