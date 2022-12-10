package day6

import java.io.File

fun day6Part1(input: List<String>) {

}

fun day6Part2(input: List<String>) {

}

fun day6() {
    val basePath = "src/main/kotlin/day6"
    val fileTestName = "test_input.txt"
    val fileName = "input.txt"

    val input = File(basePath, fileName).readLines()
    println(input)
    day6Part1(input)
    day6Part2(input)
}