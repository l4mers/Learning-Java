package day6

class Signal(input: String) {

    private val sequence = input.toCharArray()

    fun getMarker(): Int = findSequence(3)
    fun getMessage(): Int = findSequence(13)

    private fun findSequence(limit: Int): Int {
        for (i in 0 until sequence.size - limit) {
            val marker = sequence.slice(i..i+ limit).joinToString("")
            if (marker.allUnique()) {
                return i + limit + 1
            }
        }
        return 0
    }
    private fun String.allUnique(): Boolean = toSet().size == length
}