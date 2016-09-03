package com.sye.datastructures.trees.wordfinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is the main class responsible to drive the entire flow.
 * 
 * @author luis flores soberon
 *
 */
public class WordFinder {
   
    private Dictionary dictionary;
    private ExecutorService executorService;
    
    private static final Logger logger = LogManager.getLogger(WordFinder.class);
    
    /**
     * Solves the problem by finding word in the given matrix.
     *  
     * @param matrix   - The matrix we want to explore.
     * @return         - A {@link List} of words found in the matrix.
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public List<String> solve(char[][] matrix) throws InterruptedException, ExecutionException{
        
        Map<Position,Future<List<String>>> futures = new HashMap<Position,Future<List<String>>>();
        for(int i=0; i < matrix.length; i++){
            for(int j=0; j < matrix[i].length; j++){
                Entry entry = dictionary.findRootEntry(matrix[i][j]);
                if(entry != null){
                    Position currentPosition = new Position(i, j);
                    PathExplorerTask pe = new PathExplorerTask(matrix, currentPosition, entry);
                    futures.put(currentPosition,executorService.submit(pe));                    
                }
            }
        }
        
        List<String> results = new ArrayList<>();

        // We need to wait for all the tasks to complete
        for(Position position: futures.keySet()){
            Future<List<String>> future = futures.get(position);
            logger.info("Waiting for future for position " + position);
            results.addAll(future.get());
        }
        
        logger.info("Finshed");
        executorService.shutdown();
        return results;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }
    
    

}
