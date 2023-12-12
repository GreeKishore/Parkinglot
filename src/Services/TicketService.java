package Services;

import Models.*;
import Repositories.GateRepository;
import Repositories.ParkingLotReository;
import Repositories.TicketRepository;
import Repositories.VehicleRepository;
import exceptions.GateNotFoundException;
import exceptions.ParkingLotNotFoundException;
import strategy.SpotAssignmentFactory;
import strategy.SpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private final GateRepository gateRepository;
    private final TicketRepository ticketRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingLotReository parkingLotReository;
    public TicketService(GateRepository gateRepository, TicketRepository ticketRepository, VehicleRepository vehicleRepository, ParkingLotReository parkingLotReository) {
        this.gateRepository = gateRepository;
        this.ticketRepository = ticketRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotReository = parkingLotReository;
    }

    public Ticket issueTicket(String vehicleNumber, VehicleType vehicleType, String vehicleOwnerName, Long gateId){
        /*
        High-level steps of issue ticket feature
            1.Create a Ticket object
            2. Assign a spot
            3. Return the updated ticket
        */

        //new ticket is created
        Ticket ticket = new Ticket();
        //entryTime is updated
        ticket.setEntryTime(new Date());

        //problem - 1 null pointer solved using Optional
        Optional<Gate> gate =  gateRepository.findById(gateId);


        if(gate.isEmpty()){
            throw new GateNotFoundException();
        }
        Gate generateAt = gate.get();
        ticket.setGeneratedAt(generateAt);
        ticket.setGeneratedBy(generateAt.getCurrentOperator());


        Optional<ParkingLot> parkingLotOptional = parkingLotReository.findByGate(generateAt);
        if(parkingLotOptional.isEmpty()){
            throw new ParkingLotNotFoundException();
        }
        ParkingLot parkingLot = parkingLotOptional.get();

        SpotAssignmentStrategy spotAssignmentStrategy = SpotAssignmentFactory.getSpotAssignmentStrategyByType(parkingLot.getSpotAssignmentStrategyType());
        ParkingSpot spot = spotAssignmentStrategy.getSpot(generateAt,vehicleType);
        ticket.setParkingSpot(spot);

        Vehicle savedVehicle;
        Optional<Vehicle> vehicleoptional = vehicleRepository.findByNumber(vehicleNumber);
        if(vehicleoptional.isEmpty()){
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setVehicleType(vehicleType);
            vehicle.setOwnerName(vehicleOwnerName);
            savedVehicle = vehicleRepository.save(vehicle);
        }else{
            savedVehicle = vehicleoptional.get();
        }

        ticket.setVehicle(savedVehicle);
        Ticket savedTicket= ticketRepository.save(ticket);
        ticket.setNumber("Ticket - " +savedTicket.getId());
        return ticket;
    }
}
