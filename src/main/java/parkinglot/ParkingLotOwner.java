package parkinglot;

public class ParkingLotOwner implements ParkingLotObserver {
    private boolean isParkingLotCapacityFull;

    public void capacityIsFull() {
        this.isParkingLotCapacityFull = true;
    }

    public boolean isParkingLotCapacityFull() {
        return this.isParkingLotCapacityFull;
    }
}
