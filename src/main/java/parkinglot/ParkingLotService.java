package parkinglot;

public class ParkingLotService {
    public Object parkedVehicle = null;

    public static void main(String[] args) {
        System.out.println("Welcome to Parking Lot Service System");
    }

    public boolean park(Object vehicle) throws ParkingLotException {
        if (this.parkedVehicle!=null) {
            throw new ParkingLotException("Vehicle is Already Parked");
        }
        this.parkedVehicle = vehicle;
        return true;
    }

    public boolean unPark(Object vehicle) throws ParkingLotException {
        if(this.parkedVehicle==null){
            throw new ParkingLotException("No Vehicle parked") ;
        }
        if(this.parkedVehicle.equals(vehicle)){
            parkedVehicle=null;
            return true;
        }
        return false;
    }

    public boolean isParkingLotFull(){
        return parkedVehicle != null;
    }
}
