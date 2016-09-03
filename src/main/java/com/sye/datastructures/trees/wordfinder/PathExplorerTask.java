package com.sye.datastructures.trees.wordfinder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Class responsible for driving the exploration of the matrix from an initial {@link Position}.
 * 
 * We want to explore the matrix in parallel so we implement it as a {@link Callable} class.
 * 
 * @author luis flores soberon
 *
 */
public class PathExplorerTask implements Callable<List<String>>{

    private char[][] matrix;
    private Position currentPosition;    
    private Entry rootEntry;
    
    /**
     * Constructor.
     * 
     * @param matrix             - The matrix we want to explore.
     * @param currentPosition    - The position we want to start exploring from.
     * @param rootEntry          - The root {@link Entry} we want to find the words into.
     */
    public PathExplorerTask(char[][] matrix, Position currentPosition, Entry rootEntry) {
        this.matrix = matrix;
        this.currentPosition = currentPosition;        
        this.rootEntry = rootEntry;
    } 
    
    /**
     * Finds words in the {@code matrix} and stores them in the {@code foundWords} list.
     * 
     * @param entry             - The dictionary {@link Entry} we are working with.
     * @param position          - The {@link Position} we are exploring from.
     * @param visitedNeighbors  - A {@link Set} of neighbors already visited that we want to skip.
     * @param foundWords        - A list of found words.
     * @param sb                - A {@link StringBuilder} to build up the found words.
     */
    public void findWordsForEntry(Entry entry, Position position, Set<Position> visitedNeighbors, List<String> foundWords, StringBuilder sb){
        List<Position> neighbors = NeighborFinder.findValidNeighbors(matrix, position, visitedNeighbors);
        for(Position neighbor : neighbors){
            StringBuilder localSb = new StringBuilder(sb);
            Set<Position> localVisitedNeighbors = new HashSet<>(visitedNeighbors);
            Entry neighborEntry = entry.findEntry(matrix[neighbor.getVerticalPosition()][neighbor.getHorizontalPosition()]);
            if(neighborEntry != null){
                localSb.append(neighborEntry.getLetter());
                if(neighborEntry.isFinishedWord()){
                    foundWords.add(localSb.toString());
                }
                localVisitedNeighbors.add(neighbor);
                findWordsForEntry(neighborEntry, neighbor, localVisitedNeighbors, foundWords, localSb);
            }
        }
    }
    
    @Override
    public List<String> call() {
        List<String> foundWords = new ArrayList<>();
        StringBuilder sb = new StringBuilder(String.valueOf(rootEntry.getLetter()));

        Set<Position> visitedNeighbors = new HashSet<>();
        visitedNeighbors.add(currentPosition);
        
        findWordsForEntry(rootEntry, currentPosition, visitedNeighbors, foundWords, sb);

        return foundWords;
    }

}
