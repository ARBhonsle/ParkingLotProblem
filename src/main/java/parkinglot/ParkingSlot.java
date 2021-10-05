package parkinglot;

public class ParkingSlot {
    private final Integer parkingSlotIndex;
    private final Object vehicle;
    private final long entryTime;

    ParkingSlot(Integer index, Object vehicle) {
        this.parkingSlotIndex = index;
        this.vehicle = vehicle;
        this.entryTime=System.currentTimeMillis();
    }

    public Integer getParkingSlotIndex() {
        return parkingSlotIndex;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public Object getVehicle() {
        return vehicle;
    }
}
