import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotServiceTest {
    ParkingLotService service;

    @BeforeEach
    public void setUp(){
        service = new ParkingLotService();
    }
    @Test
    public void givenVehicle_whenParkedAtEmptyParkingSlot_shouldReturnTrue() {
        boolean isParked = service.park(new Object());
        Assertions.assertTrue(isParked);
    }
    @Test
    public void givenVehicle_whenParkingAtFilledParkingSlot_shouldReturnFalse() {
        service.park(new Object());
        boolean isParked = service.park(new Object());
        Assertions.assertFalse(isParked);
    }
    @Test
    public void givenVehicle_whenUnParked_shouldReturnTrue() {
        Object vehicle = new Object();
        service.park(vehicle);
        boolean isUnParked = service.unPark(vehicle);
        Assertions.assertTrue(isUnParked);
    }
    @Test
    public void givenVehicle_whenUnParkingFromEmptySlot_shouldReturnFalse() {
        Object vehicle = new Object();
        boolean isUnParked = service.unPark(vehicle);
        Assertions.assertFalse(isUnParked);
    }
    @Test
    public void givenVehicle_whenUnParkingAnotherVehicle_shouldReturnFalse(){
        Object vehicle = new Object();
        service.park(new Object());
        boolean isUnParked = service.unPark(vehicle);
        Assertions.assertFalse(isUnParked);
    }
}
