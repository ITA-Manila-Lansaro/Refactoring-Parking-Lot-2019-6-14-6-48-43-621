package com.oocl.cultivation;

import java.util.List;
import java.util.Objects;

public class ParkingBoy {

    public static final String NOT_ENOUGH_POSITION_ERR_MSG = "Not enough position.";
    public static final String NO_TICKET_ERR_MSG = "Please Provide your Parking ticket";
    public static final String UNRECOGNIZED_TICKET_ERR_MSG = "Unrecognized parking ticket.";
    public List<ParkingLot> parkingLotList;

    public List<ParkingLot> getParkingLot() {
        return parkingLotList;
    }
    private String lastErrorMessage;

    public void setLastErrorMessage(String lastErrorMessage) {
        this.lastErrorMessage = lastErrorMessage;
    }

    public ParkingBoy(List<ParkingLot> parkingLot) {
        this.parkingLotList = parkingLot;
    }

    public ParkingTicket park(Car car) {

        ParkingLot parkingLot =  parkingLotList.stream()
                .filter(a -> a.getAvailableParkingPosition() < 0)
                .findFirst()
                .orElse(null);
        if(parkingLot != null)
        {
            return parkingLot.parkCar(car);
        }
        setLastErrorMessage(NOT_ENOUGH_POSITION_ERR_MSG);

        return null;

    }

    public Car fetch(ParkingTicket ticket) {
        Car fetchedCar;

        fetchedCar = parkingLotList.stream()
                .map(a -> a.getCar(ticket))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);

        if(fetchedCar == null){
            setLastErrorMessage(UNRECOGNIZED_TICKET_ERR_MSG);
        }
        return fetchedCar;
    }

    public void fetch(){
        setLastErrorMessage(NO_TICKET_ERR_MSG);
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
