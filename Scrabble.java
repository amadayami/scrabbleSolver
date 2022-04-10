import java.io.*;

public class Scrabble{
    /*
    will do these things:
    read in files
    create board with multipliers
    create human and computer players
    create the pool and set tiles in player hand
    call to the solver (since this would be for both
        the solver and the GUI)
     */


    /*
    notes for reading in board file:
    first line is the dimension of the board (square)
    if(num, dot) - word multiplier
    if(dot, num) - letter multiplier
    if(dot, dot) - no multiplier
     */

    public static void main(String[] args) throws IOException {
        DictionaryTrie trie;
        //should only have one argument, the dictionary
        File dictionary = null;
        if(args.length == 1){
            dictionary = new File(args[0]);
        }
        else{
            System.err.println("Invalid number of argments: " + args.length);
            System.exit(0);
        }
        BufferedReader br = null;
        try{
            br= new BufferedReader(new FileReader(dictionary));
            String line;
            trie = new DictionaryTrie();
            while((line = br.readLine()) != null){
                /*
                Prints the word for debugging, might
                remove this part for the other stuff
                so it won't be as ugly in the console
                 */
                System.out.println(line);
                trie.addWord(line);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        finally{
            if(br != null){
                br.close();
            }
        }
    }
}