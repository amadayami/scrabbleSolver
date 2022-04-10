import java.util.*;
public class Anagrams{

    private List<String> possible = new ArrayList<>();
    private int max = 0;

    /**
     * The constructor for the class that find the
     * possible combination of letters passed to it.
     * @param str the String of characters possible
     * @param max the desired length of the prefix
     * or extension
     */
    public Anagrams(String str, int max){
        this.max = max;
        int length = str.length();
        permute(str, 0, length - 1);
    }

    /**
     * Takes in a String and the indexes and swaps
     * the letters to create anagrams of the String
     * @param str the String used to find the various
     * combinations of characters
     * @param l the starting index
     * @param r the ending index
     */
    private void permute(String str, int l, int r){
        if(l == r){
            possible.add(str);
        }
        else{
            for(int i = l; i <= r; i++){
                str = swap(str, l, i);
                permute(str, l + 1, r);
                str = swap(str, l, i);
            }
        }
    }

    /**
     * Swaps the characters at the given indexes and
     * returns the resulting String
     * @param str the String to have letters swapped
     * @param a the index of the first letter to be
     * swapped
     * @param b the index of the second letter to
     * be swapped
     * @return resulting String after the two
     * characters have switched locations
     */
    private String swap(String str, int a, int b){
        char c;
        char[] chars = str.toCharArray();
        c = chars[a];
        chars[a] = chars[b];
        chars[b] = c;
        return chars.toString();
    }

    /**
     * Returns a list of truncated Strings, as
     * designated by the constructor. If the given
     * truncation is already contained within the
     * list, then we skip it.
     * @return List of truncated Strings
     */
    public List<String> getPossible(){
        List<String> chopped = new ArrayList<>();
        Iterator<String> iter = possible.iterator();
        while(iter.hasNext()){
            String prefix = "";
            for(int i = 0; i < max; i++){
                prefix = prefix + iter.next().charAt(i);
            }
            if(!chopped.contains(prefix)){
                chopped.add(prefix);
            }
        }
        return chopped;
    }
}