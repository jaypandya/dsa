package org.example

class Solution {
    enum class Color {
        Red, Blue, None;
    }

    fun isBipartite(graph: Array<IntArray>): Boolean {
        val queue = ArrayDeque<Int>()
        val colorArray = Array(graph.size) { Color.None }
        for (index in colorArray.indices) {
            if (colorArray[index] != Color.None) continue
            colorArray[index] = Color.Red
            queue.addLast(index)
            while (queue.isNotEmpty()) {
                val node = queue.removeFirst()
                val nodeColor = colorArray[node]
                val neighborColor = if (nodeColor == Color.Red) Color.Blue else Color.Red
                val neighbors = graph[node]
                for (neighbor in neighbors) {
                    if (colorArray[neighbor] == Color.None) {
                        colorArray[neighbor] = neighborColor
                        queue.addLast(neighbor)
                    } else if (colorArray[neighbor] == nodeColor) {
                        return false
                    }
                }
            }
        }
        return true
    }

}

fun parseGraph(s: String): Array<IntArray> {
    var bracketCount = 0
    val array = mutableListOf<IntArray>()
    var innerList: MutableList<Int> = mutableListOf()
    for (char in s.toCharArray()) {
        if (char == '[') {
            bracketCount++
            if (bracketCount == 2) {
                innerList = mutableListOf()
            }
        } else if (char == ']') {
            bracketCount--
            if (bracketCount == 1) {
                array.add(innerList.toIntArray())
            }
        } else if (char == ',') {
            continue
        } else {
            if (bracketCount == 2) {
                innerList.add(char.digitToInt())
            }
        }
    }
    return array.toTypedArray()
}

fun main() {
    val graph1 = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(0, 2),
        intArrayOf(0, 1, 3),
        intArrayOf(0, 2)
    )
    val graph2 = arrayOf(
        intArrayOf(1, 3),
        intArrayOf(0, 2),
        intArrayOf(1, 3),
        intArrayOf(0, 2)
    )

    val graph74 = parseGraph("[[],[3],[],[1],[]]")
    val graph76 = parseGraph("[[],[2,4,6],[1,4,8,9],[7,8],[1,2,8,9],[6,9],[1,5,7,8,9],[3,6,9],[2,3,4,6,9],[2,4,5,6,7,8]]")

    // [[1],[0,3],[3],[1,2]]
    val graph78 = arrayOf(
        intArrayOf(1),
        intArrayOf(0, 3),
        intArrayOf(3),
        intArrayOf(1, 2)
    )

    val solution = Solution()
    assert(!solution.isBipartite(graph1))
    assert(solution.isBipartite(graph2))
    assert(solution.isBipartite(graph74))
    assert(!solution.isBipartite(graph76))
    assert(solution.isBipartite(graph78))
}
