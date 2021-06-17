package com.smartiq.util;

import com.smartiq.model.Car;

import java.util.ArrayList;
import java.util.List;

public class SmartiqUtil {

    public static List<Car> cars = new ArrayList<>();

    public static String createTitle(Car car){
        return car.getYear() + " " + car.getBrand() + " " + car.getModel() + " " + car.getVersion();
    }

    public static long nextCarId(){
        long maxId = 0;
        if (!cars.isEmpty()){
            for (Car car : cars){
                if (maxId < car.getId())
                    maxId = car.getId();
            }
        }
        return maxId+1;
    }

}
