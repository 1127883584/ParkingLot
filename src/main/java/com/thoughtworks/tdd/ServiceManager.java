package com.thoughtworks.tdd;

import java.util.HashMap;

public class ServiceManager extends ParkingBoy{
    private HashMap<String, ParkingBoy> managementList;

    public ServiceManager(ParkingLots parkingLots, String id){
        super(parkingLots, id);
        managementList = new HashMap<>();
    }

    public void addParkingBoysToList(ParkingBoy parkingBoy) {
        managementList.put(parkingBoy.getId(), parkingBoy);
    }

    public ParkCarResult orderParkingBoyToPark(Car car, ParkingBoy parkingBoy) {
        ParkingBoy parkingBoyTemp = managementList.get(parkingBoy.getId());
        ParkCarResult parkCarResult = new ParkCarResult();
        if (parkingBoyTemp == null) {
            parkCarResult.setMessage("This parking boy is not in your management list.");
            parkCarResult.setTicket(null);
        } else {
            parkCarResult = parkingBoy.park(car, this);
        }
        return parkCarResult;
    }
}
