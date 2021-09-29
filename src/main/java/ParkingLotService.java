public class ParkingLotService {
    public Object parkedVehicle = null;

    public static void main(String[] args) {
        System.out.println("Welcome to Parking Lot Service System");
    }

    public boolean park(Object vehicle) {
        if (this.parkedVehicle!=null) {
            return false;
        }
        this.parkedVehicle = vehicle;
        return true;
    }

    public boolean unPark(Object vehicle) {
        if(this.parkedVehicle==null){
            return false;
        }
        if(this.parkedVehicle.equals(vehicle)){
            parkedVehicle=null;
            return true;
        }
        return false;
    }
}
