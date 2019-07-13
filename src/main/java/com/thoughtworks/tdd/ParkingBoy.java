package com.thoughtworks.tdd;

public class ParkingBoy {
    private ParkingLots parkingLots;

    public ParkingBoy(ParkingLots parkingLots){
        this.parkingLots = parkingLots;
    }

    public ParkCarResult park(Car car) {
        ParkCarResult parkCarResult = parkingLots.park(car);
        return parkCarResult;
    }

    public GetCarResult fetch(Ticket ticket){
        GetCarResult getCarResult = parkingLots.getCar(ticket);
        return getCarResult;
    }
}
