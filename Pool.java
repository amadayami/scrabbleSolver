import java.util.*;


public class Pool{
    private List<Tile> pool = new ArrayList<>();

    //see about how to send the tile set info
    //might need two constructors
    public Pool(){

    }

    public int numTiles(){
        return pool.size();
    }

    public Tile removeTile(){
        //will randomly remove tile from pool
        //return said tile for adding to a hand
        int length = pool.size();
        Random random = new Random();
        int rand = random.nextInt(length);
        Tile remove = pool.get(rand);
        pool.remove(rand);
        return remove;
    }

    public void addTiles(char letter, int val, int instances){
        if(instances == 1){
            pool.add(new Tile(letter, val));
        }
        else{
            for(int i = 0; i < instances; i++){
                pool.add(new Tile(letter, val));
            }
        }
    }
}