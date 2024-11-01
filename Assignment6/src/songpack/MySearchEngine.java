
/**
 * Colby Wirth
 * Version 31 October 2024
 * Course: COS 285
 * MySearchEngine.java
 */
package songpack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

public class MySearchEngine {

    public static double CONSTANT_B = 0.75;
    public static double CONSTANT_K_1 = 1.2;


    TreeMap<Song, TreeMap<String, Double>> tf;//term frequency
    TreeMap<String, Double> idf; //Inverse Document Frequency
    TreeMap<Song, Double> avglength; //avearge length of each song
    double avgSongLength;

    /**
     * Generic constructor for a MySearchEngine
     * This populates all TreeMaps fields above from an ArrayList of Song objects
     * @param songs
     */
    public MySearchEngine(ArrayList<Song> songs){
        calculateTF(songs); 
        calculateIDF(songs); 
        calculateAvgLength(songs); 
    }

    /**
     * This method populates a MySearchEngine's TreeMap<Song, TreeMap<String, Double>> tf
     * The outer TreeMap has a key set that represents every Song passed from the ArrayList<Song> to the Constructor
     * The values for outer Tree map are the inner Treemap<String,Double>
     * The String is the set of every lyric found in a Song
     * The Double is the tf of each lyric with respect to the key'd Song
     * 
     */
    public void calculateTF(ArrayList<Song>  songs){

        for(Song song : songs){

            TreeMap<String, Double> lyricCount = new TreeMap<>();

            String[] lyrics = song.getLyrics().split("\\s+");

            for(String lyric : lyrics){   
                lyricCount.put(lyric, lyricCount.getOrDefault(lyric, 0.0) + 1.0);
            }
            for(String lyric : lyrics){
                lyricCount.put(lyric, lyricCount.get(lyric)/ lyrics.length);
            }
           tf.put(song, lyricCount);
        }
        }

    /**
     * This method populates a MySearchEngine's TreeMap<String, Double> idf;
     * The keys are a set of Strings which repesent every lyric found from the ArrayList of Songs passed into the Constructor
     * The values is the idf value for each lyric in the entire set of songs
     * @param songs
     */
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

    /**
     * This method populates a MySearchEngine's  avglength TreeMap<Song,Double> object
     * 
     * The keys is a set of all inputted Song objects from the Constructor
     * The value is a double:  (# of lyrics)/(average length of all song)
     * @param songs
     */
    public void calculateAvgLength(ArrayList<Song> songs){

        double summation = 0.0;
        for(Song song : songs){
            summation += song.getLyrics().split("\\s+").length;
        }

        this.avgSongLength = summation/songs.size();
        
        for(Song song : songs){
            avglength.put(song, avglength.get(song)/avgSongLength);
        }
    }

    
    public double calculateRelevance(Song song, String query){

        String[] queryArray = query.split(" ");

        double score = 0.0;

        for(String word : queryArray){ //relevance equation

            double idfVal = idf.get(word);

            double tfVal = tf.get(song).get(word);

            double songLength = song.getLyrics().split("\\s+").length;

           double numerator = idfVal*tfVal*(CONSTANT_K_1+1.0);
           double denominator =tfVal + (CONSTANT_K_1 * ((1.0-CONSTANT_B)+(CONSTANT_B*(songLength/avgSongLength))));

           score+=(numerator/denominator);
        }


        return score;
    } 
}
