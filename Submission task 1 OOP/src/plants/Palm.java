package plants;

//Palm är ett arv från Plant klassen
public class Palm extends Plant{
    public Palm(double height, String name) {
        super(height, name);
        food = Food.TAP_WATER;
    }
    //Polymorfism
    public void calculateFood() {
        foodAmount = food.baseAmount * height;
    }
}
