package flightpack;

import java.io.FileNotFoundException;
import java.io.IOException;

public class program2 {

    // Start Command : java flightpack.program2 flights.csv ME
    public static void main(String[] args) throws FileNotFoundException, IOException{
        
        String flightLogPath = args[0];
        String state = args[1];

        MyArrayList<Flight> f = MyDataReader.OriginStateFinder(flightLogPath,state);

        for(int i = 0 ; i < f.size() ; i++){
            System.out.println(i + " :" + f.get(i));
        }

        System.out.println(f.size());
    }
    
}

