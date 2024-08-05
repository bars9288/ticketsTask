package org.app.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private String origin;
    private String origin_name;
    private String destination;
    private String destination_name;
    private ZonedDateTime departure;
    private ZonedDateTime arrival;
    private String carrier;
    private Integer stops;
    private Double price;
}

