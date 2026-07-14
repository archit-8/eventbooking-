package com.archit.eventbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {
    private Long id;
    private String title;
    private String description;
    private  String venue;
    private LocalDate eventDate;
    private Integer availableSeats;
    private BigDecimal ticketPrice;
}
