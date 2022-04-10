import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Solver{

    /*
    needs to have the board that is read in
    current word + score
     */

    private int topScore = -1;
    private String topWord = "";
    private Anchor topAnchor = null;
    private int beginX = -1;
    private int endX = -1;
    private int beginY = -1;
    private int endY = -1;

    private Board board;
    private Hand tray;
    private DictionaryTrie trie;

    /*
    says should identify "anchors" that are in the
    current board and then work around those
    would DEF want to avoid just iterating over the
    entire board, wildly inefficient
     */

    public static void main(String[] args) throws IOException {
        DictionaryTrie trie;
        File dictionaryFile = null;
        if(args.length != 1){
            System.err.println("Invalid number of arguments: " + args.length);
            System.exit(0);
        }
        else{
            dictionaryFile = new File(args[0]);
        }
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(dictionaryFile));
            String line;
            trie = new DictionaryTrie();
            while((line = br.readLine()) != null){
                /*
                prints the dictionary for debugging but will comment
                this out for the larger dictionaries
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

        //use the code in the Scrabble to write this
        /*
        have to take in the dictionary and write to trie
        take in board and create a Board
        take in tray to create a Hand
        print board and tray to board for initial read-in check

        THEN read through board for anchors + create objects
        find prefixes that would fit within the given space to the left
        find words that fit the right space with remaining tiles
         */
    }


    /*
    NOTE TO ME:
    Need to decide how to go about the placing on the board
    once the final word is picked. Should I save the coordinates for
    the Board object? Maybe whether is was across or down? Will consider
     */

    /**
     * Takes new String and int values to replace the
     * current top word and word value. First checks that
     * the new score isn't less than or equal to the current
     * score. If this is the case, nothing happens. Otherwise,
     * the top word and score are replaced. Determination of the
     * word value will take place during the board processing,
     * since it requires the consideration of the special tiles.
     * @param newWord String representing the new word with a higher
     * value than the currently held word
     * @param newScore int representation of the new value of the word,
     * considering the special squares and the letter values
     * @param anchor The Tile that the word is created from
     */
    private void setTop(String newWord, int newScore, Anchor anchor){
        if(topScore >= newScore){
            //stop
        }
        else{
            topWord = newWord;
            topScore = newScore;
            topAnchor = anchor;
        }
    }

    /*
    THE PLAN:
    Take in the number of spaces available to the left
    Find combinations of letters from the tray that create prefixes
    with the anchor object
    Send any prefixes in the trie to the rightExtend method

    NOTE TO ME:
    Ask how to handle the A_A scenario, should I ignore the left?
    Will it be properly handled by the initial anchor?
     */
    private void leftBuild(Anchor anchor){
        int leftFree = anchor.getLeft();
        if(leftFree == 0){
            if(anchor.getY() == 0){
                //end of the board
                String prefix = "";
                prefix = prefix + anchor.getAnchorChar();
                rightExtend(prefix, anchor);
            }
            else{
                String prefix = "";
                prefix = prefix + anchor.getAnchorChar();
                boolean quit = false;
                for(int i = anchor.getY() - 1; i >= 0; i--){
                    if(quit) break;
                    else{
                        int x = anchor.getX();
                        Space current = board.getSpace(x, i);
                        if(current.isEmpty()){
                            quit = true;
                        }
                        else{
                            char add = current.getHeld().getLetter();
                            prefix = add + prefix;
                        }
                    }
                    rightExtend(prefix, anchor);
                }
            }
        }
        else if(leftFree == 1){
            int x = anchor.getX();
            int y = anchor.getY();
            if((y - 1) == 0){
                //so there is no conflicting tile
                Iterator<Tile> iter = tray.getHand().iterator();
                while(iter.hasNext()){
                    char letter = iter.next().getLetter();
                    if(verticalCross(x, y-1, letter)){
                        //then it should be valid
                        String pre = "";
                        pre = pre + letter + anchor.getAnchorChar();
                        if(trie.prefixSearch(pre)){
                            //some word has this prefix
                            rightExtend(pre, anchor);
                        }
                    }
                }
            }
            else{
                //conflicting tile, so we just send in the anchor
                String prefix = "";
                prefix = prefix + anchor.getAnchorChar();
                rightExtend(prefix, anchor);
            }
        }
        else{
            //free is length of prefix string
            int free = anchor.getLeft();
            if(anchor.getY() - free != 0){
                //not the end of the board
                free--;
            }
            char[] chars = new char[tray.getMaxTiles() + 1];
            int i = 0;
            Iterator<Tile> iter = tray.getHand().iterator();
            while(iter.hasNext()){
                char letter = iter.next().getLetter();
                chars[i] = letter;
                i++;
            }
            chars[tray.getMaxTiles() + 1] = anchor.getAnchorChar();
            Anagrams anagrams = new Anagrams(chars.toString(), free);
            List<String> prefixes = anagrams.getPossible();
            Iterator<String> iterPre = prefixes.iterator();
            while(iterPre.hasNext()){
                boolean valid = true;
                String check = iterPre.next();
                if(check.charAt(free -  1) != anchor.getAnchorChar()){
                    valid = false;
                }
                else{
                    int first = anchor.getY() - free - 1;
                    for(int j = 0; j < free - 1; j++){
                        int a = anchor.getX();
                        int b = j + first;
                        if(!verticalCross(a, b, check.charAt(j))){
                            valid = false;
                        }
                    }
                }
                if(valid){
                    rightExtend(check, anchor);
                }
            }
        }
    }


    /*
    THE PLAN:
    Take in the given prefix, maybe double check on its existence?
    Take final letters in the tray and create words with the prefix
    Check for special spaces on the board and use multipliers
    Should then check the value against the current topScore
    Store as needed

    NOTE TO ME:
    Considering a char array scenario where we can fill in the spots
    to the right that are already filled
    Then putting it back into a String and checking? Not sure about the
    efficiency but it's what I have
     */
    private void rightExtend(String prefix, Anchor anchor){
        if(trie.prefixSearch(prefix) == false){
            // shouldn't get to this, if properly called
            System.out.println("Prefix not in trie");
            return;
        }
        else if(prefix.length() == (tray.getMaxTiles() + 1)){
            //checks to see if all tiles have been used
            //if that's the case, checks against current top
            if(anchor.getRight() == 0){
                //then end of board or conflict check
                if(anchor.getY() == board.getDim()){
                    if(trie.containsWord(prefix)){
                        //word needs to be checked for score
                    }
                    else{
                        //not actually a word
                        return;
                    }
                }
                else{
                    /*
                    need to add letters to the right of the
                    anchor to the prefix before checking
                    for word
                     */
                }
            }
            else{
                if(trie.containsWord(prefix)){
                    //word needs to be checked for score
                }
                else{
                    //not actually a word
                    return;
                }
            }
        }
        //i'm not sure if I need this but in case
        char[] characters = prefix.toCharArray();
        char[] wordBuilder = new char[board.getDim()];
        for(int i = 0; i < characters.length; i++){
            wordBuilder[i] = characters[i];
        }
        int pLength = prefix.length();
        int row = anchor.getX();
        int col = anchor.getY();
        int rightFree = anchor.getRight();

        /*
        iterate over the row and fill in any spaces
        in the character array with any other letters

        then fill in the blank spaces with remaining
        tiles in the tray
         */
    }


    private void topBuild(Anchor anchor){
        int topFree = anchor.getTop();
        if(topFree == 0){
            if(anchor.getX() == 0){
                String prefix = "";
                prefix = prefix + anchor.getAnchorChar();
                bottomExtend(prefix, anchor);
            }
            else{
                String prefix = "";
                prefix = prefix + anchor.getAnchorChar();

            }
        }
        else if(topFree == 1){
            int x = anchor.getX();
            int y = anchor.getY();
            if(x - 1 == 0){
                //end of the board
                Iterator<Tile> iter = tray.getHand().iterator();
                while(iter.hasNext()){
                    char c = iter.next().getLetter();
                    if( horizontalCross(x - 1, y, c) ){
                        //should be valid
                        String prefix = "";
                        prefix = prefix + c + anchor.getAnchorChar();
                        if(trie.prefixSearch(prefix)){
                            //some word has this prefix
                            bottomExtend(prefix, anchor);
                        }
                    }
                }
            }
            else{
                //conflicting anchor, send in only anchor char
                String prefix = "";
                prefix = prefix + anchor.getAnchorChar();
                bottomExtend(prefix, anchor);
            }
        }
        else{

        }
    }

    private void bottomExtend(String prefix, Anchor anchor){
        int topFree = anchor.getTop();
        if(!trie.prefixSearch(prefix)){
            System.out.println("Prefix not in trie");
            return;
        }
        else if(prefix.length() == (tray.getMaxTiles() + 1)){
            //then all tiles in the hand are used
        }
    }

    /**
     * If the starting space is empty, returns false. If there
     * are no tiles above or below the space, then returns true
     * since there is nothing to check for. Otherwise, returns
     * true only if the word created by the added starting tile
     * can be found in the trie.
     * @param x row index of the starting space on the board
     * @param y column index of the starting space on the board
     * @param place the possible character to be placed on
     * the starting space
     * @return true if the created word is in the trie and
     * false if the created word is not in the trie
     */
    private boolean verticalCross(int x, int y, char place){
        Space start = board.getSpace(x, y);
        if(start.isEmpty()){
            return false;
        }
        else if(board.getSpace(x - 1, y).isEmpty()
            && board.getSpace(x + 1, y).isEmpty()){
                return true;
        }

        String word = "" + place;
        for(int i = (x - 1); i >= 0; i--){
            Space top = board.getSpace(i, y);
            if(top.isEmpty()){
                break;
            }
            else{
                char add = top.getHeld().getLetter();
                word = add + word;
            }
        }
        for(int j = (x + 1); j > board.getDim(); j++) {
            Space bottom = board.getSpace(j, y);
            if (bottom.isEmpty()) {
                break;
            }
            else {
                char add = bottom.getHeld().getLetter();
                word = word + add;
            }
        }
        return trie.containsWord(word);
    }

    /**
     * If the starting space is empty, returns false. If there
     * are no tiles to the right or left of the starting space,
     * returns true since there is nothing to check for.
     * Otherwise returns true only if the created word can be
     * found within the dictionary trie.
     * @param x row index of the starting space
     * @param y column index of the starting space
     * @param place possible character to be placed on the
     * starting space
     * @return true if the created word is in the trie and false
     * if the created word is not in the trie
     */
    private boolean horizontalCross(int x, int y, char place){
        Space start = board.getSpace(x, y);
        if(start.isEmpty()){
            return false;
        }
        else if(board.getSpace(x, y - 1).isEmpty() &&
                board.getSpace(x, y + 1).isEmpty()){
            return true;
        }

        String word = "" + place;
        for(int i = (y - 1); i >= 0; i--){
            Space left = board.getSpace(x, i);
            if(left.isEmpty()){
                break;
            }
            else{
                char add = left.getHeld().getLetter();
                word = add + word;
            }
        }
        for(int j = (y + 1); j > board.getDim(); j++){
            Space right = board.getSpace(x, j);
            if(right.isEmpty()){
                break;
            }
            else{
                char add = right.getHeld().getLetter();
                word = word + add;
            }
        }
        return trie.containsWord(word);
    }

    /**
     *
     */
    private void placeWord(){
        Space begin = board.getSpace(beginX, beginY);
        if(beginX == endX){
            //word is horizontal

        }
        else if(beginY == endY){
            //word is vertical

        }
        else{
            System.err.println("Shouldn't place diagonally");
            System.exit(0);
        }
    }

    private int scoreVertical(int first, Anchor anchor, String word){
        int column = anchor.getY();
        return -1;
    }

    private int scoreHorizontal(int first, Anchor anchor, String word){
        HashMap<Character, Integer> tiles = tray.hash();
        int row = anchor.getX();
        int totalScore = 0;
        int wordMult = 1;
        for(int i = first; i < word.length() - 1; i++){
            Space search = board.getSpace(row, i);
            int multi = 1;
            if(search.isEmpty()){
                if(search.getTypeMult().equals("word")){
                    wordMult = wordMult * search.getNumMult();
                }
                else if(search.getTypeMult().equals("letter")){
                    multi = multi * search.getNumMult();
                }
                else{
                    //nothing, no multiplier
                }

                int index = i - first;
                char letter = word.charAt(index);
                int val = tiles.get(letter);
                val = val * multi;
                totalScore += val;
            }
            else{
                //space isn't empty
                int add = search.getHeld().getVal();
                totalScore += add;
            }
        }

        int finalS = totalScore * wordMult;
        return finalS;
    }
}