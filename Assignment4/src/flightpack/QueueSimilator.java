package flightpack;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class QueueSimilator {

    private ArrayList<Flight> aList; // save input data -- sorted on Flight
    private MyQueue<Flight> queue; //used for similation
    private int processTime; // total process time in each day based on counters

    public QueueSimilator(ArrayList<Flight aList, int numberCounter){
        
        this.aList = aList; //change later ??
        processTime = numberCounter*360000;
        queue = new MyQueue<Flight>();
    }

    public boolean simulation(){

        LocalDateTime ldtStart = aList.get(0).getFlightDate();
        ldtStart - ldtStart.minusHours(1);
        LocalDateTime ldtEnd = aList.get(aList.size()-1).getFlightDate();


        for(LocalDateTiime = ldtStart; !ldtEnd.isAfter((ldtEnd) ; ldt = ldt.plusHourts(1))){

            //addPassengers
            addPassengers(ldt); //*****implement me****

            //process queue

            boolean success = processQueue(); //****implement me****
            if(success==false){
                return false;
            }
        }
        return true;
    }

    private boolean processQueue(){

        int currentTime = processTime;

        while(!queue.isEmpty()&& currentTime >= 0){
            
            Flight temp = queue.poll();
            int passengersProcessTime = temp.getPassenger()*6;
            currentTime -= passengersProcessTime;
        }
        if(queue.isEmpty()){
            return true;
        }

        return false;

    }

    /**
     * 
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
