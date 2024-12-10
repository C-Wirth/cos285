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

            analysis1();
            analysis2();
            analysis3();
        
    }

        public static void analysis1() throws IOException{

            tweets = MyDataReader.readDataToArray(pathToTestSet,80000);
            MySorts.ComparatorByTime c = new MySorts.ComparatorByTime();

            long startTime = System.currentTimeMillis();
            MySorts.quickSort(tweets,c);
            long endTime = System.currentTimeMillis();
            long totalTime  = endTime-startTime;
            System.out.println(totalTime + " milliseconds for quicksort 1 based on date time");
            
            startTime = System.currentTimeMillis();
            MySorts.quickSort(tweets,c);
            endTime = System.currentTimeMillis();
            totalTime  = endTime-startTime;
            System.out.println(totalTime + " milliseconds for quicksort 2 based on date time\n");
        }

        public static void analysis2(){

            MySorts.CompareByID c = new MySorts.CompareByID();

            long startTime = System.currentTimeMillis();
            MySorts.quickSort(tweets, c);
            long endTime = System.currentTimeMillis();
            long totalTime  = endTime-startTime;

            System.out.println(totalTime + " milliseconds for quicksort based on tweet ID ");
            System.out.println("Top 10 Results:");

            for(int i = 0 ; i < 10 ; i ++){
                System.out.println("" + (i+1) +": " + tweets[i] + " --- date: " + tweets[i].getPostDateTime());
            }
            System.out.println("");
        }
        public static void analysis3() throws IOException{

                final int ID_LENGTH = 7;

                tweets = MyDataReader.readDataToArray(pathToTestSet,80000);
                MySorts.CompareByID c = new MySorts.CompareByID();

                long startTime = System.currentTimeMillis();
                MySorts.quickSort(tweets, c);
                long endTime = System.currentTimeMillis();
                long totalTime  = endTime-startTime;
                System.out.println(totalTime + " milliseconds for quicksort based on tweet ID");

                startTime = System.currentTimeMillis();
                MySorts.radixSort(tweets, ID_LENGTH);
                endTime = System.currentTimeMillis();
                totalTime  = endTime-startTime;
                System.out.println(totalTime + " milliseconds for radixsort based on tweet ID");
                
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