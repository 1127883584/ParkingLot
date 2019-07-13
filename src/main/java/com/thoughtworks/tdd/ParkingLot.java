package com.thoughtworks.tdd;

import java.util.HashMap;

public class ParkingLot {
    private HashMap<Ticket, Car> parkingCarTicket;
    private int capacity = 10;

    public ParkingLot(){
        parkingCarTicket = new HashMap<>();
    }

    public Ticket park(Car car) throws Exception {
        //when the capacity is full
        if (parkingCarTicket.size() == capacity) {
            throw new Exception();
        }

        //when Passing a parked car or Passing a null car.
        if (car == null || parkingCarTicket.containsValue(car)) {
            throw new Exception();
        }

        Ticket ticket = new Ticket();
        parkingCarTicket.put(ticket, car);
        return ticket;
    }

    public Car getCar(Ticket ticket) throws Exception {
        Car car = parkingCarTicket.get(ticket);
        if (car == null) {
            throw new Exception();
        }
        parkingCarTicket.remove(ticket);
        return car;
    }
}
