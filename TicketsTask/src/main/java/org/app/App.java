package org.app;

import org.app.dao.SourceFileDAO;
import org.app.dto.TicketsDTO;
import org.app.entity.Ticket;
import org.app.service.ServiceTickets;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class App
{
    static File fileJson;
    static List<Ticket> allTickets;
    static TicketsDTO tdto = new TicketsDTO();
    static ServiceTickets st = new ServiceTickets();

    public static void main( String[] args ) throws IOException {
        fileJson = new SourceFileDAO().getFile();
        allTickets = tdto.getAllTicketsFromFile(fileJson);

        System.out.println("Минимальное время полета между городами Владивосток и Тель-Авив для каждого авиаперевозчика:");

        for(String tmpCareer : tdto.getCareers()) {
          long seconds = st.findMinimumTimeBetweenCity(allTickets,"VVO","TLV", tmpCareer);
            System.out.println("Перевозчик " + tmpCareer + " время " + (seconds) + " минут;");
        }

        System.out.println("Разница между средней и медианной ценой для полета Владивосток - Тель-Авив");
        double duration = st.findDurationBetweenAvgMedianPrice(allTickets, "VVO", "TLV");
        System.out.println("составляет " + duration + " ед.");
    }
}
