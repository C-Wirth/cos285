
import java.time.LocalDateTime;


/**
 * Author: Colby Wirth
 * Version: 6 September 2024
 * Course: COS 280
 * Class: Airport.java
 */
public class Flight{
    

    Airport origin;
   
    Airport destination;
   
    String passengers;

    LocalDateTime flightDate;

    int seats;

    int distance;
   
   
/**
 * 
 * @param origin The Airport object which the flight departed
 * @param destination The Airport Object which the flight lands at
 * @param passengers Number of passengers on a flight
 * @param flightDate Date and time of a flight (yyyy-mm-dd:hh)
 * @param seats Number of seats on a flight
 * @param distance Distance of the flight
 */
    public Flight(Airport origin, Airport destination, LocalDateTime flightDate, int passengers, int seats, int distance){
        origin = origin;
        destination = destination;
        passengers = passengers;
        flightDate = flightDate;
        seats = seats;
        distance = distance;   
    }
   
   }