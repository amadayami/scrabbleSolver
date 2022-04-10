import java.util.Iterator;
import java.util.List;

public class Player{

    private Hand tray;
    private int totalScore = 0;

    public Player(){
        this.tray = new Hand();
    }

    public int getTotalScore(){
        return totalScore;
    }

    /**
     * Takes in the hand of the player, iterates
     * over each element, and adds the letter
     * assigned to each tile to a String
     * representation of the hand. Returns
     * this String.
     * @return String representation of the
     * player's hand
     */
    public String trayToString(){
        //iterate over tray and print out letters
        String tiles = "";
        List<Tile> tileSet = tray.getHand();
        Iterator<Tile> chars = tileSet.iterator();
        while(chars.hasNext()){
            char c = chars.next().getLetter();
            tiles = tiles + c + " ";
        }
        return tiles;
    }

    protected void drawTiles(){
        //take from pool and add to hand
        //should draw until max tile is reached
        int max = tray.getMaxTiles();
        while(tray.getHand().size() < max){
            //remove tiles from pool
            //need to check for tiles first
        }
    }

    protected void playTile(){
        /*
        should somehow take the tile from the tray
        and add it to the board object

        notes:
        should it return a tile for the game?
        or maybe just return an int for debugging?
        should it take a tile as a parameter?
         */
    }

    protected void playTiles(){
        /*
        making second play tile method in case
        i end up wanting one that can play
        multiple tiles at once
         */
    }

    /**
     * Returns the endgame score for the player.
     * This is the score up to this point minus
     * the value of the player's tray.
     * @return int value representing the final
     * score of the player in the case of the
     * end game
     */
    public int endGameScore(){
        int finalScore = 0;
        int trayVal = tray.handVal();
        finalScore = totalScore - trayVal;
        return totalScore;
    }
}