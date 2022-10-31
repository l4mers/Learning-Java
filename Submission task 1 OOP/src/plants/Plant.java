package plants;

//Superklass med ett implementerat interface
/*
* Abstract eftersom vi inte vill instanciera objekt av denna klass.
* Den ska bara användas för att återanvända gemensam kod för dess underklasser
*/
public abstract class Plant implements plantInterface{
    /*Inkapsling protected vilket gör att vi inte kan nå värdet
    * utanför klassen men kan tillåta underklasser att nå dess värde
    * vilket betyder att vi slipper använda getters och setters inom klass hierarkin  */
    final protected double height;
    final protected String name;
    protected double foodAmount;
    //Enum
    protected Food food;

    Plant(double height, String name){
        this.height = height;
        this.name = name;
    }
    //Taget från interfacet
    @Override
    public String getFood() {
        return "\n" + name + "\n\nNeeds " + foodAmount + " liters\nof " + food.type;
    }
    //Taget från interfacet och fungerar samtidigt som polymorfism
    @Override
    public void calculateFood() {
    }
    public String getName(){
        return name;
    }
}
