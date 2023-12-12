package Controllers;

import Models.Ticket;
import Services.TicketService;
import dtos.IssueTicketRequestDTO;
import dtos.IssueTicketResponseDTO;
import dtos.ResponseStatus;

public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO request){
        //1.validates the request
        //2. calls the relevant service
        //3. It receives input from those
        //4. It creates output for client
        IssueTicketResponseDTO response = new IssueTicketResponseDTO();
        try{
            Ticket ticket = ticketService.issueTicket(request.getVehicleNumber(),request.getVehicleType(),request.getOwnerName(),request.getGateId());
            response.setStatus(ResponseStatus.SUCCESS);
            response.setTicket(ticket);
        }catch(Exception e){
            response.setStatus(ResponseStatus.FAILURE);
            response.setFailureReason("Failure" +e.getMessage());
        }
        return response;
    }
}
