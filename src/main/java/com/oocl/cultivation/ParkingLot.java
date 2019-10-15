package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot{
    private final int capacity;
    ParkingBoy parkingBoy;

    public Map<ParkingTicket, Car> getCars() {
        return cars;
    }

    public Car getCar(ParkingTicket ticket){
        Car fetchedCar = cars.get(ticket);

        if(fetchedCar != null){
            cars.remove(ticket);
            return fetchedCar;
        }

        return null;
    }

    public ParkingTicket parkCar(Car car) {
        if(getAvailableParkingPosition() < 0){
            ParkingTicket parkingTicket = new ParkingTicket();
            this.cars.put(parkingTicket,car);
            return parkingTicket;
        }
        parkingBoy.setLastErrorMessage(parkingBoy.NOT_ENOUGH_POSITION_ERR_MSG);
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
