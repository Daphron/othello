import java.awt.event.ActionListener;

/**
 * class OthelloPiece
 */
public class OthelloPiece extends Piece
{

    public OthelloPiece(String color)
    {
        super(color);
    }
    public void flip()
    {
        if(pieceName.equals("X"))
            pieceName = "O";
        else
            pieceName = "X";
    }
}