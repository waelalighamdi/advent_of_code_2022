package day7

import java.io.File

fun parseFSCommands(commands: List<String>): Map<String, Int> {
    // holds directories [includes the path] and their sizes
    val directoriesDeviceSystem = emptyMap<String, Int>().toMutableMap()
    var path = ""

    for (command in commands) {
        val args = command.split(" ")
        println("Parsing Command: $command - Args: $args")
        when {
            command.contains("$ ls") -> { /* Nothing to parse */
            }

            command.contains("$ cd") -> {
                // Check the Args
                when {
                    // case 1: Args is the root "/"
                    args[2] == "/" -> {
                        path = "/root"
                        directoriesDeviceSystem[path] = 0
                    }
                    // case 2: Args is back to parent directory ".."
                    args[2] == ".." -> {
                        path = path.substringBeforeLast("/")
                    }
                    // case 3: default case is change directory to
                    else -> {
                        path = "$path/${args[2]}"
                        directoriesDeviceSystem[path] = 0
                    }
                }
            }

            command.contains("dir") -> { /* Nothing to parse */
            }

            else -> {
                // default case is a file
                val fileSize = args[0].toInt()
                // update the directories sizes up to the root /
                var directory = path
                repeat(path.count { it == '/' }) {
                    directoriesDeviceSystem[directory] = directoriesDeviceSystem[directory]!! + fileSize
                    directory = directory.substringBeforeLast("/")
                }
            }
        }
    }
    return directoriesDeviceSystem
}

fun day7Part1(fileSystem: Map<String, Int>): Int {
    return fileSystem.values.sumOf { directorySize ->
        if (directorySize < 100_000) {
            directorySize
        } else 0
    }
}

fun day7Part2(fileSystem: Map<String, Int>): Int {
    val freeSpaceOnDisk = 70_000_000 - fileSystem["/root"]!!
    val requiredAdditionalSpaceOnDisk = 30_000_000 - freeSpaceOnDisk
    return fileSystem.values.map { it }.filter { directorySize ->
        directorySize >= requiredAdditionalSpaceOnDisk
    }.min()
}

fun day7() {
    val basePath = "src/main/kotlin/day7"
    val fileTestName = "test_input.txt"
    val fileName = "input.txt"

    val input = File(basePath, fileName).readLines()

    val fileSystem = parseFSCommands(input)
    println(fileSystem)

    println("---------> Day 7 Part1")
    println(day7Part1(fileSystem))

    println("---------> Day 7 Part2")
    println(day7Part2(fileSystem))
}