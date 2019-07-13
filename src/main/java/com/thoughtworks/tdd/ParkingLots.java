package com.thoughtworks.tdd;

import java.util.HashMap;

public class ParkingLots {
    private HashMap<Integer, ParkingLot> parkinglots;
    private int capacity;

    public ParkingLots(int capacity) {
        this.parkinglots = new HashMap<>();
        this.capacity = capacity;
        generateEveryParkingLot();
    }

    public ParkCarResult park(Car car){
        ParkCarResult parkCarResult = new ParkCarResult();
        for (Integer key : parkinglots.keySet()) {
            ParkingLot parkingLot = parkinglots.get(key);
            if (parkingLot.getParkingCarTicket().size() == parkingLot.getCapacity()) {
                parkCarResult.setTicket(null);
                parkCarResult.setMessage("Not enough position.");
            } else {
                if (car == null || parkingLot.getParkingCarTicket().containsValue(car)) {
                    parkCarResult.setTicket(null);
                    parkCarResult.setMessage("Car not park a parked car or park a null car.");
                } else {
                    Ticket ticket = new Ticket(key);
                    parkingLot.getParkingCarTicket().put(ticket, car);
                    parkCarResult.setTicket(ticket);
                    parkCarResult.setMessage("Success park car.");
                    break;
                }
            }
        }
        return parkCarResult;
    }

    public GetCarResult getCar(Ticket ticket) {
        GetCarResult getCarResult = new GetCarResult();
        if (ticket == null) {
            System.out.println("asdasdas");
            getCarResult.setCar(null);
            getCarResult.setMessage("Please provide your parking ticket.");
        } else {
            ParkingLot parkingLot = parkinglots.get(ticket.getParkingLotId());
            Car car = parkingLot.getParkingCarTicket().get(ticket);
            getCarResult.setCar(car);
            if (car == null) {
                getCarResult.setMessage("Unrecognized parking ticket.");
            } else {
                parkingLot.getParkingCarTicket().remove(ticket);
                getCarResult.setMessage("Success fetch the car.");
            }
        }

        return getCarResult;
    }

    public void generateEveryParkingLot() {
        for (int i = 0; i < capacity; i ++) {
            ParkingLot parkingLot = new ParkingLot(10);
            parkinglots.put(i, parkingLot);
        }
    }

}
