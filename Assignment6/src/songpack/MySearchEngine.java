
/**
 * Colby Wirth
 * Version 30 October 2024
 * Course: COS 285
 * MySearchEngine.java
 */
package songpack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

public class MySearchEngine {

    TreeMap<Song, TreeMap<String, Double>> tf;
    TreeMap<String, Double> idf;
    TreeMap<Song, Double> avglength;

    public MySearchEngine(ArrayList<Song> songs){
        calculateTF(songs);
        calculateIDF(songs);
        calculateAvgLength(songs);
    }

    /**
     * This method adds all values from a SearchEngine's 'Songs' attribute to a TreeMap<Song,TreeMap<String,Double>>
     * The nested TreeMap holds a key-value pair <String,Double> where each String is a unique lyric from the Song's 'Lyrics' field, 
     * and the Double is the the count of the lyric
     * 
     */
    public void calculateTF(ArrayList<Song>  songs){

        for(Song song : songs){

            TreeMap<String, Double> lyricCount = new TreeMap<>();

            String[] lyrics = song.getLyrics().split("\\s+");

            for(String lyric : lyrics){   
                lyricCount.put(lyric, lyricCount.getOrDefault(lyric, 0.0) + 1.0);
            }
           tf.put(song, lyricCount);
        }

        int numWords = lyrics.size();
        for(lyric : lyrics){ //i need the ratio - update the tfTree value

        }
    }

    // implement IOT 
    public void calculateIDF(ArrayList<Song> songs){

        for(Song song : songs){

            String[] words = song.getLyrics().split(" ");

            TreeSet<String> uniqueWords = new TreeSet<>(Arrays.asList(words));

            for(String word : uniqueWords){

                idf.put(word, idf.getOrDefault(word, 0.0)+1);
            }
        }

        for(String lyric : idf.keySet()){

            double nX = idf.get(lyric);

            double idfVal = (songs.size()- (nX + 0.5)/(nX + 0.5)) + 1;
            
            idfVal = Math.log(idfVal);

            idf.put(lyric, idfVal);

        }
    }

    public void calculateAvgLength(ArrayList<Song> songs){

        double summation = 0.0;
        for(Song song : songs){

            summation += song.getLyrics().split("\\s+").length;
        }

        double avg = summation/songs.size();
        for(Song song : songs){
            avglength.put(song, avglength.get(song)/avg);

        }
    }
}
