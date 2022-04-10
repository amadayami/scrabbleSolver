public class Anchor{
    /*
    might be able to put this as an inner class
    within the board object
     */

    //the character on the anchor tile
    private Tile anchor;
    //the x and y coordinates of the anchor on the board
    private int x;
    private int y;
    //the number of free spaces on the left or right side
    private int left;
    private int right;
    //the number of free spaces on the top and bottom
    private int top;
    private int bottom;

    public Anchor(){

    }

    public Anchor(Tile c, int x, int y){
        this.anchor = c;
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the number of free spaces on the left and right sides,
     * the top, and the bottom of the anchor. Originally had in the
     * constructor but it became a little much.
     * @param left number of free spaces on the left side
     * before the end of the board or finding another anchor
     * @param right number of free spaces on the right side
     * before the end of the board or finding another anchor
     * @param top number of free spaces above the anchor before
     * the end of the board or finding another anchor
     * @param bottom number of free spaces below the anchor before
     * the end of the board or finding another anchor
     */
    public void setSides(int left, int right, int top, int bottom){
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public Anchor(Tile c){
        this.anchor = c;
    }

    /**
     * Returns the Tile that the anchor represents
     * @return Tile object represented by the anchor
     */
    public Tile getTile(){
        return anchor;
    }

    public char getAnchorChar(){
        return anchor.getLetter();
    }

    /**
     * Retrieves the value set as the number of spaces
     * to the right of the anchor. This is the number until
     * there is another anchor or the end of the board.
     * @return the number of free spaces to the right of
     * an anchor before hitting another anchor or the end
     * of the board space
     */
    public int getRight(){
        return right;
    }

    /**
     * Retrieves the value set as the number of spaces to the
     * left of the anchor.
     * @return the number of free spaces to the left of the
     * anchor before hitting another anchor or the end of the
     * board space
     */
    public int getLeft(){
        return left;
    }

    /**
     * Retrieves the value set as the number of spaces on the
     * top of the anchor.
     * @return the number of free spaces above the anchor before
     * hitting another anchor or the end of the board space
     */
    public int getTop(){
        return top;
    }

    /**
     * Retrieves the value set as the number of spaces on the
     * bottom of the anchor.
     * @return the number of free spaces below the anchor
     * before hitting another anchor or the end of the
     * board space
     */
    public int getBottom(){
        return bottom;
    }

    /**
     * Retrieves the row value of the space that the anchor is
     * located on within the board space.
     * @return int value representing the row index of the
     * Space on the board containing the anchor
     */
    public int getX(){
        return x;
    }

    /**
     * Retrieves the column value of the space that the anchor is
     * located on within the board space
     * @return int value representing the column index of the
     * Space on the board containing the anchor
     */
    public int getY(){
        return y;
    }


}