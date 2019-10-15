package com.oocl.cultivation;

import java.util.Map;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private ParkingLot parkingLot2;
    private String lastErrorMessage;
    private Car car;

    public ParkingLot getParkingLot2() {
        return parkingLot2;
    }

    public void setLastErrorMessage(String lastErrorMessage) {
        this.lastErrorMessage = lastErrorMessage;
    }



    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = parkingLot.parkCar(car);

        if(parkingTicket == null){
            setLastErrorMessage("Not enough position.");
            parkToAnotherParkingLot(car);
        }
        return parkingTicket;

    }

    public ParkingTicket parkToAnotherParkingLot(Car car){
        ParkingLot parkingLot = new ParkingLot();
        this.parkingLot2 = parkingLot;

        return parkingLot2.parkCar(car);
    }
    public Car fetch(ParkingTicket ticket) {

        Car fetchedCar = parkingLot.getCar(ticket);

        if (fetchedCar==null){
            setLastErrorMessage("Unrecognized parking ticket.");
            return null;
        }
        return fetchedCar;
    }

    public void fetch(){
        setLastErrorMessage("Please Provide your Parking ticket");
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
