package com.archit.eventbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    private String title;
    private String description;
    private String venue;
    private LocalDate eventDate;
    private Integer availableSeat;
    private BigDecimal ticketPrice;

}
