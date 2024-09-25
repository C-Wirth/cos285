package flightpack;

import java.util.Comparator;
import java.util.Iterator;

public class MyLinkedList<Flight extends Comparable> implements Iterable<Flight>{
    int size ;
    Node head;
    Node current;


    /**
     * Default contructor **FINISH
     */
    public MyLinkedList(){
    }

    /**
     * Node Class **Finish
     */
    public class Node{
        Node next;
        Flight flight;
        /**
         * Constructor for a Node ** Finish
         * @param next
         * @param nextFlight
         */
        public Node(Node next, Flight flight){
            this.next = next;
            this.flight = flight;
            add(this);
            size++;
        }
    }

    /**
     * Add method used when a LL is created and when adding Nodes after creation
     * @param newNode
     */
    public void add(Node newNode){

        if (size == 0){
            head = newNode;
            return;
        }

        current = head;

        FlightCompareByDate<Flight> c = new FlightCompareByDate();

        while(current.next != null){

            if(c.compare(newNode.flight, current.next.flight) < 0){ //if newNode is 'smaller' than current.next
                
                newNode.next = current.next;
                current.next = newNode;
                return;
            }
            current = current.next;
        }
        current.next = newNode;
        newNode.next = null;
    }

    public class FlightCompareByDate implements Comparator<Flight>{
     
        /**
        * compare method compares two Flight object's DateTime
        * 
        * @Override Comparator's compare method
        * @return utilize java's String's compareTo method to compare a Flight's origin AirportName field
        */
        @Override
        public int compare(Flight f1, Flight f2) {
            return f1.getFlightDate().compareTo(f2.getFlightDate());
        
        }
    }



    @Override
    public Iterator iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }
    
}
