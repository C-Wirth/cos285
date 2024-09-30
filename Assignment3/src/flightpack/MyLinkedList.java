package flightpack;

// import flightpack.Flight;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Author: Colby Wirth 
 * Version: 29 September 2024 
 * Course: COS 285 
 * Class: MyLinkedList.java
 */
public class MyLinkedList{
    int size ;
    Node head;

    /**
     * Default contructor with zero Nodes
     */
    public MyLinkedList(){
        size = 0;
        head = new Node(null);
    }

    /**
     * Node Class **Finish
     */
    public static class Node{
        Node next;
        Node nextAirport;
        Flight data;

        /**
         * Constructor for a Node
         * @param next
         * @param nextFlight
         */
        // public Node(Node next, Flight data, Node nextAirport){
        public Node(Flight data){
            this.next = null;
            this.nextAirport = null;
            this.data = data;
        }
    }

    /**
     * Returns the size of a MyLinkedList object
     * @return size, an int
     */
    public int getSize(){
        return this.size;
    }

    /**
     * Add method used to appends a node to an ordered MyLinkedList object
     * @param newNode a Node with Flight datatype
     */
    public void add(Node newNode){

        size++;
        
        FlightCompareByDate c = new FlightCompareByDate();
        Node current = head;

        while(current.next!=null && !newFlightOrderer(newNode, current, c)){
            current = current.next;
        }

        newNode.next = current.next;
        current.next = newNode;
        
        if (size > 1) 
            flightOriginFixer(newNode);
    }

        //add the current flight to the LL in order
        private boolean newFlightOrderer(Node newNode, Node current, FlightCompareByDate c){

            if(current.next == null){
                current.next = newNode;
                return true;
            }

        return c.compare(newNode.data, current.next.data) < 0; //newNode is < current.next
        }

        /**
         * This method inserts the newNode into its respective getAirportName chain
         * @param newNode the new Node to be added
         */
        private void flightOriginFixer(Node newNode){

            Node current = head.next;
            Node lastOrigin = null;


            while(current != null && current != newNode){
                if(current.data.getOrigin().getAirportName().equals(newNode.data.getOrigin().getAirportName())){
                    lastOrigin = current;
                }
                current = current.next;
            }
            if(lastOrigin != null){
                newNode.nextAirport = lastOrigin.nextAirport;
                lastOrigin.nextAirport = newNode;
                return;
            }
            

            while(current != null && !(current.data.getOrigin().getAirportName().equals(newNode.data.getOrigin().getAirportName()))){
                current = current.next;
            }
            if(current != null && current != newNode){
                newNode.nextAirport = current;
            }                
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

    /**
     * 
     * @param airport
     * @param start
     * @param end
     * @return
     */
   public Iterator<Flight> iterator(String airport, LocalDateTime start, LocalDateTime end){
        return new MyItr(airport, start, end);
   }

   /**
    * 
    */
   private class MyItr implements Iterator<Flight>{        
        
        Node current = null;
        LocalDateTime end;


        /**
         * Constructor -- finish
         * 
         * @param airport
         * @param start
         * @param end
         */
        public MyItr(String airport, LocalDateTime start, LocalDateTime end) {
            
            this.end = end;
            current.next=head;
        
            while(this.hasNext() && !current.data.getOrigin().getAirportName().equals(airport)){
                current = current.next;
            }
        }


    /**
     * Checks for the next flight with the same flightOrigin
     */
    @Override
    public boolean hasNext() {

        return current != null;

        while( this.hasNext() && current.data.getFlightDate().compareTo(start)<0){
            current = current.nextAirport;
        }


    }

    // Code for NoSuchElementException recevied from:
    //https://stackoverflow.com/questions/7630014/difficulty-throwing-nosuchelementexception-in-java
    @Override
    public Flight next() {
        if (hasNext()){
            lastReturned = next;
            next=next.next;
            return next.data;
        }
        else{
            throw new NoSuchElementException();
        }

    }
   }
    
}