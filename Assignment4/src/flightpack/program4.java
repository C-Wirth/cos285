package flightpack;

import java.io.IOException;
import java.util.ArrayList;

public class program4 {

    public static void main(String[] args) throws IOException{

        MyDataReader dr = new MyDataReader();

        String airport = "PWM";

        String filePath = "bin/flights.csv";

        ArrayList<Flight> flights = dr.FlightSorted(airport, filePath);

        QueueSimulator sim = new QueueSimulator(flights, 1);


        // I NEED TO TINKER WITH THE NUMBER COUNT
        // MAYBE SET A LOOP UP TO ADD COUNTERS UNTIL SIMULATION IS True
        // THEN RECORD # OF SIMULATIONS FOR A GIVEN DAY
        
        sim.simulation();

    }

}
