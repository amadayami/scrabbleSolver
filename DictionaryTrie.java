import java.util.*;

public class DictionaryTrie{

    /**
     * Inner class for TrieNode, should only
     * be used by the DictionaryTrie
     */
    protected class TrieNode{

        private char c;
        private boolean isLeaf = false;
        private HashMap<Character, TrieNode> children = new HashMap<>();

        //new node containing character
        public TrieNode(char c){
            this.c = c;
        }

        //default tree node with no character, used for root node
        public TrieNode(){

        }

        protected void setLeaf(boolean leaf){
            isLeaf = leaf;
        }
    }

    //the initial node of the trie, doesn't contain a char
    private TrieNode root;

    public DictionaryTrie(){
        this.root = new TrieNode();
    }

    /**
     * Adds the given word into the trie structure.
     * If the word is 0 chars long or null, then the
     * method quits. Otherwise, turns the word into
     * all lowercase letters. Then searches through
     * the existing trie structure (which is at least
     * the root node) and adds the word if it doesn't
     * already exist. The final character node is
     * marked as a leaf for checks.
     * @param word the String to be added
     * to the trie structure
     */
    public void addWord(String word){
        if(word.equals("")) return;
        if(word == null) return;
        word = word.toLowerCase();
        TrieNode current = root;
        char[] chars = word.toCharArray();
        for(char c : chars){
            HashMap<Character, TrieNode> children = current.children;
            if(children.containsKey(c)){
                //we continue down the tree and get the node
                current = children.get(c);
            }
            else{
                //create new node and add to children
                current = new TrieNode(c);
                children.put(c, current);
            }
        }
        current.setLeaf(true);
    }

    /*
    I don't like this method, seems incorrect
    check with someone smarter, they should be
    able to tell me if it won't work for me
     */
    public boolean containsWord(String word){
        word = word.toLowerCase();
        TrieNode current = root;
        char[] chars = word.toCharArray();
        for(char c : chars){
            HashMap<Character, TrieNode> children = current.children;
            if(children.containsKey(c)){
                current = children.get(c);
            }
            //otherwise word not in dictionary
            else return false;
        }
        if(current.isLeaf == true){
            return true;
        }
        else return false;
    }

    /**
     * Follows the same sort of process as the search
     * for an entire word. If there is any word that
     * contains the given prefix, then the method
     * returns true. If there is no word with the
     * prefix (ex. qx, zw), then method returns false.
     * @param prefix the given prefix to be searched
     * for in the trie
     * @return boolean representation of whether or not
     * the prefix is within the trie
     */
    public boolean prefixSearch(String prefix){
        prefix = prefix.toLowerCase();
        TrieNode current = root;
        char[] chars = prefix.toCharArray();
        for(char c : chars){
            HashMap<Character, TrieNode> children = current.children;
            if(children.containsKey(c)){
                current = children.get(c);
            }
            else return false;
        }
        return true;
    }
}