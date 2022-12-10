package day5

import java.io.File

data class MoveFromTo(val count: Int, val moveFrom: Int, val moveTo: Int)

fun parseStacks(stacksInput: List<String>, countOfLanes: Int): MutableList<MutableList<Char>> {
    val indexJump = 4
    var steps = 2
    val stacks = mutableListOf<MutableList<Char>>()
    repeat(countOfLanes) {
        val tempStack = mutableListOf<Char>()
        stacksInput.forEach { stack ->
            if (stack.length > steps)
                if (stack[steps - 1] in 'A'..'Z') tempStack.add(stack[steps - 1])
        }
        stacks.add(tempStack)
        steps += indexJump
    }
    return stacks
}

fun parseInstructions(instructionsInput: List<String>): MutableList<MoveFromTo> {
    val instructions = mutableListOf<MoveFromTo>()
    for (instructionInput in instructionsInput) {
        val move = instructionInput.split(" ")
        instructions.add(MoveFromTo(move[1].toInt(), move[3].toInt(), move[5].toInt()))
    }
    return instructions
}

fun day5Part1(stacks: MutableList<MutableList<Char>>, instructions: MutableList<MoveFromTo>) {

    for (instruction in instructions) {
        repeat(instruction.count) {
            stacks[instruction.moveTo - 1].add(stacks[instruction.moveFrom - 1].last())
            stacks[instruction.moveFrom - 1].removeLast()
        }
    }
    println("--------> Stacks after perform the movement")
    stacks.forEach { println(it) }

    println("--------> result of part1")
    stacks.forEach { stack ->
        print(stack.last())
    }
    println()

}

fun day5Part2(stacks: MutableList<MutableList<Char>>, instructions: MutableList<MoveFromTo>) {
    for (instruction in instructions) {
        val fromIndex = stacks[instruction.moveFrom - 1].size - instruction.count
        repeat(instruction.count) {
            stacks[instruction.moveTo - 1].add(stacks[instruction.moveFrom - 1][fromIndex])
            stacks[instruction.moveFrom - 1].removeAt(fromIndex)
        }

    }
    println("--------> Stacks after perform the movement")
    stacks.forEach { println(it) }

    println("--------> result of part2")
    stacks.forEach { stack ->
        print(stack.last())
    }
    println()

}

fun day5() {
    val basePath = "src/main/kotlin/day5"
    val fileTestName = "test_input.txt"
    val fileName = "input.txt"

    val input = File(basePath, fileName).readText()
    println(input)

    //Pre parsing - I need to get stacks, count of stacks' lanes and the instructions
    val (stacksAndLanesPart, instructionsPart) = input.split("\n\n")
    val countOfLanes = stacksAndLanesPart.split("\n").last().last().digitToInt()
    val stacksInput = stacksAndLanesPart.split("\n").dropLast(1).asReversed()
    val instructionsInput = instructionsPart.split("\n")

    var stacks = parseStacks(stacksInput = stacksInput, countOfLanes = countOfLanes)
    val instructions = parseInstructions(instructionsInput)

    day5Part1(stacks, instructions)

    stacks = parseStacks(stacksInput = stacksInput, countOfLanes = countOfLanes)
    day5Part2(stacks, instructions)
}