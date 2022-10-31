package plants;

//Cactus ärver från Plant klassen
public class Cactus extends Plant{
    public Cactus(double height, String name) {
        super(height, name);
        food = Food.MINERAL_WATER;
    }
    //Polymorfism
    public void calculateFood() {
        foodAmount = food.baseAmount;
    }
}
