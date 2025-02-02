package com.dev.ServiceHelp.strategy;

import com.dev.ServiceHelp.models.dto.TicketUpdateDTO;
import com.dev.ServiceHelp.models.entities.Ticket;
import org.springframework.stereotype.Component;

@Component
public interface StatusTicketStrategy {

    void applyStatus(Ticket ticket, TicketUpdateDTO ticketUpdateDTO);

}