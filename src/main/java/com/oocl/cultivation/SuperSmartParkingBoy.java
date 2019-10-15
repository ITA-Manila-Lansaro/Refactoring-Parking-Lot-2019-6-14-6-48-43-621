package com.oocl.cultivation;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy{

    public SuperSmartParkingBoy(List<ParkingLot> parkingLot) {
        super(parkingLot);
    }


    @Override
    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket;

        parkingTicket = parkingLotList.stream()
                .reduce((a,b) ->  a.getAvailableParkingPosition() > b.getAvailableParkingPosition() ? b: a)
                .map(a -> a.parkCar(car))
                .orElse(null);

        return parkingTicket;
    }

//    private ParkingTicket distributeCarToParkingLots(Car car) {
//        ParkingTicket parkingTicket = new ParkingTicket();
//
////        if (parkingLot2 == null){
////            return getParkingLot().parkCar(car);
////        }
////
////        if(parkingLot.getAvailableParkingPosition() < parkingLot2.getAvailableParkingPosition()) {
////            parkingTicket = parkingLot.parkCar(car);
////        }else {
////            parkingTicket = parkingLot2.parkCar(car);
////        }
//        return parkingTicket;
//    }
}
