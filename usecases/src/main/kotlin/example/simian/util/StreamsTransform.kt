package example.simian.util

import kotlin.streams.toList

class StreamsTransform {

    companion object {

        private fun getHorizontal(matrix: List<List<String>>): List<String> = matrix
            .map {
                it.joinToString("")
            }

        private fun getVertical(matrix: List<List<String>>): List<String> {
            return (0..matrix.size).map {
                var slot = "";
                for (x in 0.until(matrix.size)) {
                    for (y in 0.until(matrix[x].size)) {
                        if (y == it) {
                            slot += matrix[x][y]
                        }
                    }
                }
                slot
            }
        }

        private fun getDiagonalLeft(matrix: List<List<String>>): List<String> {
            val size = matrix.size
            val line = size - 1
            return (0 until 2 * size - 1).map {
                var slot = ""
                if (it >= size) {
                    val x = it - line
                    for (i in line downTo x) {
                        slot += matrix[i][line + x - i]
                    }
                } else {
                    for (i in it downTo 0) {
                        slot += matrix[i][it - i]
                    }
                }
                slot;
            }
        }


        private fun getDiagonalRight(matrix: List<List<String>>): List<String> {
            val size = matrix.size
            val line = size - 1
            var incPosition = 1
            return (line until (2 * size - 2) + size).map {
                var slot = ""
                if (it - size < line) {
                    for (i in it downTo line) {
                        val x = i - (it - line)
                        val y = (line - i).times(-1)
                        slot += matrix[x][y]
                    }
                } else {
                    for (i in (it - line - incPosition) downTo incPosition) {
                        slot += matrix[i - incPosition][i]
                    }
                    incPosition = incPosition.inc()
                }
                slot
            }
        }

        private fun toMatrix(slots: List<String>): List<List<String>> = slots.map { slot ->
            slot.map { it.toString() }
        }

        private fun getAllSlots(matrix: List<List<String>>): List<String> =
            (getHorizontal(matrix) + getVertical(matrix) + getDiagonalLeft(matrix) + getDiagonalRight(matrix)).distinct()

        fun countAllMatchingSlots(
            slots: List<String>,
            minimalRequiredChar: Int,
            requiredMatchWords: List<String>
        ): Int {
            val streams = getAllSlots(toMatrix(slots))
                .stream()
                .filter { it.length >= minimalRequiredChar }
                .map { it.toString() }
                .toList()
                .joinToString(" ")
            return requiredMatchWords.sumOf { (streams.split(it).size - 1) };
        }


    }
}