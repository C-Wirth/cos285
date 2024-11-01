package songpack;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Colby Wirth
 * Version 30 October 2024
 * Dr. Berhrooz Mansouri
 * program 6
 */
public class program6 {

    public static final String QUERY0 = "back in black";
    public static final String QUERY1 = "we are the champions";
    public static final String QUERY2 = "i will always love you";
    public static final String QUERY3 = "walking on sunshine";
    public static final String QUERY4 = "dancing in the rain";
    public static final String QUERY5 = "put your hands in the jupitersky";
        
    public static String filePath="src/song_lyrics.tsv";
    public static final String GENRE = "rock";

    public static MySearchEngine engine ;
    
        public static void main(String[] args) throws IOException{
    
            if(args.length != 0){
                filePath = args[0];
            }
    
            ArrayList<Song> songs = MyDataReader.readFileToArrayList(filePath, GENRE);
    
            //building the index
            long startTime = System.currentTimeMillis();
            engine = new MySearchEngine(songs);
            long endtime = System.currentTimeMillis() - startTime;
            System.out.println("\n" + endtime + " milliseconds to build the index");
    
            //execute queries
            queryMaker(QUERY0);
            queryMaker(QUERY1);
            queryMaker(QUERY2);
            queryMaker(QUERY3);
            queryMaker(QUERY4);
            queryMaker(QUERY5);
        }

    private static void queryMaker(String query){
        long startTime = System.currentTimeMillis();
        engine.search(query);
        long endtime = System.currentTimeMillis() - startTime;
        System.out.println(endtime + " milliseconds to search for " + query);
    }

    

}
