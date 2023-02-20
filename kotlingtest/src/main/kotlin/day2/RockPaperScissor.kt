package day2

class RockPaperScissor(private val input: List<String>) {

    fun getScore(): Int = input.map{ s ->
        when (s) {
            "A Y" -> 6 + 2
            "A X" -> 3 + 1
            "A Z" -> 0 + 3
            "B Y" -> 3 + 2
            "B X" -> 0 + 1
            "B Z" -> 6 + 3
            "C Y" -> 0 + 2
            "C X" -> 6 + 1
            "C Z" -> 3 + 3
            else -> 0
        }
    }.sum()

    fun getScoreSecond(): Int = input.map{s ->
        when (s) {
            "A Y" -> 3 + 1
            "A X" -> 0 + 3
            "A Z" -> 6 + 2
            "B Y" -> 3 + 2
            "B X" -> 0 + 1
            "B Z" -> 6 + 3
            "C Y" -> 3 + 3
            "C X" -> 0 + 2
            "C Z" -> 6 + 1
            else -> 0
        }
    }.sum()
}
