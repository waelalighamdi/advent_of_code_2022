package day2

import java.io.File

fun day2Part1(input: List<String>) {
    fun shapeScore(shape: Char): Int {
        return when (shape) {
            'X' -> 1    // rock
            'Y' -> 2    // paper
            'Z' -> 3    // scissors
            else -> 0   // default
        }
    }

    fun roundScore(roundShapes: String): Int {
        return when (roundShapes) {
            "A X", "B Y", "C Z" -> 3 // draw
            "A Y", "B Z", "C X" -> 6 // win
            "A Z", "B X", "C Y" -> 0 // lose
            else -> 0 // default
        }
    }

    val totalScore = input.sumOf { roundShapes ->
        shapeScore(roundShapes[2]) + roundScore(roundShapes)
    }
    println(totalScore)
}

fun day2Part2(input: List<String>) {
    fun shapeScore(shapes: String): Int {
        return when (shapes) {
            // lose - draw - win
            "B X", "A Y", "C Z" -> 1    // rock
            "C X", "B Y", "A Z" -> 2    // paper
            "A X", "C Y", "B Z" -> 3    // scissors
            else -> 0   // default
        }
    }

    fun roundScore(roundStrategy: Char): Int {
        return when (roundStrategy) {
            'X' -> 0 // lose
            'Y' -> 3 // draw
            'Z' -> 6 // win
            else -> 0 // default
        }
    }
    val totalScore = input.sumOf { roundShapes ->
        shapeScore(roundShapes) + roundScore(roundShapes[2])
    }
    println(totalScore)
}

fun day2() {
    val basePath = "src/main/kotlin/day2"
    val fileTestName = "test_input.txt"
    val fileName = "input.txt"

    val input = File(basePath, fileName).readLines()
    println(input)
    day2Part1(input)
    day2Part2(input)
}