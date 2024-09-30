package flightpack;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class program3 {

    public static void main(String[] args){


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH");
        LocalDateTime min = LocalDateTime.parse("1994-01-01:01", formatter);
        LocalDateTime max = LocalDateTime.parse("2009-12-31:23", formatter);
        String inputState = "ME";
        String path = "flights.csv";

        // MyArrayList<Flight> flightsForExercise6 = MyDataReader.OriginStateFinder(flightLogPath,state);
        MyLinkedList flights = 

    }
    
}
