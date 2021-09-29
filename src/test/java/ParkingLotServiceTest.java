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
    public void givenVehicle_whenParkedAtFilledParkingSlot_shouldReturnFalse() {
        service.park(new Object());
        boolean isParked = service.park(new Object());
        Assertions.assertFalse(isParked);
    }
}
