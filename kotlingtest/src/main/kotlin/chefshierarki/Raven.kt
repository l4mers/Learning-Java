package chefshierarki

class Raven() : Employee() {
    init {
        this.name = "Räven"
        hierarchyList.add(Grasuggan())
        hierarchyList.add(Myran())
    }
}