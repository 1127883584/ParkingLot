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
