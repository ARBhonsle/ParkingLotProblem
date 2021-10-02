package parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotServiceTest {
    ParkingLotService parkingLotService;
    Object vehicle;

    @BeforeEach
    public void setUp() {
        parkingLotService = new ParkingLotService(1);
        vehicle = new Object();
    }

    @Test
    public void givenVehicle_whenParkedAtEmptyParkingSlot_shouldReturnTrue() throws ParkingLotException {
        parkingLotService.park(vehicle);
        boolean isParked = parkingLotService.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenVehicle_whenParkingAtParkedSlot_shouldReturnException() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotService.registerOwner(owner);
        try {
            parkingLotService.park(vehicle);
            parkingLotService.park(new Object());
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Parking Lot is Full", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_whenUnParked_shouldReturnTrue() throws ParkingLotException {
        parkingLotService.park(vehicle);
        boolean isUnParked = parkingLotService.unPark(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    public void givenVehicle_whenUnParkingFromEmptySlot_shouldReturnException() {
        try {
            boolean isUnParked = parkingLotService.unPark(vehicle);
            Assertions.assertFalse(isUnParked);
        } catch (ParkingLotException e) {
            Assertions.assertEquals("No Vehicle parked", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_whenUnParkingAnotherVehicle_shouldReturnFalse() throws ParkingLotException {
        parkingLotService.park(new Object());
        boolean isUnParked = parkingLotService.unPark(vehicle);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    public void parkingLotWhenChecked_shouldInformOwnerIfFoundFull() throws ParkingLotException {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotService.registerOwner(owner);
        parkingLotService.park(vehicle);
        try {
            parkingLotService.park(vehicle);
            parkingLotService.park(new Object());
        } catch (Exception e) {
            Assertions.assertEquals("Parking Lot is Full", e.getMessage());
        }
        Assertions.assertTrue(owner.isParkingLotCapacityFull());
    }

    @Test
    public void given2ParkingCapacity_shouldPark2Vehicles() throws ParkingLotException {
        Object vehicle2 = new Object();
        parkingLotService.setActualParkingCapacity(2);
        parkingLotService.park(vehicle);
        parkingLotService.park(vehicle2);
        boolean isParked = parkingLotService.isVehicleParked(vehicle);
        boolean isParked1 = parkingLotService.isVehicleParked(vehicle2);
        Assertions.assertTrue(isParked && isParked1);
    }

    @Test
    public void parkingLotWhenFoundFull_shouldIndicateToAirportSecurity() throws ParkingLotException {
        try {
            parkingLotService.park(vehicle);
            if (parkingLotService.isParkingLotFull()) {
                throw new ParkingLotSignal("Parking Lot is FULL");
            }
        } catch (ParkingLotSignal signBoard) {
            Assertions.assertEquals("Parking Lot is FULL", signBoard.getMessage());
        }
    }

    @Test
    public void parkingLotWhenFoundNotFull_shouldIndicateToOwner() throws ParkingLotException {
        try {
            parkingLotService.park(vehicle);
            if (parkingLotService.isParkingLotFull()) {
                throw new ParkingLotSignal("Parking Allowed in Parking Lot");
            }
        } catch (ParkingLotSignal signBoard) {
            Assertions.assertEquals("Parking Allowed in Parking Lot", signBoard.getMessage());
        }
    }
}
