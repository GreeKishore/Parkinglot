package strategy;

import Models.Gate;
import Models.ParkingSpot;
import Models.VehicleType;

import java.util.Optional;

public interface SpotAssignmentStrategy {
    public ParkingSpot getSpot(Gate gate, VehicleType vehicleType);
}
