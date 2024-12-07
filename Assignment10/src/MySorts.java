
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

        //insertion sort
        long startTime = System.nanoTime();
        insertionSort(arrayForInsertionSort);
        long endTime = System.nanoTime();
        long insertionSortTime = endTime - startTime;
        System.out.println("Insertion Sort time: " + insertionSortTime + " nanoseconds");
        printArray(arrayForInsertionSort);

        //merge sort
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
                int temp = input[j];
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
        System.arraycopy(array, mid, right, 0, array.length-mid);

        mergeSort(left);
        mergeSort(right);

        merge(array,left,right);
    }

    private static void merge(int[] array, int[] left, int[] right){

        int leftPointer = 0;
        int rightPointer = 0;
        int i = 0;

        while(leftPointer < left.length || rightPointer < right.length){  //**** WE HAVE VALUES IN LEFT AND RIGHT)

            if(leftPointer >= left.length){ //edge case - left is at end, add right value
                array[i] = right[rightPointer];
                rightPointer++;
                i++;
            }
            else if (rightPointer >= right.length){ //edge case - right is at end, add left value
                array[i] = left[leftPointer];
                leftPointer++;
                i++;
            }

            else if (left[leftPointer] < right[rightPointer]){
                array[i] = left[leftPointer];
                leftPointer++;
                i++;
            }
            else{
                array[i] = right[rightPointer];
                rightPointer++;
                i++;
            }
            }
    }

    public static void printArray(int[] input){
        System.out.println(Arrays.toString(input));
    }
}
