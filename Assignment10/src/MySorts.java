
import java.util.Arrays;
import java.util.Random;


public class MySorts {

    public static void main(String[] args){
        int SIZE = 10;

        Random rand = new Random();
        int[] randomArray = new int[SIZE];
        for(int i = 0 ; i < SIZE ; i ++)
            randomArray[i] = rand.nextInt(1000);

        int[] arrayForInsertionSort = randomArray.clone();
        int[] arrayForMergeSort = randomArray.clone();

        long startTime = System.nanoTime();
        insertionSort(arrayForInsertionSort);
        long endTime = System.nanoTime();
        long insertionSortTime = endTime - startTime;
        System.out.println("Insertion Sort time: " + insertionSortTime + " nanoseconds");
        printArray(arrayForInsertionSort);

        startTime = System.nanoTime();
        mergeSort(arrayForMergeSort);
        endTime = System.nanoTime();
        long mergeSortTime = endTime - startTime;
        System.out.println("Merge Sort Time: " + mergeSortTime + " nanoseconds");
        printArray(arrayForMergeSort);
    }

    public static void insertionSort(int[] input){
        for(int i =1; i < input.length; i++){
            for(int j=i; j>0 && (input[j-1]>input[j]); j--){
                int temp = input[i];
                input[j]=input[j-1];
                input[j-1]=temp;
            }
        }   
    }

    public static void mergeSort(int[] array){
        if(array.length <= 1)
            return;

        int mid = array.length /2;
        int[] left = new int[mid];
        int[] right = new int[array.length-mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, array.length-mid, mid);

        mergeSort(left);
        mergeSort(right);

        merge(array,left,right);
    }

    public static void printArray(int[] input){
        System.out.println(Arrays.toString(input));
    }
}
