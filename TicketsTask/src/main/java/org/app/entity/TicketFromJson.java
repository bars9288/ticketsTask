package org.app.entity;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TicketFromJson {
    private String origin;
    private String origin_name;
    private String destination;
    private String destination_name;
    private String departure_date;
    private String departure_time;
    private String arrival_date;
    private String arrival_time;
    private String carrier;
    private String stops;
    private String price;
}

