class Day4 {
companion object {
    private val input = Input("day4.txt").readLines().map { it.toList() }

    fun part1(): Int {
        val boxes = mutableListOf<List<List<Char>>>()
        val boxesX = input.size
        val boxesY = input[0].size

        println("$boxesX $boxesY")

        val boxRegions = mutableListOf<List<Int>>()
        for (i in 0 until boxesX - 3) {
            val rows = input.subList(i, i + 4)
            assert(rows.size == 4)

            for (j in 0 until boxesY - 3) {
                val segments = rows.map { it.subList(j, j + 4) }
                assert(segments.map { it.size }.all { it == 4 })
                boxes.add(segments)
                boxRegions.add(listOf(i, i+4, j, j+4))
            }
        }

        println(boxRegions.size)

        val values = setOf("XMAS", "XMAS".reversed())
        var count = 0

        var edited = input.map { it.toMutableList() }
        boxRegions.forEach { bounds ->
            val box = edited.subList(bounds[0], bounds[1]).map { it.subList(bounds[2], bounds[3])}
            assert(box.size == 4 && box[0].size == 4)
            for (i in box.indices) {
                val row = box[i].joinToString("")
                if (row in values) {
                    count++
                    edited[i + bounds[0]][bounds[2]] = '.'
                }
            }
        }

        edited = input.map { it.toMutableList() }
        boxRegions.forEach { bounds ->
            val box = edited.subList(bounds[0], bounds[1]).map { it.subList(bounds[2], bounds[3])}
            assert(box.size == 4 && box[0].size == 4)
            for (i in box.indices) {
                val column = box.transpose()[i].joinToString("")
                if (column in values) {
                    count++
                    edited[bounds[0]][i + bounds[2]] = '.'
                }
            }
        }

        edited = input.map { it.toMutableList() }
        boxRegions.forEach { bounds ->
            val box = edited.subList(bounds[0], bounds[1]).map { it.subList(bounds[2], bounds[3])}
            assert(box.size == 4 && box[0].size == 4)
            val leftDiag = mutableListOf<Char>()
            for (i in box.indices) {
                leftDiag.add(box[i][3 - i])
            }
            if (leftDiag.joinToString("") in values) {
                count++
                edited[bounds[0]][bounds[2]] = '.'
            }
        }

        edited = input.map { it.toMutableList() }
        boxRegions.forEach { bounds ->
            val box = edited.subList(bounds[0], bounds[1]).map { it.subList(bounds[2], bounds[3])}
            assert(box.size == 4 && box[0].size == 4)
            val rightDiag = mutableListOf<Char>()
            for (i in box.indices) {
                rightDiag.add(box[i][i])
            }
            if (rightDiag.joinToString("") in values) {
                count++
                edited[bounds[0]][bounds[2]] = '.'
            }
        }

        return count
    }

    private fun tryX(x: Int, y: Int): Boolean {
        val values = setOf("MAS", "SAM")
        val box = input.subList(y, y + 3).map { it.subList(x, x + 3) }
        return (box.indices.map { box[it][it] }.joinToString("")) in values
            && (box.indices.map { box[it][2 - it] }.joinToString("")) in values
    }

    fun part2(): Int {
        var count = 0
        (0 until input.size - 2).forEach { y ->
            (0 until input[0].size - 2).forEach { x ->
                count += if (tryX(x, y)) 1 else 0
            }
        }
        return count
    }
}
}

fun main() {
    AoC.run(4, Day4::part1, Day4::part2)
}