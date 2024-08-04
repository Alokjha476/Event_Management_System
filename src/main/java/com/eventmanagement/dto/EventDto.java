package com.eventmanagement.dto;

import lombok.Data;

import java.util.Date;
@Data
public class EventDto {
    private Long id;
    private String eventName;
    private Date eventDate;
    private String description;
}
