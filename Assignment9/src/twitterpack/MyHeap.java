package twitterpack;

import java.util.Arrays;

/**
 * Author: Colby Wirth 
 * Version: 30 November 2024 
 * Course: COS 285 
 * Class: MyHeap.java
 * This implementation is for a min heap
 */
public class MyHeap<T extends Comparable<T>> {

    private T[] heap;

    /**
     * Constructor
     * Instantiate the inner array
     */
    public MyHeap(){

        @SuppressWarnings("unchecked")
        T[] heap = (T[]) new Object();

    }

    /**
     * add method adds an element to the inner heap, then calls heapify to reorganzie the heap array based on comparator definition
     * @param key
     */
    public void add(T key){
        heap[heap.length] = key;
        heapifyUp();
    }

    /**
     * This method heapifies a heap after adding an element comparator (min heap)
     */
    @SuppressWarnings("unchecked")
    private void heapifyUp(){

        int parentIndex = findParent(heap.length-1);
        int currentIndex = heap.length-1;

            while (currentIndex > 0 && heap[currentIndex].compareTo(heap[parentIndex]) < 0) {{ //min heap property satisfied when while loop terminates

            //swapping operations
            Object temp = heap[parentIndex]; 
            heap[parentIndex] = heap[currentIndex];
            heap[currentIndex]= (T) temp;

            //iterate to next parent
            currentIndex = parentIndex;
            parentIndex = findParent(parentIndex);
        }
    }

    /**
     * helper method to find the parent node of a specified node
     * @param index - the location of the node
     * 
     * @return parentIndex the index of the parent node | or if the given index is the root, the returned index will be -1
     */
    public int findParent(int index){

        if(index == 0) //root was passed to the method.  root is the batman node (it has no parents)
            return -1;

        else // Java's integer division truncates and simplifies the operations
            return (index-1)/2;
    }

    /**
     * isEmpty method 
     * @return True - is Empty - False otherwise
     */
    public boolean isEmpty(){
        return heap.length == 0;
    }

    /**
     * size method gets the number of elements in the hidden heap
     * @return the number of elements in the heap
     */
    public int size(){
        return heap.length;
    }

    /**
     * Peek method gets the top element of the MyHeap
     * @return heap[0] this is the top element of the MyHeap, always
     */
    public T peek(){
        return heap[0];
    }

    /**
     * toString for debugging, print the inner array
     */
    @Override
    public String toString() {
        return Arrays.toString(heap);
    }

    


    public static void main( String[] args){

        MyHeap<Integer> h = new MyHeap<>();

        h.add(1);
        System.out.println(h.toString());
    }


}
