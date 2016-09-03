package com.sye.datastructures.trees.wordfinder;

import org.apache.commons.lang.builder.ToStringBuilder;

/** 
 * 
 * Represents an entry in the dictionary and implements the Trie Tree used by the application.
 * 
 * @author luis flores soberon
 *
 */
public class Entry {

    public static final int ALPHABET_SIZE = 27;
    
    private Entry[] nextLevel;
    private char letter;
    private boolean finishedWord;
    
    /**
     * 
     * @param letter         - The letter we want to create the Entry for.
     * @param finishedWord   - Flag to indicate if this entry finishes a word.
     */
    public Entry(char letter, boolean finishedWord) {
        this.letter = letter;
        this.finishedWord = finishedWord;
        nextLevel = new Entry[ALPHABET_SIZE];
    }
    
    
    /**
     * Finds the entry that corresponds to a letter in the subtree.
     *  
     * @param letter   - The letter to find.
     * @return         - The {@link Entry}.
     */
    public Entry findEntry(char letter){
        return nextLevel[Entry.indexOf(letter)];
    }

    
    /**
     * Creates the subtree necessary to store the remainder of a word.
     * 
     * @param remainder - The remainder string to store.
     */
    public void addRemainder(String remainder){
        char nextChar = remainder.toUpperCase().charAt(0);
        int index = indexOf(nextChar);
        boolean nextCharFinishedWord = remainder.length() == 1;
        if(nextLevel[index] == null){
            Entry nextEntry = new Entry(nextChar, nextCharFinishedWord);
            nextLevel[index] = nextEntry;
        }else{
            nextLevel[index].setFinishedWord(nextCharFinishedWord);
        }
        
        if(!nextCharFinishedWord){
            nextLevel[index].addRemainder(remainder.substring(1,remainder.length()));
        }
    }
    

    public static int indexOf(char letter){
        return letter - 65;
    }

    public boolean isFinishedWord() {
        return finishedWord;
    }

    public void setFinishedWord(boolean finishedWord) {
        this.finishedWord = finishedWord;
    }

    public char getLetter() {
        return letter;
    }
    

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
