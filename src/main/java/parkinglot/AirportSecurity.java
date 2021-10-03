package parkinglot;

public class AirportSecurity implements ParkingLotObserver {
    private boolean isParkingLotCapacityFull;

    @Override
    public void capacityIsFull() {
        this.isParkingLotCapacityFull = true;
    }

    @Override
    public void capacityHasSpaceAvailable() {
        this.isParkingLotCapacityFull = false;
    }

    public boolean isParkingLotCapacityFull() {
        return isParkingLotCapacityFull;
    }
}
