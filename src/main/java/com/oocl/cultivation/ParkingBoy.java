package com.oocl.cultivation;

public class ParkingBoy {

    public static final String NOT_ENOUGH_POSITION_ERR_MSG = "Not enough position.";
    public static final String NO_TICKET_ERR_MSG = "Please Provide your Parking ticket";
    public static final String UNRECOGNIZED_TICKET_ERR_MSG = "Unrecognized parking ticket.";

    public ParkingLot parkingLot;
    public ParkingLot parkingLot2;

    public ParkingLot getParkingLot() {
        return parkingLot;
    }
    private String lastErrorMessage;

    public ParkingLot getParkingLot2() {
        return parkingLot2;
    }

    public void setLastErrorMessage(String lastErrorMessage) {
        this.lastErrorMessage = lastErrorMessage;
    }



    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
    public ParkingBoy(ParkingLot parkingLot, ParkingLot parkingLot2) {
        this.parkingLot = parkingLot;
        this.parkingLot2 = parkingLot2;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = parkingLot.parkCar(car);

        parkToAnotherParkingLotIfParkingIsFull(car, parkingTicket);

        return parkingTicket;

    }

    private void parkToAnotherParkingLotIfParkingIsFull(Car car, ParkingTicket parkingTicket) {
        if(parkingTicket == null){
            setLastErrorMessage(NOT_ENOUGH_POSITION_ERR_MSG);
            parkToAnotherParkingLot(car);
        }
    }

    public ParkingTicket parkToAnotherParkingLot(Car car){
        ParkingLot parkingLot = new ParkingLot();
        this.parkingLot2 = parkingLot;

        return parkingLot2.parkCar(car);
    }
    public Car fetch(ParkingTicket ticket) {
        Car fetchedCar = parkingLot.getCar(ticket);

        if (parkingLot2 != null){
            fetchedCar = parkingLot.getCar(ticket);
            fetchedCar = fetchedCar != null ? fetchedCar : parkingLot2.getCar(ticket);
        }

        if (hasFetchedCar(fetchedCar)) return null;

        return fetchedCar;
    }

    private boolean hasFetchedCar(Car fetchedCar) {
        if (fetchedCar==null){
            setLastErrorMessage(UNRECOGNIZED_TICKET_ERR_MSG);
            return true;
        }
        return false;
    }


    public void fetch(){
        setLastErrorMessage(NO_TICKET_ERR_MSG);
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
