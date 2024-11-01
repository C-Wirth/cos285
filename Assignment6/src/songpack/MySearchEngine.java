
/**
 * Colby Wirth
 * Version 31 October 2024
 * Course: COS 285
 * MySearchEngine.java
 */
package songpack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public final class MySearchEngine {

    public static double CONSTANT_B = 0.75;
    public static double CONSTANT_K_1 = 1.2;

    TreeMap<Song, TreeMap<String, Double>> tf = new TreeMap<>();;//term frequency
    TreeMap<String, Double> idf = new TreeMap<>();; //Inverse Document Frequency
    TreeMap<Song, Double> avglength = new TreeMap<>();; //avearge length of each song
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

            String[] lyrics = song.getLyrics().toLowerCase().split("\\s+");

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
     * Each value is the idf value for each lyric in the entire set of songs
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

            // double idfVal = Math.log((double) songs.size() / (1 + nX));

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
            int songLength = song.getLyrics().split("\\s+").length;
            summation += songLength;
            avglength.put(song, (double) songLength);
        }

        this.avgSongLength = summation/songs.size();
        
        for(Song song : songs){
            avglength.put(song, avglength.get(song)/avgSongLength);
        }
    }

    /**
     * This method calculates the relevance score of a query with respect to a Song
     * @param song the Song to find a relevance score
     * @param query user's query
     * @return the calculated relevance score
     */
    public double calculateRelevance(Song song, String query){

        String[] queryArray = query.split("\\s+");

        double score = 0.0;

        for(String word : queryArray){ //relevance equation

            word = word.toLowerCase();

            double idfVal = idf.getOrDefault(word, 0.0);

            double tfVal = 0;

            if(tf.get(song) != null && tf.get(song).get(word) != null)
                tfVal = tf.get(song).get(word);

            double songLength = song.getLyrics().split("\\s+").length;

           double numerator = idfVal*tfVal*(CONSTANT_K_1+1.0);
           double denominator =tfVal + (CONSTANT_K_1 * ((1.0-CONSTANT_B)+(CONSTANT_B*(songLength/avgSongLength))));

           score+=(numerator/denominator);

        }
        return score;
    } 

    /**
     * serach method takes the query and prints the top-5 songs with the highest relevacne score
     * @param query
     */
    public void search(String query){

        TreeMap<Song, Double> relevanceScores = new TreeMap<>();

        for(Song song : tf.keySet()){ //look at all songs in the MySearchEngine
            relevanceScores.put(song,calculateRelevance(song, query));
        }
  
        List<Map.Entry<Song, Double>> sortedOutput = sortedByValue(relevanceScores, 5);

        printSearchResults(query, sortedOutput);
    }

    /**
     * helper method fpr search method
     * 
     * This method returns a sorted list of top k results as a list of entries <Song,Double>
     * @param treeMap
     * @param topK
     * @return A list of Maps with songs and their relevance value
     */
    private List<Map.Entry<Song, Double>> sortedByValue(TreeMap<Song, Double> treeMap, int topK){
        
        // Convert to list of Map.Entry
        List<Map.Entry<Song, Double>> list = new ArrayList<>(treeMap.entrySet());
        
        // Sort the list based on values
        Collections.sort(list, (Map.Entry<Song, Double> o1, Map.Entry<Song, Double> o2) 
        -> o2.getValue().compareTo(o1.getValue()));
    
        int counter = 0;
        List<Map.Entry<Song, Double>> results = new ArrayList<>();
        
        while(counter<topK){
            results.add(Map.entry(list.get(counter).getKey(),list.get(counter).getValue()));
            counter+=1;
        }
    
    return results;
    }

    /**
     * Helper method to format and print results from a query
     * @param query the user's query
     * @param results the ordered results
     */
    private void printSearchResults(String query, List<Map.Entry<Song, Double>> results){
        System.out.println("\nResults for "+ query);
        int rank = 1;
        for(Map.Entry<Song, Double> entry: results){
        System.out.println(rank+": " + entry.getKey().getTitle() + " by " + entry.getKey().getArtist() + "\t"
        + entry.getValue());
        rank+=1;
        }
        }

}
