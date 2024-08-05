package org.app.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.app.entity.Ticket;
import org.app.entity.TicketsFromJson;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class TicketsDTO {

    private Set<String> careers = new HashSet<>();

   String datePattern = "dd.MM.yy H:mm";

   Map<String, ZoneId> timeZoneGmt = new HashMap<String, ZoneId>() {{
       // https://www.timeanddate.com/time/map/#!cities=676 - Все данные брат отсюда
       put("VVO", ZoneId.of("Etc/GMT-10"));
       put("TLV", ZoneId.of("Etc/GMT-3"));
       put("UFA", ZoneId.of("Etc/GMT-5"));
       put("LRN", ZoneId.of("Etc/GMT-3"));
   }};

   public List<Ticket> getAllTicketsFromFile(File sourceFile) throws IOException {
      TicketsFromJson listOfJsonTicket = new  ObjectMapper().readValue(sourceFile, TicketsFromJson.class);

      return listOfJsonTicket.getTickets().stream()
              .map(x -> {
                  careers.add(x.getCarrier());
                  return new Ticket(
                      x.getOrigin(),
                      x.getOrigin_name(),
                      x.getDestination(),
                      x.getDestination_name(),
                      ZonedDateTime.of(
                                 LocalDateTime.parse(x.getDeparture_date() + " " + x.getDeparture_time(),
                                         DateTimeFormatter.ofPattern(datePattern)),
                              timeZoneGmt.get(x.getOrigin())),
                      ZonedDateTime.of(
                              LocalDateTime.parse(x.getArrival_date() + " " + x.getArrival_time(),
                                      DateTimeFormatter.ofPattern(datePattern)),
                              timeZoneGmt.get(x.getDestination())),
                      x.getCarrier(),
                      Integer.valueOf(x.getStops()),
                      Double.valueOf(x.getPrice()));}
              )
              .collect(Collectors.toList());
   }
}
