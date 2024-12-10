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

            analysis1();
            // analysis2();
            // analysis3();
        
    }

        public static void analysis1(){
            long startTime = System.currentTimeMillis();
            MySorts.quickSort(tweets);
            long endTime = System.currentTimeMillis();
            long totalTime  = endTime-startTime;
            System.out.println(totalTime + " milliseconds for quicksort 1 based on date time");
            
            startTime = System.currentTimeMillis();
            MySorts.quickSort(tweets);
            endTime = System.currentTimeMillis();
            totalTime  = endTime-startTime;
            System.out.println(totalTime + " milliseconds for quicksort 2 based on date time");


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