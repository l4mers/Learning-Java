package plants;

import org.junit.jupiter.api.Test;

class PlantTest {
    Palm palm = new Palm(10, "Coco");
    Carnivorous carnivorous = new Carnivorous(1.5, "T-rex");
    Cactus cactus = new Cactus(2, "Spike");

    @Test
    void testCalculateFood(){
        palm.calculateFood();
        carnivorous.calculateFood();
        cactus.calculateFood();
        //0,5 * 10(Höjden) = 5 Liter
        assert (palm.foodAmount == 5);
        //0,2 * 1,5(Höjden) + 0,1 = 0,4 Liter
        assert (carnivorous.foodAmount == 0.4);
        //Alltid 0,02 Liter
        assert (cactus.foodAmount == 0.02);
    }
}