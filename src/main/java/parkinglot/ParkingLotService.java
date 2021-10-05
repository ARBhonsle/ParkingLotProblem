package parkinglot;

import java.util.*;

public class ParkingLotService {
    private int actualParkingCapacity;
    private List<ParkingSlot> parkingSlot;
    private List<ParkingLotObserver> observers;

    public ParkingLotService(int actualParkingCapacity) {
        this.observers = new ArrayList<>();
        this.parkingSlot = new ArrayList<>();
        this.actualParkingCapacity = actualParkingCapacity;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Parking Lot Service System");
    }

    public int getAvailableParkingSlots() throws ParkingLotException {
        // no filled slots
        if (parkingSlot.size() == 0) {
            return 0;
        }
        // find the nearest unparked slot
        int counter = 0;
        for (ParkingSlot slot : parkingSlot) {
            if (slot == null) {
                return counter;
            }
            counter++;
        }
        // find empty parking slots
        if (parkingSlot.size() < actualParkingCapacity) {
            return parkingSlot.size();
        }
        throw new ParkingLotException("Parking Lot is Full");
    }

    public void setActualParkingCapacity(int parkingCapacity) {
        this.actualParkingCapacity = parkingCapacity;
    }

    public int findVehicle(Object vehicle) throws ParkingLotException {
        for (ParkingSlot slot : parkingSlot.stream().toList()) {
            if (slot.getVehicle().equals(vehicle)) {
                return slot.getParkingSlotIndex();
            }
        }
        throw new ParkingLotException("Given Vehicle Not Parked in Parking Lot");
    }

    public boolean isVehicleParked(Object vehicle) throws ParkingLotException {
        try {
            return findVehicle(vehicle) < this.actualParkingCapacity;
        } catch (ParkingLotException e) {
            if (e.getMessage().equals("Given Vehicle Not Parked in Parking Lot")) {
                return false;
            }
        }
        return false;
    }

    public boolean isParkingLotFull() {
        return this.parkingSlot.size() == this.actualParkingCapacity;
    }

    public long getEntryTimeOfParkedVehicle(Integer index) {
        return this.parkingSlot.get(index).getEntryTime();
    }

    public void registerObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    public void park(int index, Object vehicle) {
        this.parkingSlot.add(new ParkingSlot(index, vehicle));
    }

    public void park(Object vehicle) throws ParkingLotException {
        if (isParkingLotFull()) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException("Parking Lot is Full");
        }
        if (this.isVehicleParked(vehicle)) {
            throw new ParkingLotException("Vehicle is Already Parked");
        }
        int index = this.getAvailableParkingSlots();
        this.park(index, vehicle);

    }

    public void unPark(Object vehicle) throws ParkingLotException {
        if (this.parkingSlot.size() == 0) {
            throw new ParkingLotException("No Vehicle parked");
        }
        if (this.isVehicleParked(vehicle)) {
            parkingSlot.remove(findVehicle(vehicle));
            for (ParkingLotObserver observer : observers) {
                observer.capacityHasSpaceAvailable();
            }
        }
    }

}
