package day1

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream


class CaloriesAfter() {

    private val calories = getInput()

    fun getAlfaElf(): Int = calories.first()

    fun getAlfaElfs(): Int = calories.take(3).sum()

    private fun getInput(): List<Int>{
        val sumList = mutableListOf<Int>()
        val addList = mutableListOf<Int>()
        try{
            val stream: Stream<String> = Files.lines(Paths.get("src/main/kotlin/day1/calories.txt"))
            stream.forEach{lines ->
                if(lines.equals("")){
                    sumList.add(addList.stream().toList().sum())
                    addList.clear()
                }else{
                    addList.add(Integer.valueOf(lines))
                }
            }
        }catch (e: IOException){
            e.stackTrace
        }
        return sumList.sortedDescending()
    }
}