package com.sye.datastructures.trees.wordfinder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class NeighborFinderTest {
    
    @Test
    public void testFindNeighbors_InitialPosition_EmptyVisitedNeighbors(){
        char[][] matrix = DataSetupHelper.getTestMatrix();
        List<Position> neighbors = NeighborFinder.findValidNeighbors(matrix, new Position(0,0), new HashSet<Position>());
        Assert.assertEquals(3, neighbors.size());
        Assert.assertTrue(neighbors.contains(new Position(0, 1)));
        Assert.assertTrue(neighbors.contains(new Position(1, 1)));
        Assert.assertTrue(neighbors.contains(new Position(1, 0)));
    }

    @Test
    public void testFindNeighbors_CentralPosition_VisitedNeighbors(){
        char[][] matrix = DataSetupHelper.getTestMatrix();
        Set<Position> visitedNeighbors = new HashSet<>();
        visitedNeighbors.add(new Position(0, 1));
        List<Position> neighbors = NeighborFinder.findValidNeighbors(matrix, new Position(1,1), visitedNeighbors);
        Assert.assertEquals(7, neighbors.size());
        Assert.assertTrue(neighbors.contains(new Position(0, 0)));
        Assert.assertFalse(neighbors.contains(new Position(0, 1)));
        Assert.assertTrue(neighbors.contains(new Position(0, 2)));
        Assert.assertTrue(neighbors.contains(new Position(1, 0)));
        Assert.assertTrue(neighbors.contains(new Position(1, 2)));
        Assert.assertTrue(neighbors.contains(new Position(2, 0)));
        Assert.assertTrue(neighbors.contains(new Position(2, 1)));
        Assert.assertTrue(neighbors.contains(new Position(2, 2)));

    }
    
    @Test
    public void testFindNeighbors_CentralPosition_EmptyVisitedNeighbors(){
        char[][] matrix = DataSetupHelper.getTestMatrix();
        List<Position> neighbors = NeighborFinder.findValidNeighbors(matrix, new Position(1,1), new HashSet<Position>());
        Assert.assertEquals(8, neighbors.size());
        Assert.assertTrue(neighbors.contains(new Position(0, 0)));
        Assert.assertTrue(neighbors.contains(new Position(0, 1)));
        Assert.assertTrue(neighbors.contains(new Position(0, 2)));
        Assert.assertTrue(neighbors.contains(new Position(1, 0)));
        Assert.assertTrue(neighbors.contains(new Position(1, 2)));
        Assert.assertTrue(neighbors.contains(new Position(2, 0)));
        Assert.assertTrue(neighbors.contains(new Position(2, 1)));
        Assert.assertTrue(neighbors.contains(new Position(2, 2)));
    }

    @Test
    public void testFindNeighbors_RightPosition_VisitedNeighbors(){
        char[][] matrix = DataSetupHelper.getTestMatrix();
        List<Position> neighbors = NeighborFinder.findValidNeighbors(matrix, new Position(2,1), new HashSet<Position>());
        Assert.assertEquals(5, neighbors.size());
        Assert.assertTrue(neighbors.contains(new Position(1, 0)));
        Assert.assertTrue(neighbors.contains(new Position(1, 1)));
        Assert.assertTrue(neighbors.contains(new Position(1, 2)));
        Assert.assertTrue(neighbors.contains(new Position(2, 0)));
        Assert.assertTrue(neighbors.contains(new Position(2, 2)));
    }

}
