package parkinglot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingLotService {
    private int actualParkingCapacity;
    public List parkedVehicles;
    private List<ParkingLotObserver> observers;
    private int[] parkingSlots;

    public ParkingLotService(int actualParkingCapacity) {
        this.parkingSlots = new int[100];
        Arrays.fill(parkingSlots, 0);
        this.observers = new ArrayList<>();
        this.parkedVehicles = new ArrayList();
        this.actualParkingCapacity = actualParkingCapacity;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Parking Lot Service System");
    }

    public int getAvailableParkingSlots() throws ParkingLotException {
        for (int parkingSlot : parkingSlots) {
            if (parkingSlot == 0) {
                return parkingSlot;
            }
        }
        throw new ParkingLotException("Parking Lot is Full");
    }

    public void setActualParkingCapacity(int actualParkingCapacity) {
        this.actualParkingCapacity = actualParkingCapacity;
        this.parkingSlots = new int[actualParkingCapacity];
    }

    public int findVehicle(Object vehicle){
        return parkedVehicles.indexOf(vehicle)+1;
    }
    public boolean isVehicleParked(Object vehicle) {
        return this.parkedVehicles.contains(vehicle);
    }

    public boolean isParkingLotFull() {
        return this.parkedVehicles.size() == this.actualParkingCapacity;
    }

    public void registerObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    public void park(int index, Object vehicle) throws ParkingLotException {
        if (isParkingLotFull()) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException("Parking Lot is Full");
        }
        if (this.isVehicleParked(vehicle)) {
            throw new ParkingLotException("Vehicle is Already Parked");
        }
        parkingSlots[index] = 1;
        this.parkedVehicles.add(vehicle);
    }

    public void park(Object vehicle) throws ParkingLotException {
        int index = this.getAvailableParkingSlots();
        this.park(index, vehicle);

    }

    public boolean unPark(Object vehicle) throws ParkingLotException {
        if (this.parkedVehicles.size() == 0) {
            throw new ParkingLotException("No Vehicle parked");
        }
        if (this.isVehicleParked(vehicle)) {
            parkedVehicles.remove(vehicle);
            for (ParkingLotObserver observer : observers) {
                observer.capacityHasSpaceAvailable();
            }
            return true;
        }
        return false;
    }

}
