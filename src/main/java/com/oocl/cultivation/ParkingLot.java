package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;

    public Map<ParkingTicket, Car> getCars() {
        return cars;
    }

    public Car getCar(ParkingTicket ticket){
        Car fetchedCar = cars.get(ticket);
        cars.remove(ticket);
        return fetchedCar;
    }

    public ParkingTicket parkCar(Car car) {
        if(getAvailableParkingPosition() < 0){
            ParkingTicket parkingTicket = new ParkingTicket(car);
            this.cars.put(parkingTicket,car);
            return parkingTicket;
        }
        return null;
    }

    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableParkingPosition() {
        return cars.size() - capacity;
    }
}
