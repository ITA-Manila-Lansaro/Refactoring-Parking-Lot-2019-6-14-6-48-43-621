package com.oocl.cultivation;

public class SuperSmartParkingBoy extends ParkingBoy{

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SuperSmartParkingBoy(ParkingLot parkingLot, ParkingLot parkingLot2) {
        super(parkingLot, parkingLot2);
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket;

        parkingTicket = distributeCarToParkingLots(car);

        return parkingTicket;
    }

    private ParkingTicket distributeCarToParkingLots(Car car) {
        ParkingTicket parkingTicket;

        if (parkingLot2 == null){
            return getParkingLot().parkCar(car);
        }

        if(parkingLot.getAvailableParkingPosition() < parkingLot2.getAvailableParkingPosition()) {
            parkingTicket = parkingLot.parkCar(car);
        }else {
            parkingTicket = parkingLot2.parkCar(car);
        }
        return parkingTicket;
    }
}
