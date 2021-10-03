package parkinglot;

public interface ParkingLotObserver {
    void capacityIsFull();

    boolean isParkingLotCapacityFull();

    void capacityHasSpaceAvailable();
}
