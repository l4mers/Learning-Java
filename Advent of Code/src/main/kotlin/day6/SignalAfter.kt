package day6

class SignalAfter(input: String) {
    companion object {
        const val MARKER_LIMIT = 3
        const val MESSAGE_LIMIT = 13
    }

    private val sequence = input.toCharArray()

    fun getMarker(): Int = findSequence(MARKER_LIMIT)
    fun getMessage(): Int = findSequence(MESSAGE_LIMIT)

    private fun findSequence(limit: Int): Int {
        sequence.withIndex().forEach { (i, _) ->
            if (i <= sequence.size - limit) {
                val marker = sequence.slice(i..i + limit).joinToString("")
                if (marker.allUnique()) {
                    return i + limit + 1
                }
            }
        }
        return 0
    }
}

fun String.allUnique(): Boolean = toSet().size == length