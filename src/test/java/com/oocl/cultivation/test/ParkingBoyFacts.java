package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {
    @Test
    void should_return_ticket_when_parking_boy_parks_at_the_parking_lot() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLotList= new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        ParkingTicket parkingTicket = parkingBoy.park(car);

        assertNotNull(parkingLot.getCars());
    }

    @Test
    void should_return_car_when_parking_boy_fetch_car_from_parking_lot() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLotList= new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        ParkingTicket parkingTicket = parkingBoy.park(car);

        assertEquals(car, parkingBoy.fetch(parkingTicket));
    }

    @Test
    void should_return_no_car_when_customer_gives_wrong_ticket(){
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLotList= new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        ParkingTicket parkingTicket = parkingBoy.park(car);
        ParkingTicket wrongParkingTicket = parkingBoy.park(new Car());

        assertNotEquals(wrongParkingTicket, parkingBoy.fetch(parkingTicket));
    }

    @Test
    void should_fetch_no_car_if_customer_gives_ticket_that_has_already_been_used(){
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLotList= new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(parkingTicket);
        assertEquals(car, fetchedCar);

        Car fetchCarAgain = parkingBoy.fetch(parkingTicket);
        assertNull(fetchCarAgain);
    }

    @Test
    void should_return_no_ticket_if_the_park_is_full(){

        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLotList= new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        IntStream.rangeClosed(0,10).forEach( a ->
                parkingBoy.park(new Car())
                );

        ParkingTicket excessCar = parkingBoy.park(new Car());

        assertNull(excessCar);
    }

    @Test
    void should_return_error_message_when_customer_gives_wrong_ticket(){
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLotList= new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        parkingBoy.fetch(new ParkingTicket());

        assertEquals(parkingBoy.getLastErrorMessage(), "Unrecognized parking ticket.");
    }

    @Test
    void should_return_error_message_when_customer_gives_no_ticket(){
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLotList= new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        parkingBoy.fetch();

        assertEquals(parkingBoy.getLastErrorMessage(), "Please Provide your Parking ticket");
    }

    @Test
    void should_return_error_message_if_the_park_is_full(){

        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLotList= new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        IntStream.rangeClosed(0,23).forEach( a ->
                parkingBoy.park(new Car())
        );

        ParkingTicket excessCar = parkingBoy.park(new Car());

        assertEquals(parkingBoy.getLastErrorMessage(), "Not enough position.");
    }

    @Test
    void should_park_into_second_parking_lot_when_first_parking_lot_is_full(){
        List<ParkingLot> parkingLotList= new ArrayList<>();
        parkingLotList.add(new ParkingLot());
        parkingLotList.add(new ParkingLot());
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        IntStream.rangeClosed(0,9).forEach( a ->
                parkingBoy.park(new Car())
        );

        parkingBoy.park(new Car());
        assertEquals(parkingBoy.parkingLotList.get(parkingLotList.size() - 1).getCars().size() , 1);
    }

    @Test
    void should_return_car_when_super_parking_boy_fetch_car_from_parking_lot() {
        Car car = new Car();
        List<ParkingLot> parkingLotList= new ArrayList<>();
        parkingLotList.add(new ParkingLot());
        parkingLotList.add(new ParkingLot());
        SuperSmartParkingBoy superParkingBoy = new SuperSmartParkingBoy(parkingLotList);

        ParkingTicket parkingTicket = superParkingBoy.park(car);
        assertEquals(car, superParkingBoy.fetch(parkingTicket));
    }

    @Test
    void should_super_smart_parking_boy_park_to_parking_lot_with_larger_capacity(){

        List<ParkingLot> parkingLotList= new ArrayList<>();
        parkingLotList.add(new ParkingLot());
        parkingLotList.add(new ParkingLot());
        SuperSmartParkingBoy superParkingBoy = new SuperSmartParkingBoy(parkingLotList);

        IntStream.rangeClosed(0 , 9).forEach( a ->
                superParkingBoy.park(new Car())
        );

        assertEquals(superParkingBoy.parkingLotList.stream().findFirst().orElse(null).getCars().size() , 5);
        assertEquals(superParkingBoy.parkingLotList.get(parkingLotList.size() - 1).getCars().size() , 5);
    }

}
