import java.awt.Color;

public class level
{
    private int numRows;
    private int numCols;
    private Color brickColor;

    public level(int numRows, int numCols, Color brickColor){
        this.numRows = numRows;
        this.numCols = numCols;
        this.brickColor = brickColor;
    }

    public int getNumRows(){
        return numRows;
    }
    public int getNumCols(){
        return numCols;
    }
    public Color getBrickColor(){
        return brickColor;
    }




    
}
