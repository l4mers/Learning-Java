package factory;

import unit.laser.BigLaser;
import unit.laser.Laser;
import unit.laser.MediumLaser;
import unit.laser.SmallLaser;

public class LaserFactory {

    public Laser getLaser(int id, int size, int defaultY){
        switch (id){
            case 1 ->{
                return new SmallLaser(size, defaultY);
            }case 2 ->{
                return new MediumLaser(size, defaultY);
            }default ->{
                return new BigLaser(size, defaultY);
            }
        }
    }
}
