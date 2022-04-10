public class Tile{

    private char letter;
    private int val;


    public Tile(char letter, int val){
        this.letter = letter;
        this.val = val;
    }

    public char getLetter(){
        return letter;
    }

    public int getVal() {
        return val;
    }
}