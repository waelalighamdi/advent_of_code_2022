package day4

import java.io.File

fun day4Part1(input: List<String>) {
    val assignedSections = input.map { pairOfElves ->
        val (firstSection, secondSection) = pairOfElves.split(",")
        val s1 = firstSection.split("-")
        val s2 = secondSection.split("-")
        (s1.first().toInt()..s1.last().toInt()) to (s2.first().toInt()..s2.last().toInt())
    }

    val fullyOverlapSections = assignedSections.count { (pair1, pair2) ->
        (pair1.first in pair2 && pair1.last in pair2) || (pair2.first in pair1 && pair2.last in pair1)
    }

    println(fullyOverlapSections)
}

fun day4Part2(input: List<String>) {
    val assignedSections = input.map { pairOfElves ->
        val (firstSection, secondSection) = pairOfElves.split(",")
        val s1 = firstSection.split("-")
        val s2 = secondSection.split("-")
        (s1.first().toInt()..s1.last().toInt()) to (s2.first().toInt()..s2.last().toInt())
    }

    val partiallyOverlapSections = assignedSections.count { (pair1, pair2) ->
        pair1.intersect(pair2).isNotEmpty()
    }

    println(partiallyOverlapSections)

}

fun day4() {
    val basePath = "src/main/kotlin/day4"
    val fileTestName = "test_input.txt"
    val fileName = "input.txt"

    val input = File(basePath, fileName).readLines()
    println(input)
    day4Part1(input)
    day4Part2(input)
}