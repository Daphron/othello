/**
 * Abstract class Piece - write a description of the class here
 */
public abstract class Piece
{
    // instance variables - replace the example below with your own
    protected String pieceName;
   
    public Piece(String pieceName)
    {
        this.pieceName = pieceName;
    }
   
    public String toString()
    {
        return pieceName;
    }
}

