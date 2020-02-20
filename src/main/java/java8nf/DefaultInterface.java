package java8nf;

interface Vehicle {
    static void blowHorn() {
        System.out.println("bi......");
    }

    static void fire() {
        System.out.println("boom...");
    }

    default void print() {
        System.out.println("i'm vehicle");
    }

    default void aa() {
        System.out.println("aa..");
    }
}

interface FourWheeler {
    default void print() {
        System.out.println("i'm four wheeler");
    }
}

public class DefaultInterface {

    public static void main(String[] args) {
        Car car = new Car();
        car.print();
    }

}

class Car implements Vehicle, FourWheeler {


    @Override
    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        Vehicle.fire();
        aa();
    }
}
