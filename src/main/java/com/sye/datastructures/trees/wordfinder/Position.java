package com.sye.datastructures.trees.wordfinder;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Represents a position in the matrix.
 * 
 * @author luis flores soberon
 *
 */
public class Position {

    private int verticalPosition;
    private int horizontalPosition;
    
    public Position(int verticalPosition, int horizontalPosition) {
        this.verticalPosition = verticalPosition;
        this.horizontalPosition = horizontalPosition;
    }

    public int getVerticalPosition() {
        return verticalPosition;
    }

    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + verticalPosition;
        result = prime * result + horizontalPosition;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (verticalPosition != other.verticalPosition)
            return false;
        if (horizontalPosition != other.horizontalPosition)
            return false;
        return true;
    }

    @Override
    public String toString() {        
        return ToStringBuilder.reflectionToString(this);
    }

}
