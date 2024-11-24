package twitterpack;

import java.io.IOException;

/**
 * Author: Colby Wirth 
 * Version: 23 November 2024 
 * Course: COS 285 
 * Class: program8.java
 */
public class program8 {

    static String pathToTrainSet ="";
    static String pathToTestSet="";

                
    /**
    * Main method runs exercises for Assigment 8
    * @throws IOException 
    */
    public static void main(String[] args) throws IOException{

        checkPath(args);

        MyHashMap<Tweet, Boolean> trainData = mapBuilder(pathToTrainSet, "train hash map");
        MyHashMap<Tweet, Boolean> testData = mapBuilder(pathToTestSet, "test hash map");

        MySentimentAnalysisModel model = new MySentimentAnalysisModel(trainData);

        System.out.println("Ratio of correct predictions: " + model.testModel(testData));
    }
    
        /**
         * check the path - use default path values if args is not inputted
         * @param args from main method
         */
        private static void checkPath(String[] args){
    
            //default for testing - no inputted parameters
            pathToTrainSet = "src/data/tweets_train.tsv";
            pathToTestSet = "src/data/tweets_test.tsv";
    
            //use inputted values instead of defaults
            if(args.length != 0){
                pathToTrainSet = args[0];
                pathToTestSet = args[1];
            }
        }
        
        /**
         * method to handle the outputs specified in the assignment
         * @param path the path to a dataset
         * @param mapName the name of the map
         * @return the built MyHashMap object
         * @throws IOException
         */
        public static MyHashMap<Tweet, Boolean> mapBuilder(String path, String mapName) throws IOException{

            long startTime = System.currentTimeMillis();
            MyHashMap<Tweet, Boolean> map = MyDataReader.readDataToHashMap(path);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            System.out.println(elapsedTime + " milliseconds to build the " + mapName);
            System.out.println(map.getResizes() + " resizng to build the " + mapName + "\n");

            return map;
        }
}