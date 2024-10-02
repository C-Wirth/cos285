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

    private ArrayList<T> list;

    int head;

    int tail;

    int size = ;

    
    /**
     * Constructor for generic MyQueue object with an ArrayList<T>
     */
    public MyQueue(int elements){

        list = new ArrayList<T>(elements);

    }


    /**
     * 
     * @param input
     * @return
     */
    public boolean offer(T input){

        list.add(tail, input);

    }

    /**
     * 
     * @return
     */
    public T poll(){

    }

    /**
     * 
     * @return
     */
    public boolean isEmpty(){

    }

    /**
     * 
     * @return
     */
    public int size(){

    }

    /**
     * 
     * @return
     */
    public T peek(){

    }

}
