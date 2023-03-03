package chefshierarki

class Butter() : Employee() {

    init {
        this.name = "Butter"
        hierarchyList.add(Radjuret())
        hierarchyList.add(Nyckelpigan())
        hierarchyList.add(Haren())
        hierarchyList.add(Raven())
    }
}