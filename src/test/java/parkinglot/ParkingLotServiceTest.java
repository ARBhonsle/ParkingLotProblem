package parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotServiceTest {
    ParkingLotService service;
    Object vehicle;

    @BeforeEach
    public void setUp() {
        service = new ParkingLotService();
        vehicle = new Object();
    }

    @Test
    public void givenVehicle_whenParkedAtEmptyParkingSlot_shouldReturnTrue() throws ParkingLotException {
        boolean isParked = service.park(new Object());
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenVehicle_whenParkingAtFilledParkingSlot_shouldReturnFalse() {
        try {
            service.park(new Object());
            service.park(new Object());
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Vehicle is Already Parked", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_whenUnParked_shouldReturnTrue() throws ParkingLotException {
        service.park(vehicle);
        boolean isUnParked = service.unPark(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    public void givenVehicle_whenUnParkingFromEmptySlot_shouldReturnFalse() {
        try {
            boolean isUnParked = service.unPark(vehicle);
            Assertions.assertFalse(isUnParked);
        } catch (
                ParkingLotException e) {
            Assertions.assertEquals("No Vehicle parked", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_whenUnParkingAnotherVehicle_shouldReturnFalse() throws ParkingLotException {
        service.park(new Object());
        boolean isUnParked = service.unPark(vehicle);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    public void parkingLotWhenChecked_isFoundFull_thenReturnTrue_elseReturnFalse() throws ParkingLotException {
        service.park(vehicle);
        boolean isFull = service.isParkingLotFull();
        Assertions.assertTrue(isFull);
        service.unPark(vehicle);
        boolean isNotFull = service.isParkingLotFull();
        Assertions.assertFalse(isNotFull);
    }

    @Test
    public void parkingLotWhenFoundFull_shouldIndicateToAirportSecurity() throws ParkingLotException {
        try {
            service.park(vehicle);
            if (service.isParkingLotFull()) {
                throw new ParkingLotSignal("Parking Lot Is FULL");
            }
        } catch (ParkingLotSignal signBoard){
            Assertions.assertEquals("Parking Lot Is FULL",signBoard.getMessage());
        }
    }
}
