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
        head = null;
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
        if (getSize()  ==  1){
            head = newNode;
            return;
        }
        Node current = head;

        Node lastOriginInstance = (current.data.getOrigin().equals(newNode.data.getOrigin())) ? current : null;
            // originOrganizer(lastOriginInstance, newNode);

        if (!swapChecker(newNode, head)){

             while(current.next != null && !swapChecker(newNode, current.next)){
                lastOriginInstance = (current.next.data.getOrigin().equals(newNode.data.getOrigin())) ? current.next : lastOriginInstance;
                current = current.next;
             }

        current.next = newNode;
        }
        originOrganizer(lastOriginInstance, newNode);
    }

        /**
         * SWAP AIRPORT REFERENCES 
         * @param lastOriginInstance
         * @param newNode
         */
        private void originOrganizer(Node lastOriginInstance, Node newNode){

            Node current = newNode.next;

            if(lastOriginInstance == null){
                while(current != null){
                    if(current.data.getOrigin().equals(newNode.data.getOrigin())){
                        newNode.nextAirport=current;
                        return;
                    }
                    current = current.next;
                }
            }
            else if(lastOriginInstance.nextAirport == null){
                lastOriginInstance.nextAirport = newNode;
            }
            else if(head.equals(newNode)){
                head.nextAirport=lastOriginInstance;
            }

            else{
                newNode.nextAirport = lastOriginInstance.nextAirport;
                lastOriginInstance.nextAirport = newNode;
            }
        }

        /**
         * 
         * @param current
         * @param newNode
         * @return 
         */
        private boolean swapChecker(Node newNode, Node current){
            FlightCompareByDate c = new FlightCompareByDate();

            if(c.compare(newNode.data, current.data) < 0){
                newNode.next = current;
                head = newNode;
                return true;
            }

            return false;

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