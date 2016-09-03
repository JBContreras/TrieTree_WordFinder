package com.sye.datastructures.trees.wordfinder;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PathExplorerTaskTest {

    
   private Dictionary dictionary;
   private char[][] matrix;
    
   @Before
   public void setup() throws Exception{
       matrix = DataSetupHelper.getTestMatrix();
       dictionary = new Dictionary("dictionary.txt");
   }
   
   @Test
   public void testFoundWords() throws Exception{
       List<String> expectedWords = Arrays.asList(new String[]{"LAU", "LAU", "LUPA", "LUNA", "LUZ", "LAU", "LAPIZ"});
       Entry entry = dictionary.findRootEntry('L');
       List<String> results = new PathExplorerTask(matrix, new Position(0, 0), entry).call();
       Assert.assertEquals(7, results.size());
       for(String word: expectedWords){
           Assert.assertTrue(results.contains(word));
       }       
   }

}
