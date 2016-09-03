package com.sye.datastructures.trees.wordfinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class responsible for finding valid neighbors to explore for a specific {@link Position}.
 *  
 * @author luis flores soberon
 *
 */
public class NeighborFinder {

    private static void addValidNeighbors(List<Position> validNeighbors, int verticalPosition, int horizontalPosition, boolean isLeftHorizontalValid, boolean isRightHorizontalValid, int leftHorizontal, int rightHorizontal, Set<Position> visitedNeighbors){
        Position pos = new Position(verticalPosition, horizontalPosition);
        if(!visitedNeighbors.contains(pos)){
            validNeighbors.add(pos);
        }
        
        pos = new Position(verticalPosition, leftHorizontal);
        if(isLeftHorizontalValid && !visitedNeighbors.contains(pos)){
            validNeighbors.add(pos);
        }

        pos = new Position(verticalPosition, rightHorizontal);
        if(isRightHorizontalValid && !visitedNeighbors.contains(pos)){
            validNeighbors.add(pos);
        }            

    }
   
    /**
     * This method simply explores the 8 valid neighbors of a given {@link Position} that are not yet explored.
     * 
     * @param matrix            - The matrix we are exploring
     * @param currentPosition   - The position we are trying to get neighbors for
     * @param visitedNeighbors  - The already visited neighbors that we should exclude
     * @return                  - List of {@link Position} to continue exploring
     */
    public static List<Position> findValidNeighbors(char[][] matrix, Position currentPosition, Set<Position> visitedNeighbors){
        int upperVertical = currentPosition.getVerticalPosition() - 1;
        int bottomVertical = currentPosition.getVerticalPosition() + 1;
        int leftHorizontal = currentPosition.getHorizontalPosition() - 1;
        int rightHorizontal = currentPosition.getHorizontalPosition() + 1;
        
        boolean isUpperVerticalValid = upperVertical >= 0;
        boolean isBottomVerticalValid = bottomVertical < matrix.length;
        boolean isLeftHorizontalValid = leftHorizontal >= 0;
        // We assume we have a valid matrix
        boolean isRightHorizontalValid = rightHorizontal < matrix[0].length;
        
        List<Position> validNeighbors = new ArrayList<>();
        
        // Left column
        if(isUpperVerticalValid){        
            addValidNeighbors(validNeighbors, upperVertical, currentPosition.getHorizontalPosition(), isLeftHorizontalValid, isRightHorizontalValid, leftHorizontal, rightHorizontal, visitedNeighbors);
        }
        
        // Right column
        if(isBottomVerticalValid){
            addValidNeighbors(validNeighbors, bottomVertical, currentPosition.getHorizontalPosition(), isLeftHorizontalValid, isRightHorizontalValid, leftHorizontal, rightHorizontal, visitedNeighbors);
        }
        
        // Central
        Position pos = new Position(currentPosition.getVerticalPosition(), leftHorizontal);
        if(isLeftHorizontalValid && !visitedNeighbors.contains(pos)){
            validNeighbors.add(pos);
        }

        pos = new Position(currentPosition.getVerticalPosition(), rightHorizontal);
        if(isRightHorizontalValid && !visitedNeighbors.contains(pos)){
            validNeighbors.add(pos);
        }
        
        return validNeighbors;
        
        
    }

}
