package com.thoughtworks.tdd;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
    }

    public ParkCarResult park(Car car) {
        ParkCarResult parkCarResult = parkingLot.park(car);
        return parkCarResult;
    }

    public GetCarResult fetch(Ticket ticket){
        GetCarResult getCarResult = parkingLot.getCar(ticket);
        return getCarResult;
    }
}
