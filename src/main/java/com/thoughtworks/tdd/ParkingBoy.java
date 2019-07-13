package com.thoughtworks.tdd;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
    }

    public Ticket park(Car car) throws Exception {
        Ticket ticket = parkingLot.park(car);
        return ticket;
    }

    public Car fetch(Ticket ticket) throws Exception{
        Car car = parkingLot.getCar(ticket);
        return car;
    }
}
