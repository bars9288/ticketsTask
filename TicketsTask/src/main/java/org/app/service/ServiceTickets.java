package org.app.service;


import org.app.entity.Ticket;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


public class ServiceTickets {

    public long findMinimumTimeBetweenCity(List<Ticket> tickets, String origin, String destination, String carrier) {

        AtomicLong MinValue = new AtomicLong(Long.MAX_VALUE);

        if (tickets == null || tickets.size() == 0) {return -1L;}

       List<Ticket> sortedTickets = tickets.stream()
               .filter(x ->
                        (x.getCarrier().equals(carrier)
                        && x.getOrigin().equals(origin)
                        && x.getDestination().equals(destination)))
                .map(x -> {
                   long value = (Duration.between(x.getDeparture(), x.getArrival()).toMinutes());
                   MinValue.set(Math.min(MinValue.get(), value));
                   return x;
                })
                .collect(Collectors.toList());

        return MinValue.get();
    }

    public double findDurationBetweenAvgMedianPrice(List<Ticket> tickets,String origin, String destination) {
        double sumPrice = 0.0;
        List<Double> allPrices = new ArrayList<>();

       List<Ticket> filtredList = tickets.stream()
                .filter(x ->
                        (x.getOrigin().equals(origin) && x.getDestination().equals(destination)))
                .collect(Collectors.toList());

       for (Ticket tmp : filtredList) {
           sumPrice += tmp.getPrice();
           allPrices.add(tmp.getPrice());
       }

       double avgPrice = sumPrice / allPrices.size();
       double medianPrice = findMedianValue(allPrices);

       return Math.abs(avgPrice - medianPrice);

    }

    public double findMedianValue(List<Double> numbers) {

        Collections.sort(numbers);

        int size = numbers.size();
        if (size % 2 == 0) {
            return (numbers.get(size / 2 - 1) + numbers.get(size / 2)) / 2.0;
        } else {
            return numbers.get(size / 2);
        }
    }


}
