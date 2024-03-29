package day2

class RockPaperScissorAfter(private val input: List<String>) {

    private val firstScore: Map<String, Int> =
        mapOf(
            "A X" to 1 + 3,
            "A Y" to 2 + 6,
            "A Z" to 3 + 0,
            "B X" to 1 + 0,
            "B Y" to 2 + 3,
            "B Z" to 3 + 6,
            "C X" to 1 + 6,
            "C Y" to 2 + 0,
            "C Z" to 3 + 3,
        )

    private val secondScore: Map<String, Int> =
        mapOf(
            "A X" to 3 + 0,
            "A Y" to 1 + 3,
            "A Z" to 2 + 6,
            "B X" to 1 + 0,
            "B Y" to 2 + 3,
            "B Z" to 3 + 6,
            "C X" to 2 + 0,
            "C Y" to 3 + 3,
            "C Z" to 1 + 6,
        )

    fun getScore(): Int =
        input.sumOf { firstScore[it] ?: 0 }

    fun getScoreSecond(): Int =
        input.sumOf { secondScore[it] ?: 0 }
}