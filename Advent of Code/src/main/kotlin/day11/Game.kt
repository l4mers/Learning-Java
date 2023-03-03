package day11


class Game(input: List<String>) {
    private val monkeys = setUpMonkeys(input)


    fun monkeyBusiness() : Long {
        for (i in 1..20){
            for (monkey in monkeys){
                if (monkey.itemList.size > 0){
                    for (item in monkey.itemList){
                        val worryLvl = monkey.applyOperation(item)
                        val realWorryLvl: Long = worryLvl.div(3)
                        if((realWorryLvl % monkey.divider) == 0L){
                            monkeys[monkey.monkey1].itemList.add(realWorryLvl)
                        } else {
                            monkeys[monkey.monkey2].itemList.add(realWorryLvl)
                        }
                    }
                    monkey.monkeyBusiness += monkey.itemList.size
                    monkey.itemList.clear()
                }
            }
        }
        val sortedList = monkeys.sortedByDescending { it.monkeyBusiness }
        return sortedList[0].monkeyBusiness * sortedList[1].monkeyBusiness
    }

    fun monkeyBusinessRidiculous () : Long {
        for (i in 1..10000){
            for (monkey in monkeys){
                if (monkey.itemList.size > 0){
                    for (item in monkey.itemList){
                        val worryLvl = monkey.applyOperation(item)
                        if((worryLvl % monkey.divider) == 0L){
                            monkeys[monkey.monkey1].itemList.add(worryLvl)
                        } else {
                            monkeys[monkey.monkey2].itemList.add(worryLvl)
                        }
                        monkey.monkeyBusiness++
                    }
                    monkey.itemList.clear()
                }
            }
        }
        val sortedList = monkeys.sortedByDescending { it.monkeyBusiness }
        return sortedList[0].monkeyBusiness * sortedList[1].monkeyBusiness
    }


    private fun setUpMonkeys(input: List<String>): List<Monkey>{
        val monkeys = mutableListOf<Monkey>()
        var itemList = mutableListOf<Long>()
        var operator = ""
        var operation = ""
        var divider: Long = 0
        var monkey1: Int = 0
        for (ss in input){
            val s = ss.trim()
            if (s.startsWith("Starting")){
                val items = s.replace("Starting items: ", "").split(", ")
                for(i in items){
                    itemList.add(i.toLong())
                }
            }
            if(s.startsWith("Operation")){
                operation = s.substringAfterLast(" ")
                operator = s.replace("Operation: new = old ", "").substringBeforeLast(" ")
            }
            if(s.startsWith("Test")){
                divider = s.replace("Test: divisible by ", "").toLong()
            }
            if(s.startsWith("If true")){
                monkey1 = s.replace("If true: throw to monkey ", "").toInt()
            }
            if(s.startsWith("If false")){
                val monkey2 = s.replace("If false: throw to monkey ", "").toInt()
                monkeys.add(Monkey(itemList, operator, operation, divider, monkey1, monkey2))
                itemList = mutableListOf<Long>()
            }
        }
        return monkeys
    }
    inner class Monkey(
        val itemList: MutableList<Long>,
        private val operator: String,
        private val operation: String,
        val divider: Long,
        val monkey1: Int,
        val monkey2: Int
    ) {
        var monkeyBusiness: Long = 0
        fun applyOperation(item: Long): Long =
            if(operation == "old"){
                item * item
            } else if(operator == "*"){
                item * operation.toLong()
            } else {
                item + operation.toLong()
            }
    }
}