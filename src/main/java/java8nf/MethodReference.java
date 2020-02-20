package java8nf;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class MethodReference {
    public static void main(String[] args) {
        Car car = Car.create(Car::new);
        List<Car> cars = Arrays.asList(car);
        cars.forEach(Car::collide);
        cars.forEach(Car::repair);
        Car another = Car.create(Car::new);
        cars.forEach(another::follow);
    }

    public static class Car {
        public static Car create(Supplier<Car> supplier) {
            return supplier.get();
        }

        public static void collide(final Car car) {
            System.out.println("Collided " + car.toString());
        }

        public void follow(final Car another) {
            System.out.println("Following the " + another.toString());
        }

        public void repair() {
            System.out.println("Repaired " + this.toString());
        }
    }
}