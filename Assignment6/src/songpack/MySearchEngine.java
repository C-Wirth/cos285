
/**
 * Colby Wirth
 * Version 30 October 2024
 * Course: COS 285
 * MySearchEngine.java
 */
package songpack;

import java.util.ArrayList;
import java.util.TreeMap;

public class MySearchEngine {

    TreeMap<Song, TreeMap<String, Double>> tf;

    ArrayList<Song> songs;


    public MySearchEngine(ArrayList<Song> songs){
        this.songs= songs;
    }

    /**
     * This method adds all values from a SearchEngine's 'Songs' attribute to a TreeMap<Song,TreeMap<String,Double>>
     * The nested TreeMap holds a key-value pair <String,Double> where each String is a unique lyric from the Song's 'Lyrics' field, 
     * and the Double is the the count of the lyric
     * 
     */
    public void calculateTF(){

        for(Song song : songs){

            TreeMap<String, Double> lyricCount = new TreeMap<>();

            String[] lyrics = song.getLyrics().split("\\\s"); // splitting function got from stackoverflow on 10-30-2024 
                                                                    // https://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
            for(String lyric : lyrics){   
                lyricCount.put(lyric, lyricCount.getOrDefault(lyric, 0.0) + 1.0);
            }
           tf.put(song, lyricCount);
        }
    }

}
