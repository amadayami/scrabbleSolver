public class Space{

    /*
    isEmpty is for whether or not there is a tile
    currently being played in this space
     */
    private boolean isEmpty;
    private int numMult;
    private String typeMult;
    private Tile held;

    /**
     * Constructer for space object to be placed
     * in the board structure. This one is for a
     * square that has no multiplier.
     */
    public Space(){
        this.isEmpty = true;
        this.numMult = 1;
        this.typeMult = "none";
        this.held = null;
    }

    /**
     * Constructor for space object to be placed
     * in the board structure. This one is for
     * squares that have a multiplier.
     * @param multiplier the value of the multiplier
     * @param type the kind of multiplier
     */
    public Space(int multiplier, String type){
        //type will either be word or letter
        this.isEmpty = true;
        this.numMult = multiplier;
        this.typeMult = type;
        this.held = null;
    }

    /**
     * Returns the value of the multiplier
     * found on this Space on the Board
     * @return int representing the amount
     * a word or letter is multiplied by
     */
    public int getNumMult(){
        return numMult;
    }

    /**
     * Checks to see if there is a Tile
     * currently being played on the Board
     * @return boolean representation of
     * the status of the Space
     */
    public boolean isEmpty(){
        return isEmpty;
    }

    /**
     * Returns a string representation the
     * type of multiplier on the Space,
     * either a letter or a word mult.
     * @return String representation of the
     * type of multiplier
     */
    public String getTypeMult(){
        return typeMult;
    }

    /**
     * Checks to see if there is a Tile being held
     * in the space on the board. If the space is
     * not empty, then the Tile is sent. Otherwise,
     * system prints message and null is returned.
     * @return Tile being held on space or null if
     * space is empty
     */
    public Tile getHeld(){
        if(isEmpty){
            System.out.println("Space is empty");
            return null;
        }
        else return held;
    }

    /**
     * Checks to see if the given space is empty. If not,
     * system prints out a message and does nothing.
     * Otherwise, adds the tile to the space and flags
     * the space as not being empty.
     * @param place the tile to be placed on the space
     * on the board
     */
    public void placeTile(Tile place){
        if(!isEmpty){
            System.out.println("Space not empty");
        }
        else{
            held = place;
            isEmpty = false;
        }
    }
}