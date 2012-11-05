import javax.swing.JFrame;
/**
 * This abstract class written for Dan K, Dan S, Zack, Stephen
 */

public abstract class TwoDGame extends JFrame
{
    private Piece[][] board; 
    private int rows, columns;
    private Piece startValue; 
    
    public TwoDGame(int rows, int columns)
    {
    	
        this.rows = rows; 
        this.columns = columns; 
        
        this.board = new Piece[rows][columns]; 
    }
    
    public Piece getPiece(int r, int c)
    {
        return board[r][c]; 
    }
    public Piece getPiece(int r, int c, Piece[][] vboard)
    {
    	return vboard[r][c];
    }
    public void setPiece(int r, int c, Piece p)
    {
        this.board[r][c] = p;
    }
    public void setPiece(int r, int c, Piece p, Piece[][] vboard)
    {
        vboard[r][c] = p;
    }
    
    public int numRows()
    {
        return rows; 
    }
    
    public int numColumns()
    {
        return columns; 
    }
    
    public Piece[][] getBoard()
    {
        return board; 
    }
    
    public void printBoard(Piece[][] board)
    {
        String rowResult = new String(); 
        System.out.println(" 01234567");
        int r = 0;
        for (Piece[] s : board)
        {
        	rowResult += r;
            for (Piece q : s)
            {
                if (q == null)
                    rowResult = rowResult + "."; 
                else
                    rowResult = rowResult + q; 
            }
        	rowResult += r;
        	r++;
            System.out.println(rowResult); 
            rowResult = ""; 
        }
        System.out.println(" 01234567");
    }
    public void printBoard()
    {
    	printBoard(this.board);
    }
    
    public boolean boardFull(Piece[][] board)
    {
        for (Piece[] s : board)
            for (Piece q : s)
                if (q == null)
                    return false; 
        return true; 
    }
    public boolean boardFull()
    {
    	return boardFull(this.board);
    }
    
    public boolean validMove(int r, int c, Piece[][] board)
    {
        if (r >= 0 && r < rows && c >= 0 && c < columns)
            return true; 
        else
            return false; 
    }
    public boolean validMove(int r, int c)
    {
    	return validMove(r,c,this.board);
    }
    
    public abstract boolean gameOver(); 
    //public abstract void playGame(); 
    public abstract String determineWinner(); 
    //public abstract void humansTurn(); 
    public abstract void computersTurn(); 
}