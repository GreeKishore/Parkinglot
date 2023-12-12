package Repositories;

import Models.Gate;
import Models.ParkingLot;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class ParkingLotReository {
    private Map<Long,ParkingLot> parkingLots = new TreeMap<>();
    public Optional<ParkingLot> findByGate(Gate gate){
        return parkingLots.values().stream().filter(parkingLot -> parkingLot.getGates().contains(gate)).findFirst();
    }
}
