package chefshierarki

open class Employee() {
    var hierarchyList: ArrayList<Employee> = ArrayList()
    var name: String = ""

    fun getUnderlings(emp: Employee, nisse: String): List<String>{
        val collector: MutableList<String> = mutableListOf()
        getNisse(emp, nisse, collector)
        return collector
    }

    private fun getNisse(emp: Employee, nisse: String, collector: MutableList<String>){
        if(emp.name === nisse){
            if(emp.hierarchyList.size > 0){
                emp.hierarchyList.forEach{
                    collector.add(it.name)
                    it.getNisseName(it, collector)}
            }
        }else{
            if(emp.hierarchyList.size > 0){
                emp.hierarchyList.forEach{it.getNisse(it, nisse, collector)}
            }
        }
    }
    private fun getNisseName(emp: Employee, collector: MutableList<String>){
        if(emp.hierarchyList.size > 0){
            emp.hierarchyList.forEach{
                collector.add(it.name)
                emp.getNisseName(it, collector)
            }
        }
    }
}