package day6

import java.io.File

fun day6Part1(input: List<String>) {
    val dataStream = input.first().windowed(4)
    println(dataStream)
    val markerIndex = input.first().windowed(4).indexOfFirst { packet ->
        packet.toSet().size == packet.length
    } + 4
    println("---------> Part1")
    println(dataStream)
    println(markerIndex)
}

fun day6Part2(input: List<String>) {
    val dataStream = input.first().windowed(14)
    println(dataStream)
    val markerIndex = input.first().windowed(14).indexOfFirst { packet ->
        packet.toSet().size == packet.length
    } + 14
    println("---------> Part2")
    println(dataStream)
    println(markerIndex)
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