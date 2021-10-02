package parkinglot;

public class ParkingLotOwner {
    private boolean isParkingLotCapacityFull;

    public void capacityIsFull() {
        this.isParkingLotCapacityFull = true;
    }

    public boolean isParkingLotCapacityFull() {
        return this.isParkingLotCapacityFull;
    }
}
