package com.sye.datastructures.trees.wordfinder;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 *  Main class
 * 
 *  You can experiment with this by changing the matrix below and the dictionary in the resources folder. Please have in mind
 *  that this is just a proof of concept done in a couple of hours. Don't expect this
 *  to be 'production' quality code.
 *  
 * @author sobel003
 *
 */
public class Main {
    
    private static final Logger logger = LogManager.getLogger(Main.class);
    
    // Matrix to test.
    private static char[][] matrix = {
            {'L','A','P'},
            {'A','U','I'},
            {'U','N','Z'}
            
    };


    public static void main(String[] args) throws Exception {
        @SuppressWarnings("resource")
        ApplicationContext appContext = new ClassPathXmlApplicationContext("app-config.xml");
        WordFinder wordFinder = (WordFinder)appContext.getBean("wordFinder");
        List<String> results = wordFinder.solve(matrix);
        logger.info("Results: " + results);
    }

}
