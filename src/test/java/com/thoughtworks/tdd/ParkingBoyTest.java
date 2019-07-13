package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingBoyTest {
    @Test
    public void should_return_car_when_park_car_to_parking_lot_then_get_it_back() {
        //given
        ParkingLot[] parkingLotArray = new ParkingLot[]{new ParkingLot(5)};
        Car car = new Car();
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Ticket ticket = parkingBoy.park(car).getTicket();
        Car fetchedCar = parkingBoy.fetch(ticket).getCar();

        //then
        assertSame(car, fetchedCar);
    }

    @Test
    public void should_multiple_cars_when_use_correspond_ticket() {
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingLot[] parkingLotArray = new ParkingLot[]{new ParkingLot(5)};
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Ticket firstTicket = parkingBoy.park(firstCar).getTicket();
        Ticket secondTicket = parkingBoy.park(secondCar).getTicket();
        Car fetchedFirstCar = parkingBoy.fetch(firstTicket).getCar();
        Car fetchedSecondCar = parkingBoy.fetch(secondTicket).getCar();

        //then
        assertSame(firstCar, fetchedFirstCar);
        assertSame(secondCar, fetchedSecondCar);
    }

    @Test
    public void should_not_fetch_car_when_ticket_is_wrong() {
        ParkingLot[] parkingLotArray = new ParkingLot[]{new ParkingLot(5)};
        Car car = new Car();
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket wrongTicket = new Ticket(0);

        //when
        parkingBoy.park(car);

        assertSame(null, parkingBoy.fetch(wrongTicket).getCar());
    }

    @Test
    public void should_not_fetch_when_ticket_has_been_used() {
        //given
        ParkingLot[] parkingLotArray = new ParkingLot[]{new ParkingLot(5)};
        Car car = new Car();
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Ticket ticket = parkingBoy.park(car).getTicket();
        parkingBoy.fetch(ticket);

        assertSame(null, parkingBoy.fetch(ticket).getCar());
    }

    @Test
    public void should_not_park_car_when_parking_lot_capacity_is_full() {
        ParkingLot[] parkingLotArray = new ParkingLot[]{new ParkingLot(5)};
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        for(int i = 0; i < 10; i ++) {
            Car car = new Car();
            parkingBoy.park(car);
        }
        Car overflowCar = new Car();

        assertSame(null, parkingBoy.park(overflowCar).getTicket());
    }

    @Test
    public void should_not_park_car_when_park_a_parked_car() {
        //given
        ParkingLot[] parkingLotArray = new ParkingLot[]{new ParkingLot(5)};
        Car car = new Car();
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        parkingBoy.park(car);

        assertSame(null, parkingBoy.park(car).getTicket());
    }

    @Test
    public void should_not_park_car_when_park_a_null_car(){
        ParkingLot[] parkingLotArray = new ParkingLot[]{new ParkingLot(5)};
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        assertSame(null, parkingBoy.park(null).getTicket());
    }

    @Test
    public void should_return_error_message_when_ticket_is_wrong() {
        ParkingLot[] parkingLotArray = new ParkingLot[]{new ParkingLot(5)};
        Car car = new Car();
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Ticket ticket = new Ticket(0);
        parkingBoy.park(car);

        assertThat(parkingBoy.fetch(ticket).getMessage(), is("Unrecognized parking ticket."));
    }

    @Test
    public void should_return_error_message_when_ticket_has_been_used() {
        ParkingLot[] parkingLotArray = new ParkingLot[]{new ParkingLot(5)};
        Car car = new Car();
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Ticket ticket = parkingBoy.park(car).getTicket();
        parkingBoy.fetch(ticket);

        assertThat(parkingBoy.fetch(ticket).getMessage(), is("Unrecognized parking ticket."));
    }

    @Test
    public void should_return_error_message_when_not_provide_ticket() {
        ParkingLot[] parkingLotArray = new ParkingLot[]{new ParkingLot(5)};
        Car car = new Car();
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        parkingBoy.park(car);

        assertThat(parkingBoy.fetch(null).getMessage(), is("Please provide your parking ticket."));
    }

    @Test
    public void should_return_error_message_when_park_car_into_parking_lot_without_position() {
        ParkingLot[] parkingLotArray = new ParkingLot[]{new ParkingLot(5)};
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        for(int i = 0; i < 5; i ++) {
            Car car = new Car();
            parkingBoy.park(car);
        }
        Car overflowCar = new Car();

        assertThat(parkingBoy.park(overflowCar).getMessage(), is("Not enough position."));
    }

    @Test
    public void should_return_the_second_parking_lot_when_the_first_parking_lot_is_full() {
        ParkingLot[] parkingLotArray = new ParkingLot[]{new ParkingLot(5), new ParkingLot(8), new ParkingLot(10)};
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        for(int i = 0; i < 10; i ++) {
            Car car = new Car();
            parkingBoy.park(car);
        }

        Car overflowCar = new Car();
        Ticket ticket = parkingBoy.park(overflowCar).getTicket();
        assertSame(1, ticket.getParkingLotId());
    }

    @Test
    public void should_return_the_right_parking_lot_id_when_the_smart_parking_boy_part_car() {
        ParkingLot[] parkingLotArray = new ParkingLot[]{new ParkingLot(5), new ParkingLot(8), new ParkingLot(10)};
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        for(int i = 0; i < 6; i ++) {
            Car car = new Car();
            smartParkingBoy.smartPark(car);
        }

        Car overflowCar = new Car();
        Ticket ticket = smartParkingBoy.smartPark(overflowCar).getTicket();
        assertSame(1, ticket.getParkingLotId());
    }

    @Test
    public void should_return_the_right_parking_lot_id_when_the_super_smart_parking_boy_part_car() {
        ParkingLot[] parkingLotArray = new ParkingLot[]{new ParkingLot(5), new ParkingLot(8), new ParkingLot(10)};
        ParkingLots parkingLots = new ParkingLots(parkingLotArray);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        for(int i = 0; i < 6; i ++) {
            Car car = new Car();
            superSmartParkingBoy.superSmartPark(car);
        }

        Car overflowCar = new Car();
        ParkCarResult parkCarResult = superSmartParkingBoy.superSmartPark(overflowCar);
        Ticket ticket = parkCarResult.getTicket();
        assertSame(2, ticket.getParkingLotId());
    }
}
