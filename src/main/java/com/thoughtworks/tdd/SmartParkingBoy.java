package com.thoughtworks.tdd;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLots parkingLots){
        super(parkingLots);
    }

    public ParkCarResult smartPark(Car car) {
        return super.getParkingLots().smartPark(car);
    }
}
