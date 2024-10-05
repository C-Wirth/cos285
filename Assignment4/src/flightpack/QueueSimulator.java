package flightpack;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class QueueSimulator {

    private ArrayList<Flight> aList; // save input data -- sorted vy Flight
    private MyQueue<Flight> queue; //used for similation
    private int processTime; // total process time in each day based on counters

    /**
     * Constructor for a QueueSimulator object
     * @param aList the ArrayList<Flight> from a specified airport
     * @param numberCounter number of counters required
     */
    public QueueSimulator(ArrayList<Flight> aList, int numberCounter){
        
        this.aList = aList; 
        processTime = numberCounter*360000; //Confusing???
        queue = new MyQueue<>();
    }

    /**
     * This runs the main logic of the simulation - processes each day
     * @return true if the simulation is completed with the number of counters 
     * @return false if more counters are needed
     */
    public boolean simulation(){

        LocalDateTime ldtStart = aList.get(0).getFlightDate();
        ldtStart = ldtStart.minusHours(1);
        LocalDateTime ldtEnd = aList.get(aList.size()-1).getFlightDate();


        for (LocalDateTime ldt = ldtStart; !ldt.isAfter(ldtEnd); ldt = ldt.plusHours(1)) {

            //addPassengers
            addPassengers(ldt); //*****implement me****

            //process queue
            boolean success = processQueue(); //****implement me****???
            if(success==false){
                return false;
            }
        }
        return true;
    }

    /**
     * helper method for simulation processes the timing for a queue of passengers
     * 
     * @return true if all passengers are processed in the alotted time
     * @return false if not all passengers are processed in the time
     */
    private boolean processQueue(){

        int currentTime = processTime;

        while(!queue.isEmpty()&& currentTime >= 0){
            
            Flight temp = queue.poll();
            int passengersProcessTime = temp.getPassengers()*6;
            currentTime -= passengersProcessTime;
        }
        return queue.isEmpty();

    }

    /**
     * helper method for simulation Fix me?
     * @param ldt
     */
    private void addPassengers(LocalDateTime ldt){

       while (aList.get(0).getFlightDate().equals(ldt.plusHours(1))){

            Flight temp= aList.get(0);
            queue.offer(temp);
            aList.remove(0); //we need to make a copy of the aList before using it **

       }

    }



}
