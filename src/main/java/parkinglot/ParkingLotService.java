package parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotService {
    private int actualParkingCapacity;
    public List parkedVehicles;
    private List<ParkingLotObserver> observers;

    public ParkingLotService(int actualParkingCapacity) {
        this.observers = new ArrayList<>();
        this.parkedVehicles = new ArrayList();
        this.actualParkingCapacity = actualParkingCapacity;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Parking Lot Service System");
    }

    public void setActualParkingCapacity(int actualParkingCapacity) {
        this.actualParkingCapacity = actualParkingCapacity;
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
        this.parkedVehicles.add(vehicle);
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
