import java.nio.charset.CharacterCodingException;
import java.util.*;

public class Hand{

    private int maxTiles;
    private List<Tile> hand = new ArrayList<>();

    //constructor that takes given tile max
    public Hand(int max){
        this.maxTiles = max;
    }

    //default hand with default maximum
    public Hand(){
        this.maxTiles = 7;
    }

    /**
     * Sends the value of the maximum number
     * of tiles that can be held in a player
     * hand.
     * @return int value representing max number
     * of tiles held by a player
     */
    public int getMaxTiles(){
        return maxTiles;
    }

    /**
     * Takes in the given set of Tiles using
     * an iterator and adds each one to the
     * player hand. Will quit if the hand would
     * exceed maximum number of tiles when new
     * tiles are added.
     * @param toAdd List of Scrabble Tiles to
     * be added to the current tray
     */
    public void addTiles(List<Tile> toAdd){
        int handL = hand.size();
        int addL = toAdd.size();
        if(handL + addL > maxTiles){
            System.out.println("Tried to draw too many tiles");
            return;
        }
        else{
            Iterator<Tile> iter = toAdd.iterator();
            while(iter.hasNext()){
                hand.add(iter.next());
            }
        }
    }

    /**
     * Returns a list structure holding all
     * of the tiles being held in the player hand
     * @return List of tile objects in player tray
     */
    public List<Tile> getHand(){
        return hand;
    }

    /**
     * Creates a HashMap of the tile characters and integer
     * values to make it easier to score a word
     * @return HashMap of Character and Integer values
     * representing the information held on a Tile object
     */
    public HashMap<Character, Integer> hash(){
        HashMap<Character, Integer> hashMap = new HashMap<>();
        Iterator<Tile> iter = hand.iterator();
        while(iter.hasNext()){
            hashMap.put(iter.next().getLetter(), iter.next().getVal());
        }
        return hashMap;
    }

    /**
     * Finds the value of all the tiles held in
     * a player's hand. This would be used in the
     * case that neither player can play a word and
     * the game ends.
     * @return the int value of the tiles on a
     * player's tray
     */
    public int handVal(){
        int total = 0;
        Iterator<Tile> iter = hand.iterator();
        while(iter.hasNext()){
            int add = iter.next().getVal();
            total = total + add;
        }

        return total;
    }
}