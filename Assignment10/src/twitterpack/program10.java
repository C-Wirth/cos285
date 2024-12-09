package twitterpack;

import java.io.IOException;

/**
 * Author: Colby Wirth 
 * Version: 8 December 2024 
 * Course: COS 285 
 * Class: program10.java
 */
public class program10 {

    //default for testing - no inputted paths with args
    static String pathToTestSet = "src/data/tweets_test.tsv";
    
    static Tweet[] tweets;
                    
        /**
        * Main method runs exercises for Assigment 10
        * @throws IOException 
        */
        public static void main(String[] args) throws IOException{
    
            checkPath(args);
            tweets = MyDataReader.readDataToArray(pathToTestSet,80000);

            MySorts.mergeSort(tweets);

            for(int i = 0 ; i < 10 ; i++)
                System.out.println(tweets[i].getPostDateTime());
        
    }
    
        /**
         * check the path
         * @param args from main method
         */
        private static void checkPath(String[] args){
    
            if(args.length == 1){
                pathToTestSet = args[0];
            }
        }
}