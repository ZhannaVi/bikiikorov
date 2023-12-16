import kotlin.random.Random

fun main() {
    println("Добро пожаловать в игру 'Быки и коровы'!")

    val secretNumber = generateSecretNumber() // Компьютер загадывает число
    var attempts = 0

    while (true) {
        println("Введите вашу попытку угадать 4-значное число:")
        val guess = readLine()

        if (guess != null && guess.length == 4 && guess.all { it.isDigit() }) {
            attempts++
            val result = checkGuess(guess, secretNumber)
            println("Результат попытки: Быки: ${result.first}, Коровы: ${result.second}")

            if (result.first == 4) {
                println("Поздравляем! Вы угадали число $secretNumber за $attempts попыток.")
                break
            }
        } else {
            println("Пожалуйста, введите 4-значное число.")
        }
    }
}

fun generateSecretNumber(): String {
    val digits = mutableListOf<Int>(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    digits.shuffle()

    var secretNumber = ""
    for (i in 0 until 4) {
        secretNumber += digits[i]
    }

    return secretNumber
}

fun checkGuess(guess: String, secret: String): Pair<Int, Int> {
    var bulls = 0
    var cows = 0

    for (i in guess.indices) {
        if (guess[i] == secret[i]) {
            bulls++
        } else if (guess[i] in secret) {
            cows++
        }
    }
    return Pair(bulls, cows)
}