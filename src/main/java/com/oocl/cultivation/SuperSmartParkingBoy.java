package com.oocl.cultivation;

public class SuperSmartParkingBoy {
    public static final String NO_TICKER_ERR_MSG = "Please Provide your Parking ticket";
    public static final String UNREGOCNIZED_TICKET_ERR_MSG = "Unrecognized parking ticket.";
    private final ParkingLot parkingLot;
    private ParkingLot parkingLot2;
    private String lastErrorMessage;

    public ParkingLot getParkingLot2() {
        return parkingLot2;
    }

    public ParkingLot getParkingLot(){
        return parkingLot;
    }

    public void setLastErrorMessage(String lastErrorMessage) {
        this.lastErrorMessage = lastErrorMessage;
    }

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
    public SuperSmartParkingBoy(ParkingLot parkingLot, ParkingLot parkingLot2) {
        this.parkingLot = parkingLot;
        this.parkingLot2 = parkingLot2;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket;

        parkingTicket = distributeCarToParkingLots(car);

        return parkingTicket;

    }

    private ParkingTicket distributeCarToParkingLots(Car car) {
        ParkingTicket parkingTicket;
        
        if(parkingLot.getAvailableParkingPosition() > parkingLot2.getAvailableParkingPosition()) {
            parkingTicket = parkingLot2.parkCar(car);
        }else {
            parkingTicket = parkingLot.parkCar(car);
        }
        return parkingTicket;
    }

    public Car fetch(ParkingTicket ticket) {

        Car fetchedCar = parkingLot.getCar(ticket);

        if (fetchedCar==null){
            setLastErrorMessage(UNREGOCNIZED_TICKET_ERR_MSG);
            return null;
        }
        return fetchedCar;
    }

    public void fetch(){
        setLastErrorMessage(NO_TICKER_ERR_MSG);
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
