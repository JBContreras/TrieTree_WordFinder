package com.sye.datastructures.trees.wordfinder;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * This class represents the dictionary of words.
 * 
 * It uses a Trie Tree to store the words. This implementation is just a proof of concept 
 * and is expecting words of more than one digit length.
 * 
 * @author luis flores soberon
 *
 */
public class Dictionary {

    private Entry[] rootEntries;
    
    /**
     * 
     * @param fileName    - File name of the file we want to build the {@link Dictionary} from.  
     * @throws Exception
     */
    public Dictionary(String fileName) throws Exception{
        rootEntries = new Entry[Entry.ALPHABET_SIZE];
        Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
        List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
        for(String line : lines){
            int index = Entry.indexOf(line.charAt(0));
            if(rootEntries[index] == null){
                rootEntries[index] = new Entry(line.charAt(0), false);
            }
            rootEntries[index].addRemainder(line.substring(1,line.length()));            
        }
    }
    
    /**
     * Returns the subtree for the given {@code letter}.
     * 
     * @param letter   - The leter we want to find the entry for.
     * @return         - The letter's {@link Entry}.
     */
    public Entry findRootEntry(char letter){
        return rootEntries[Entry.indexOf(letter)];
    }
    
}
