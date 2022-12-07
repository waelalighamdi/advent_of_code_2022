package day3

import java.io.File

fun day3Part1(input: List<String>) {
    val sumPriorities = input.sumOf { rucksack ->
        val compartments = rucksack.chunked(rucksack.length/2)
        val itemType = compartments.first().toSet().intersect(compartments.last().toSet())
        when(itemType.first()) {
            in 'a'..'z' -> itemType.first() - 'a' + 1
            in 'A'..'Z' -> itemType.first() - 'A' + 27
            else -> 0
        }
    }
    println(sumPriorities)
}

fun day3Part2(input: List<String>) {

}

fun day3() {
    val basePath = "src/main/kotlin/day3"
    val fileTestName = "test_input.txt"
    val fileName = "input.txt"

    val input = File(basePath, fileName).readLines()
    println(input)
    day3Part1(input)
}