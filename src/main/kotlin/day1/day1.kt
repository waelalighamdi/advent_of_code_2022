package day1

import java.io.File

fun day1Part1(input: List<String>) {
    var mostCalories = 0
    var calories = 0

    for (calorie in input) {
        if (calorie.isNotBlank()) {
            calories += calorie.toInt()
        } else {
            if (calories > mostCalories) mostCalories = calories
            calories = 0
        }
    }
    if (calories > mostCalories) mostCalories = calories
    println(mostCalories)
}

fun day1Part2(input: List<String>) {
    // I am trying a different approach to resolve part2
    val elves = mutableListOf<List<Int>>()
    var elf = mutableListOf<Int>()
    for (calorie in input) {
        if (calorie.isNotBlank()) {
            elf.add(calorie.toInt())
        } else {
            elves.add(elf)
            elf = mutableListOf()
        }
    }
    elves.add(elf)
    val elvesCalories = elves.map { it.sum() }
    println(elvesCalories.sortedDescending().take(3).sum())
}

fun day1() {
    val basePath = "src/main/kotlin/day1"
    val fileTestName = "test_input.txt"
    val fileName = "input.txt"

    val input = File(basePath, fileName).readLines()
    day1Part1(input)
    day1Part2(input)
}