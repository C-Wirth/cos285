package songpack;

import java.io.IOException;
import static java.lang.System.currentTimeMillis;
import java.util.ArrayList;

/**
 * Author: Colby Wirth 
 * Version: 25 October 2024 
 * Course: COS 285 
 * Class: main.java
 */
public class program5 {

    /**
    * Main method runs tests for Assigment 5
     * @throws IOException 
    * 
    */
    public static void main(String[] args) throws IOException{

        final String FILEPATH = args[0];
        final String TAG = args[1];

        final int MIN_SONGS = 1000;
        final int MAX_SONGS = 10000;

        //Exercise 1
        long startTime = System.currentTimeMillis();
        BinarySearchTree bst = MyDataReader.readFileToBST(FILEPATH, TAG);
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println(elapsedTime + " milliseconds to read the rock songs into a binary search tree\n");

        //Exercise 2
        startTime = System.currentTimeMillis();
        ArrayList<Song> sortedSongs = bst.toSortedArrayList();
        StringBuilder topSongs = new StringBuilder("[");
        elapsedTime = currentTimeMillis() - startTime;
        
        for(int i = sortedSongs.size()-1, j = 0 ; j < 10 ; i--, j++)
            topSongs.append(sortedSongs.get(i).getTitle()).append(",");

        topSongs.deleteCharAt(topSongs.length() - 1).append("]");

        System.out.println("Top-10 titles of songs with the highest number of views:\n" + topSongs + "\n");
        System.out.println(elapsedTime + " milliseconds to find top-10 popular songs\n");


        //Exercise 3
        startTime = System.currentTimeMillis();
        BinarySearchTree bstClone = bst.clone();
        elapsedTime = currentTimeMillis() - startTime;
        System.out.println(elapsedTime + " milliseconds to clone the tree\n");

        startTime = System.currentTimeMillis();
        bstClone.fliterByView(MIN_SONGS, MAX_SONGS);
        boolean results = (bstClone.isValidBST(bstClone.root));
        elapsedTime = currentTimeMillis() - startTime;

        System.out.println("validation result of cloning and filtering: " + results + "\n");
        System.out.println(elapsedTime + " milliseconds to filter the tree and validate the binary search tree\n");

        startTime = System.currentTimeMillis();
        ArrayList<Song> topSongsInRange = bstClone.toSortedArrayList();
        Song topSong = topSongsInRange.getLast();
        StringBuilder artists = new StringBuilder(topSong.getArtist() + ",");

        for(int i = topSongsInRange.size() -2 ; i >= 0 ; i--){

            if(topSongsInRange.get(i).getViews() == topSong.getViews())
                artists.append(topSongsInRange.get(i).getArtist()).append(",");
            else {
                artists.deleteCharAt(artists.length() - 1); 
                break;
            }
        }

        elapsedTime = currentTimeMillis() - startTime;
        
        System.out.println("\nThe Artist(s) with the highest view(s) in the given range: " + artists + "\n");
        System.out.println(elapsedTime + " milliseconds to find popular artists\n");
    }
    
}
