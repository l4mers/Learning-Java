package day4

class CleaningAssignment(private val input: List<String>) {

    fun countAssignmentsTotallyOverlap() : Int =
        input.map { s ->
            val assignments = s.split(",","-").map { it.toInt() }
            if((assignments[0] >= assignments[2] && assignments[1] <= assignments[3]) ||
                (assignments[2] >= assignments[0] && assignments[3] <= assignments[1])){
                1
            } else 0
        }.sum()

    fun countAssignmentsOverlapping(): Int =
        input.map { s ->
            val assignments = s.split(",","-").map { it.toInt() }
            if(assignments[0] >= assignments[2] && assignments[0] <= assignments[3]){
                1
            }else if(assignments[1] >= assignments[2] && assignments[1] <= assignments[3]){
                1
            }else if(assignments[2] >= assignments[0] && assignments[2] <= assignments[1]){
                1
            }else if(assignments[3] >= assignments[0] && assignments[3] <= assignments[1]){
                1
            }
            else 0
        }.sum()
}