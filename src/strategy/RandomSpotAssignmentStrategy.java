package strategy;

import Models.*;
import Repositories.ParkingLotReository;
import exceptions.ParkingLotNotFoundException;

import java.util.Optional;

public class RandomSpotAssignmentStrategy implements SpotAssignmentStrategy {
    private ParkingLotReository parkingLotReository;
    @Override
    public ParkingSpot getSpot(Gate gate, VehicleType vehicleType) {
        Optional<ParkingLot> parkingLotOptional = parkingLotReository.findByGate(gate);
        if(parkingLotOptional.isEmpty()){
            throw new ParkingLotNotFoundException();
        }
        for(ParkingFloor parkingFloor: parkingLotOptional.get().getParkingFloors()){
            for(ParkingSpot parkingSpot:parkingFloor.getParkingSpots()){
                if(parkingSpot.getParkingSpotStatus().equals(ParkingSpotStatus.AVAILABLE)&&parkingSpot.getSupportedVehicleType().contains(vehicleType)){
                    return parkingSpot;
                }
            }
        }
        return null;
    }
}
