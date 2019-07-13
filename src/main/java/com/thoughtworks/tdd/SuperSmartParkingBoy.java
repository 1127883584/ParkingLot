package com.thoughtworks.tdd;

public class SuperSmartParkingBoy extends SmartParkingBoy {
    public SuperSmartParkingBoy(ParkingLots parkingLots){
        super(parkingLots);
    }

    public ParkCarResult superSmartPark(Car car) {
        return super.getParkingLots().superSmartPark(car);
    }
}
