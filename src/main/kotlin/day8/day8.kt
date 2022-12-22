package day8

import java.io.File

private fun upList(treesMap: List<List<Int>>, rows: Int, columns: Int): List<Int> {
    val list = emptyList<Int>().toMutableList()
    for (rowIndex in rows - 1 downTo 0) {
        list.add(treesMap[rowIndex][columns])
    }
    return list.toList()
}

private fun downList(treesMap: List<List<Int>>, rows: Int, columns: Int, size: Int): List<Int> {
    val list = emptyList<Int>().toMutableList()
    for (rowIndex in rows + 1 until size) {
        list.add(treesMap[rowIndex][columns])
    }
    return list.toList()
}

private fun leftList(treesMap: List<List<Int>>, rows: Int, columns: Int): List<Int> {
    val list = emptyList<Int>().toMutableList()
    for (columnIndex in columns - 1 downTo 0) {
        list.add(treesMap[rows][columnIndex])
    }
    return list.toList()
}

private fun rightList(treesMap: List<List<Int>>, rows: Int, columns: Int, size: Int): List<Int> {
    val list = emptyList<Int>().toMutableList()
    for (columnIndex in columns + 1 until size) {
        list.add(treesMap[rows][columnIndex])
    }
    return list.toList()
}

private fun day8Part1(treesMap: List<List<Int>>, rows: Int, columns: Int): Int {
    var countVisible = (rows * 2) + (columns * 2) - 4
    for (rowIndex in 1 until rows - 1) {
        for (columnIndex in 1 until columns - 1) {
            if ( // Check UP
                (treesMap[rowIndex][columnIndex] > upList(treesMap, rowIndex, columnIndex).max()) ||
                // Check DOWN
                (treesMap[rowIndex][columnIndex] > downList(treesMap, rowIndex, columnIndex, rows).max()) ||
                // Check LEFT
                (treesMap[rowIndex][columnIndex] > leftList(treesMap, rowIndex, columnIndex).max()) ||
                // Check RIGHT
                (treesMap[rowIndex][columnIndex] > rightList(treesMap, rowIndex, columnIndex, columns).max())
            ) {
                countVisible += 1
            }
        }
    }
    return countVisible
}

private fun countTrees(trees: List<Int>, tree: Int): Int {
    return trees.indexOfFirst { it >= tree }.takeIf { it != -1 }?.plus(1) ?: trees.count()
}

private fun day8Part2(treesMap: List<List<Int>>, rows: Int, columns: Int): Int {
    var scenicScore = 0
    val scenicScoreList = emptyList<Int>().toMutableList()

    for (rowIndex in 1 until rows - 1) {
        for (columnIndex in 1 until columns - 1) {
            // Check UP
            val countUp = countTrees(upList(treesMap, rowIndex, columnIndex), treesMap[rowIndex][columnIndex])
            // Check DOWN
            val countDown = countTrees(downList(treesMap, rowIndex, columnIndex, rows), treesMap[rowIndex][columnIndex])
            // Check LEFT
            val countLeft = countTrees(leftList(treesMap, rowIndex, columnIndex), treesMap[rowIndex][columnIndex])
            // Check RIGHT
            val countRight = countTrees(rightList(treesMap, rowIndex, columnIndex, columns), treesMap[rowIndex][columnIndex])

            scenicScore = countUp * countDown * countLeft * countRight
            scenicScoreList.add(scenicScore)
        }
    }
    return scenicScoreList.max()
}

fun day8() {
    val basePath = "src/main/kotlin/day8"
    val fileTestName = "test_input.txt"
    val fileName = "input.txt"

    val input = File(basePath, fileName).readLines()

    val treesMap = input.map { treeLine ->
        treeLine.map { tree ->
            tree.digitToInt()
        }
    }
    println(treesMap)

    val rows = treesMap.size
    val columns = treesMap.first().size

    println("---------> Day 8 Part1")
    println(day8Part1(treesMap, rows, columns))

    println("---------> Day 8 Part2")
    println(day8Part2(treesMap, rows, columns))
}