package parkinglot;

import java.util.*;

public class ParkingLotService {
    private int actualParkingCapacity;
    public HashMap<Integer, Object> parkedVehicles;
    private List<ParkingLotObserver> observers;

    public ParkingLotService(int actualParkingCapacity) {
        this.observers = new ArrayList<>();
        this.parkedVehicles = new HashMap<>();
        this.actualParkingCapacity = actualParkingCapacity;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Parking Lot Service System");
    }

    public int getAvailableParkingSlots() throws ParkingLotException {
        if (parkedVehicles.keySet().size() == 0) {
            return 1;
        }
        int counter = 1;
        for (Object parkingSlot : parkedVehicles.values()) {
            if (parkingSlot == null) {
                return counter;
            }
            counter++;
        }
        if (parkedVehicles.values().size() < actualParkingCapacity) {
            return parkedVehicles.values().size();
        }
        throw new ParkingLotException("Parking Lot is Full");
    }

    public void setActualParkingCapacity(int actualParkingCapacity) {
        this.actualParkingCapacity = actualParkingCapacity;
    }

    public int findVehicle(Object vehicle) throws ParkingLotException {
        for (Integer key : parkedVehicles.keySet()) {
            if (parkedVehicles.get(key).equals(vehicle)) {
                return key;
            }
        }
        throw new ParkingLotException("Given Vehicle Not Parked in Parking Lot");
    }

    public boolean isVehicleParked(Object vehicle) {
        return this.parkedVehicles.containsValue(vehicle);
    }

    public boolean isParkingLotFull() {
        return this.parkedVehicles.keySet().size() == this.actualParkingCapacity;
    }

    public void registerObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    public void park(int index, Object vehicle) throws ParkingLotException {
        if (this.isVehicleParked(vehicle)) {
            throw new ParkingLotException("Vehicle is Already Parked");
        }
        this.parkedVehicles.put(index, vehicle);
    }

    public void park(Object vehicle) throws ParkingLotException {
        if (isParkingLotFull()) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException("Parking Lot is Full");
        }
        int index = this.getAvailableParkingSlots();
        this.park(index, vehicle);

    }

    public void unPark(Object vehicle) throws ParkingLotException {
        if (this.parkedVehicles.keySet().size() == 0) {
            throw new ParkingLotException("No Vehicle parked");
        }
        if (this.isVehicleParked(vehicle)) {
            parkedVehicles.values().remove(vehicle);
            for (ParkingLotObserver observer : observers) {
                observer.capacityHasSpaceAvailable();
            }
        }
    }

}
