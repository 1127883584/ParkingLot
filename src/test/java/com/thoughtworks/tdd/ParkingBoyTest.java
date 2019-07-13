package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingBoyTest {
    @Test
    public void should_return_car_when_park_car_to_parking_lot_then_get_it_back() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        Ticket ticket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(ticket);

        //then
        assertSame(car, fetchedCar);
    }

    @Test
    public void should_multiple_cars_when_use_correspond_ticket() throws Exception {
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        Ticket firstTicket = parkingBoy.park(firstCar);
        Ticket secondTicket = parkingBoy.park(secondCar);
        Car fetchedFirstCar = parkingBoy.fetch(firstTicket);
        Car fetchedSecondCar = parkingBoy.fetch(secondTicket);

        //then
        assertSame(firstCar, fetchedFirstCar);
        assertSame(secondCar, fetchedSecondCar);
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_wrong() throws Exception {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Ticket wrongTicket = new Ticket();

        //when
        parkingBoy.park(car);

        Assertions.assertThrows(Exception.class, () -> {
            parkingBoy.fetch(wrongTicket);
        });
    }

    @Test
    public void should_not_fetch_when_ticket_is_used() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        Ticket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);

        Assertions.assertThrows(Exception.class, () -> {
            parkingBoy.fetch(ticket);
        });
    }

    @Test
    public void should_not_park_car_when_parking_lot_capacity_is_full() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        for(int i = 0; i < 10; i ++) {
            Car car = new Car();
            parkingBoy.park(car);
        }
        Car overflowCar = new Car();
        Assertions.assertThrows(Exception.class, () -> {
            parkingBoy.park(overflowCar);
        });
    }

    @Test
    public void should_not_park_car_when_park_a_parked_car() throws Exception {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        parkingBoy.park(car);

        Assertions.assertThrows(Exception.class, () -> {
            parkingBoy.park(car);
        });
    }

    @Test
    public void should_not_park_car_when_park_a_null_car(){
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Assertions.assertThrows(Exception.class, () -> {
            parkingBoy.park(null);
        });
    }
}
