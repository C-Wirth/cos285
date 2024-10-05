package flightpack;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * Author: Colby Wirth 
 * Version: 5 October 2024 
 * Course: COS 285 
 * Class: program4.java
 */
public class program4 {

    private static final String FILE_PATH = "src/flights.csv";
    private static final MyDataReader DATA_READER = new MyDataReader();

   /** 
    * Main method for program 4 calls simulations on specified airports
    */
    public static void main(String[] args) throws IOException{


        String airport1 = "PWM";
        simRunner(airport1);

        String airport2 = "BGR";
        simRunner(airport2);

        String airport3 = "AUG";
        simRunner(airport3);

        String airport4 = "LGA";
        simRunner(airport4);

        String airport5 = "JFK";
        simRunner(airport5);

    }

    /**
     * this method handles running similations to find how many counters are needed per airport
     * @param airport the airport name to run the simulation with
     * @throws IOException
     */
    public static void simRunner(String airport) throws IOException{

        ArrayList<Flight> flights = DATA_READER.FlightSorted(airport, FILE_PATH);

        int i = 1;

        while(! new QueueSimulator(flights, i).simulation()){
            i+=1;
        }
        System.out.println("Counters required for " + airport + ": " + i);
    }
}
