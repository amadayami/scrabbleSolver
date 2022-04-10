public class Board{

    private Space[][] board;
    private int dim;

    public Board(){
        board = new Space[15][15];
        this.dim = 15;
    }

    public Board(int dim){
        board = new Space[dim][dim];
        this.dim = dim;
    }

    public Space getSpace(int x, int y){
        return board[x][y];
    }

    public String boardString(){
        for(int i = 0; i < dim; i++){
            String row = "";
            for(int j = 0; j < dim; j++){
                Space curr = board[i][j];

            }
        }
        return null;
    }

    public void addSpace(Space add, int x, int y){
        board[x][y] = add;
    }

    public int getDim(){
        return dim;
    }
}