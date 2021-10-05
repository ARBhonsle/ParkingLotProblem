package parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotServiceTest {
    ParkingLotService parkingLotService = null;
    Object vehicle = null;

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
        try {
            parkingLotService.park(vehicle);
            parkingLotService.park(new Object());
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Parking Lot is Full", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_whenUnParked_shouldReturnFalse() throws ParkingLotException {
        parkingLotService.park(vehicle);
        parkingLotService.unPark(vehicle);
        boolean isUnParked = parkingLotService.isVehicleParked(vehicle);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    public void givenVehicle_whenUnParkingFromEmptySlot_shouldReturnException() {
        try {
            parkingLotService.unPark(vehicle);
            boolean isUnParked = parkingLotService.isVehicleParked(vehicle);
            Assertions.assertFalse(isUnParked);
        } catch (ParkingLotException e) {
            Assertions.assertEquals("No Vehicle parked", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_whenUnParkingAnotherVehicle_shouldReturnFalse() throws ParkingLotException {
        parkingLotService.park(new Object());
        parkingLotService.unPark(vehicle);
        boolean isUnParked = parkingLotService.isVehicleParked(vehicle);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    public void parkingLotWhenChecked_shouldInformOwnerIfFoundFull() throws ParkingLotException {
        ParkingLotObserver owner = new ParkingLotOwner();
        parkingLotService.registerObserver(owner);
        parkingLotService.park(vehicle);
        try {
            parkingLotService.park(vehicle);
            parkingLotService.park(new Object());
        } catch (ParkingLotException e) {
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
    public void givenWhenParkingLotIsFull_shouldInformAirportSecurity() {
        ParkingLotObserver airportSecurity = new AirportSecurity();
        parkingLotService.registerObserver(airportSecurity);
        try {
            parkingLotService.park(vehicle);
            parkingLotService.park(new Object());
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Parking Lot is Full", e.getMessage());
        }
        Assertions.assertTrue(airportSecurity.isParkingLotCapacityFull());
    }

    @Test
    public void givenParkingLotIsFull_shouldInformAllObserver() {
        ParkingLotObserver airportSecurity = new AirportSecurity();
        parkingLotService.registerObserver(airportSecurity);
        ParkingLotObserver owner = new ParkingLotOwner();
        parkingLotService.registerObserver(owner);
        try {
            parkingLotService.park(vehicle);
            parkingLotService.park(new Object());
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Parking Lot is Full", e.getMessage());
        }
        Assertions.assertTrue(owner.isParkingLotCapacityFull());
        Assertions.assertTrue(airportSecurity.isParkingLotCapacityFull());
    }

    @Test
    public void givenWhenParkingLotHasAvailableSpaceAfterFull_shouldInformToOwner() throws ParkingLotException {
        ParkingLotObserver owner = new ParkingLotOwner();
        parkingLotService.registerObserver(owner);
        parkingLotService.park(vehicle);
        Object vehicle2 = new Object();
        try {
            parkingLotService.park(vehicle);
            parkingLotService.park(vehicle2);
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Parking Lot is Full", e.getMessage());
        }
        parkingLotService.unPark(vehicle);
        Assertions.assertFalse(owner.isParkingLotCapacityFull());
    }

    @Test
    public void givenVehicle_OwnerCanParkAtSpecifiedSlots() throws ParkingLotException {
        parkingLotService.park(vehicle);
        Assertions.assertTrue(parkingLotService.isVehicleParked(vehicle));
    }

    @Test
    public void givenVehicle_findParkingSlot() throws ParkingLotException {
        parkingLotService.park(vehicle);
        int slot = parkingLotService.findVehicle(vehicle);
        Assertions.assertEquals(0, slot);
    }

    @Test
    public void givenVehicle_findTimeElapsedInParkingLot() throws ParkingLotException {
        parkingLotService.park(vehicle);
        Integer parkingSlotIndex = parkingLotService.findVehicle(vehicle);
        long entryTime = parkingLotService.getEntryTimeOfParkedVehicle(parkingSlotIndex);
        boolean isParked = parkingLotService.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
        long timeElapsed = System.currentTimeMillis() - entryTime;
        Assertions.assertEquals(0, timeElapsed);
    }
}
