package com.thoughtworks.tdd;

import java.util.HashMap;

public class ParkingLot {
    private HashMap<Ticket, Car> parkingCarTicket;
    private int capacity = 10;

    public ParkingLot(){
        parkingCarTicket = new HashMap<>();
    }

    public Ticket park(Car car) throws Exception {
        if (parkingCarTicket.size() == capacity) {
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
