package com.thoughtworks.tdd;

import java.util.HashMap;

public class ParkingLot {
    private HashMap<Ticket, Car> parkingCarTicket;
    private int capacity = 10;

    public ParkingLot(){
        parkingCarTicket = new HashMap<>();
    }

    public ParkCarResult park(Car car){
        ParkCarResult parkCarResult = new ParkCarResult();

        //when the capacity is full
        if (parkingCarTicket.size() == capacity) {
            parkCarResult.setTicket(null);
            parkCarResult.setMessage("Not enough position.");
        } else {
            //when Passing a parked car or Passing a null car.
            if (car == null || parkingCarTicket.containsValue(car)) {
                parkCarResult.setTicket(null);
                parkCarResult.setMessage("Car not park a parked car or park a null car.");
            } else {
                Ticket ticket = new Ticket();
                parkingCarTicket.put(ticket, car);
                parkCarResult.setTicket(ticket);
                parkCarResult.setMessage("Success park car.");
            }
        }
        return parkCarResult;
    }

    public GetCarResult getCar(Ticket ticket) {
        GetCarResult getCarResult = new GetCarResult();
        if (ticket == null) {
            getCarResult.setCar(null);
            getCarResult.setMessage("Please provide your parking ticket.");
        } else {
            Car car = parkingCarTicket.get(ticket);
            getCarResult.setCar(car);
            if (car == null) {
                getCarResult.setMessage("Unrecognized parking ticket.");
            } else {
                parkingCarTicket.remove(ticket);
                getCarResult.setMessage("Success fetch the car.");
            }
        }

        return getCarResult;
    }
}
