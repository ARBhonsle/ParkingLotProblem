package parkinglot;

public class AirportSecurity implements ParkingLotObserver {
    private boolean isParkingLotCapacityFull;

    public void capacityIsFull() {
        this.isParkingLotCapacityFull = true;
    }

    public boolean isParkingLotCapacityFull() {
        return isParkingLotCapacityFull;
    }
}
