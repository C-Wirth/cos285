package flightpack;

public class MyArrayList<T extends Comparable<T>>{

    private T[] elements;

    private int size = 0;
    
    private static final int DEFAULT_CAPACITY = 10;
    
}
