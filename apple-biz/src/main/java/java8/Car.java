package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 *  方法引用<br>
 * 〈功能详细描述〉
 *
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Car {

    public static Car create(final Supplier<Car> supplier){
        return supplier.get();
    }

    public static void collied(final Car car){
        System.out.println("Collied " + car.toString());
    }

    public void follow(final Car another){
        System.out.println("following "+ another.toString());
    }

    private void repair(){
        System.out.println("repair " + this.toString());
    }

    public static void main(String[] args) {
        Car car = Car.create(Car::new);
        Car car1 = Car.create(Car::new);
        List<Car> cars = Arrays.asList(car,car1);

        cars.forEach(Car::collied);
        cars.forEach(Car::repair);
        Car police = Car.create(Car::new);
        cars.forEach(police::follow);
    }
}
