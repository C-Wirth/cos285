package flightpack;

// import flightpack.Flight;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Author: Colby Wirth 
 * Version: 1 October 2024 
 * Course: COS 285 
 * Class: MyQueue.java 
 * Uses generic types - implemented with an ArrayList
 * @param <E>
 */
public class MyQueue<T, E>{

    private ArrayList<T> queue;

    int head =0;

    int tail=0;

    int size =0;

    
    /**
     * Constructor for generic MyQueue object with an ArrayList<T>
     */
    public MyQueue(){

        queue = new ArrayList<T>();

    }


    /**
     * 
     * @param input
     * @return
     */
    public boolean offer(T input){
        return queue.add(input);
    }

    /**
     * 
     * @return
     */
    public T poll(){
        return queue.removeFirst();
    }

    /**
     * 
     * @return
     */
    public boolean isEmpty(){
        return queue.isEmpty();

    }

    /**
     * 
     * @return
     */
    public int size(){
        return queue.size();

    }

    /**
     * 
     * @return
     */
    public T peek(){
        return queue.getFirst();
    }

}
