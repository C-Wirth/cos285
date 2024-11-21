package songpack;

import java.util.LinkedList;

/**
 * Author: Colby Wirth 
 * Version: 25 October 2024 
 * Course: COS 285 
 * Class: MyHashMap.java
 */
public class MyHashMap<K,V> implements Iterable<K> {

    private static final Double LOAD_FACTOR = 0.75;

    private LinkedList<MyEntry<K,V>>[] table;

    private int size;
    private int tableSize;
    private int threshold; //LOAD_FACTOR * table.length
    private int resizeCount;


    public MyHashMap(){
        tableSize = 32;
        table = new LinkedList[tableSize];
        threshold = (int) table.length * LOAD_FACTOR;
        resizeCount = 0;
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
            resize(); //Build me!!
        

    }

    /**
     * Standard get method for MyHashMap
     * @param key the inputted key
     * @return V the value paired with the key
     */
    public V get(K key){
        
        int index = indexFor(key);
        
        //table does contain key -> find the value
        if(table[index] != null)

            for(MyEntry<K,V> entry : table[index]){ 

                if(entry.key.equals(key))
                    return entry.value; 
            }
        else
            return null;
    }

    protected static class MyEntry<E>{
    
        K key;
        V value;

        MyEntry(K key, V value){
            this.key = key;
            this.value = value;
        }
    
    }

    // implement an iterator

}
