package flightpack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;

/**
 * Author: Colby Wirth 
 * Version: 21 September 2024 
 * Course: COS 285 
 * Class: program2.java
 */
public class program2 {

    /**
     * main method runs flightpack program
     * @param args should be <flights.csv> <ME>
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException{
        
        String flightLogPath = args[0];
        String state = args[1];

        long startTime = System.currentTimeMillis();

        MyArrayList<Flight> flightsForExercise6 = MyDataReader.OriginStateFinder(flightLogPath,state);
        long elapsedTime = System.currentTimeMillis() - startTime;

        MyArrayList<Flight> flightsForExercise7 = MyDataReader.OriginStateFinder(flightLogPath,state);
        System.out.println(elapsedTime + " milliseconds to read the file.\n");

        // Exercise 6.a and 6.b 
        startTime = System.currentTimeMillis();
        flightsForExercise6.sort();
        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println(elapsedTime + "  milliseconds to sort based on DateTime (6.a). \n");

        startTime = System.currentTimeMillis();
        flightsForExercise6.sort();
        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println(elapsedTime + "  milliseconds to sort based on DateTime (6.b).\n");

        // Exercise 7.a and 7.b
        Comparator<Flight> c = new FlightComparator();
        startTime = System.currentTimeMillis();
        flightsForExercise7.sort(c);
        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println(elapsedTime + " milliseconds to sort based on origin (7.a).\n");

        startTime = System.currentTimeMillis();
        flightsForExercise7.sort(c);
        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println(elapsedTime + " milliseconds to sort based on origin (7.b)\n");

        for(int i = 0 ; i < flightsForExercise7.size(); i+=1000)
            System.out.println(flightsForExercise7.get(i).toString());
    }
    
}

