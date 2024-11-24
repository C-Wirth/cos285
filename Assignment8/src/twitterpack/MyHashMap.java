package twitterpack;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Author: Colby Wirth 
 * Version: 25 October 2024 
 * Course: COS 285 
 * Class: MyHashMap.java
 */
public class MyHashMap<K,V> implements Iterable<K> {

    private static final Double LOAD_FACTOR = 0.75;

    private LinkedList<MyEntry<K, V>>[] table;

    private int size;
    private int tableSize;
    private int threshold; //LOAD_FACTOR * table.length
    private int resizeCount;


    @SuppressWarnings("unchecked")
    /**
     * Generic constructor for a hash map
     */
    public MyHashMap(){
        tableSize = 32;
        table = (LinkedList<MyEntry<K, V>>[]) new LinkedList[tableSize];
        threshold = (int) (table.length * LOAD_FACTOR);
        resizeCount = 0;
    }

    /**
     * Inner class encapsulated by MyHashMap
     * Each entry has a key, value pair
     */
    @SuppressWarnings("hiding")
    protected class MyEntry<K,V>{
    
        K key;
        V value;

        /**
         * Generic constructor for key, value pairs
         * @param key
         * @param value
         */
        MyEntry(K key, V value){
            this.key = key;
            this.value = value;
        }
    
    }

    /**
     * Return the index that an entry belongs
     * @param key
     * @return
     */
    private int indexFor(K key){
        int hash = key.hashCode();
        int index = hash &(tableSize-1);
        return index;
    }

    /**
     * Standard put method for a hashmap
     * @param key
     * @param value
     */
    public void put(K key, V value){
        int index = indexFor(key);

        if(table[index] == null)
                table[index] = new LinkedList<>();
        
        for(MyEntry<K,V> entry : table[index]){ 

            if(entry.key.equals(key)){
                entry.value = value;
                return;
            }
        }
        table[index].add(new MyEntry<>(key,value));
        size++;
        if(size > threshold)
            resize();
        

    }

    /**
     * Standard get method for MyHashMap
     * @param key the inputted key
     * @return V the value paired with the key
     */
    public V get(K key){
        
        int index = indexFor(key);
        
        //table does contain key -> find the value
        if(table[index] != null){
            for(MyEntry<K,V> entry : table[index]){ 

                if(entry.key.equals(key))
                    return entry.value; 
            }
        }

        return null;
    }

    /**
     * resizes the underlying 'table' array by a factor of two when threshold is  met or exceeded
     */
    @SuppressWarnings("unchecked")
    private void resize(){

        resizeCount++;
        tableSize *=2;
        
        LinkedList<MyEntry<K, V>>[] oldTable = table; // Save the old table
        table = (LinkedList<MyEntry<K, V>>[]) new LinkedList[tableSize];
        threshold = (int) (LOAD_FACTOR*table.length);
        
        for(LinkedList<MyEntry<K, V>> tableIndex : oldTable){ //iterate through each index in the old table
            if(tableIndex == null)
                continue;
            
            for(MyEntry<K, V> entry : tableIndex){ //iterate through each LL at each index in the old table
                if(entry != null)
                    put(entry.key, entry.value);
            }
        }      
    }

    /**
     * A getOrDefault method implemented to better aid operations
     * @param key
     * @return V if key exists, null if key is not in the map
     */
    public V getOrDefault(K key, V defaultValue){

        if (this.get(key) != null) //map contains key - return the value
            return this.get(key);
            
        else 
            return defaultValue; 
    }

    /**
     * Get a standard ketset
     * @return
     */
    public HashSet<K> keySet(){
        HashSet<K> keys = new HashSet<>();

        for(LinkedList<MyEntry<K, V>> tableIndex : table){ //iterate through each index in the old table
            if(tableIndex == null)
                continue;
            
            for(MyEntry<K, V> entry : tableIndex){ //iterate through each LL at each index in the old table
                if(entry != null)
                    keys.add(entry.key);
            }      
        }
        System.out.println("returning now from keyset() func");
        return keys;

    }
    
    /**
     * Getter method for resizes
     * @return the number of resizes that have occured in the building and maintaining of the MyHashMap object
     */
    public int getResizes(){
        return this.resizeCount;
    }

    /**
     * Iterator inner class 
     */
    @Override
    public Iterator<K> iterator(){
        return new MyIterator();
    }

    /**
     * The iterator will iterate through all of the keys in the table
     */
    public class MyIterator implements Iterator<K> {
        private K lastRet;
        private int nextIndex;
        private Iterator<MyEntry<K, V>> keyIterator; //this iterator iterates on each element within the LinkedList located at each index

        /**
         * Constructor for itereator inner-class
         * Finds the first index of a LinkedList in the table with values 
         */
        public MyIterator(){
            keyIterator = null;
            lastRet = null;
            nextIndex = 0;
            getNextValidIndex();
        }

        /**
         * helper method for constructor and next method to iterate nextIndex to the next index with a valid LinkedList in the table
         */
        private void getNextValidIndex() {

            while (nextIndex < tableSize) {
                if (table[nextIndex] != null) { // check for  a LinkedList with entries
                    keyIterator = table[nextIndex].iterator();
                    if (keyIterator.hasNext()) {
                        return; // Found a valid LinkedList to iterate on
                    }
                }
                nextIndex++; //nothing found at previous index, move to next index in table
            }
            keyIterator = null; // reset keyIterator
        }

        /**
         * Standard has next method 
         * @return false if the iterator is at the end of the table, true elsewise
         * 
         */
        @Override
        public boolean hasNext(){
            return (keyIterator.hasNext() && keyIterator != null)|| nextIndex < tableSize;
        }

        /**
         * find the next key in the table 
         * @return K a key from the LinkedList
         */
        @Override
        public K next(){

            if (!hasNext())
                throw new NoSuchElementException();
        
            if (keyIterator != null && keyIterator.hasNext()) { // First check the current LinkedList
                lastRet = keyIterator.next().key; 
            } 
            else { //The current index has no more elements, find the next valid index
                getNextValidIndex();
                if (keyIterator != null && keyIterator.hasNext()) {
                    lastRet = keyIterator.next().key;
                } 
                else
                    throw new NoSuchElementException(); //iterated through the last empty indices and is at the end of the table
            }
            return lastRet;
        }
    }
}
