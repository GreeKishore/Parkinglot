import Controllers.TicketController;
import Models.Ticket;
import Repositories.GateRepository;
import Repositories.ParkingLotReository;
import Repositories.TicketRepository;
import Repositories.VehicleRepository;
import Services.TicketService;

public class ParkingLotApplication {
    public static void main(String[] args) {
        //Ticketcontroller calls ticket service ->multiple repositories
        TicketRepository ticketRepository = new TicketRepository();
        ParkingLotReository parkingLotReository = new ParkingLotReository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        GateRepository gateRepository = new GateRepository();
        TicketService ticketService = new TicketService(gateRepository,ticketRepository,vehicleRepository,parkingLotReository);
        TicketController ticketController = new TicketController(ticketService);
        System.out.println("Server has been started at port  - 8080 //This is where springboot application starts");

    }
}
