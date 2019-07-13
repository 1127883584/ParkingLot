package com.thoughtworks.tdd;

import java.util.HashMap;

public class ParkingLot {
    private HashMap<Ticket, Car> parkingCarTicket;
    private int capacity;
    private ParkingLots parkingLots;

    public ParkingLot(int capacity){
        parkingCarTicket = new HashMap<>();
        this.capacity = capacity;
    }

    public HashMap<Ticket, Car> getParkingCarTicket() {
        return parkingCarTicket;
    }

    public int getCapacity() {
        return capacity;
    }

    public ParkCarResult park(Car car){
        ParkCarResult parkCarResult = parkingLots.park(car);
        return parkCarResult;
    }

    public GetCarResult getCar(Ticket ticket) {
        GetCarResult getCarResult = parkingLots.getCar(ticket);
        return getCarResult;
    }
}
