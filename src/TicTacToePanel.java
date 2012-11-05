import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

/******************************
* Create a new compoment class.
******************************/

public class TicTacToePanel extends JPanel
{
	private char board[][] = new char[8][8];
	public final static char X = 'X';
	public final static char O = 'O';
	public final static char Empty = ' ';
	public static final int SpaceSize = 40;
	public static final int CellPadding = 5;
	int gameOver;
	private char turn;
	
	public TicTacToePanel()
	{
		super();  
		reset();		
		addMouseListener(
			new MouseAdapter()
			{			
				public void mousePressed(MouseEvent e)
				{
					moveMade(e.getX() / SpaceSize, e.getY() / SpaceSize);
				}
			}
		);
	}

	// Set the game to its intial values.
	public void reset()
	{
		gameOver = 0;
		turn = X;
		for (int row=0; row<3; row++)
		{
			for (int col=0; col<3; col++)
			{
				board[col][row] = Empty;
			}
		}
	}
	
	// Draw the Tic Tac Toe game.
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		//Draw Vertical Lines
		g.drawLine(SpaceSize, 40, SpaceSize, SpaceSize * 9);
		g.drawLine(SpaceSize * 2, 40, SpaceSize * 2, SpaceSize * 9);
		g.drawLine(SpaceSize * 3, 40, SpaceSize * 3, SpaceSize * 9);
		g.drawLine(SpaceSize * 4, 40, SpaceSize * 4, SpaceSize * 9);
		g.drawLine(SpaceSize * 5, 40, SpaceSize * 5, SpaceSize * 9);
		g.drawLine(SpaceSize * 6, 40, SpaceSize * 6, SpaceSize * 9);
		g.drawLine(SpaceSize * 7, 40, SpaceSize * 7, SpaceSize * 9);
		g.drawLine(SpaceSize * 8, 40, SpaceSize * 8, SpaceSize * 9);
		g.drawLine(SpaceSize * 9, 40, SpaceSize * 9, SpaceSize * 9);
		//Draw horizontal Lines
		g.drawLine(40, SpaceSize, SpaceSize * 9, SpaceSize);		
		g.drawLine(40, SpaceSize * 2, SpaceSize * 9, SpaceSize * 2);
		g.drawLine(40, SpaceSize * 3, SpaceSize * 9, SpaceSize * 3);
		g.drawLine(40, SpaceSize * 4, SpaceSize * 9, SpaceSize * 4);
		g.drawLine(40, SpaceSize * 5, SpaceSize * 9, SpaceSize * 5);
		g.drawLine(40, SpaceSize * 6, SpaceSize * 9, SpaceSize * 6);
		g.drawLine(40, SpaceSize * 7, SpaceSize * 9, SpaceSize * 7);
		g.drawLine(40, SpaceSize * 8, SpaceSize * 9, SpaceSize * 8);
		g.drawLine(40, SpaceSize * 9, SpaceSize * 9, SpaceSize * 9);
		
		// Draw X's and O's
		for (int col=0; col<3; col++)
		{
			for(int row=0; row<3; row++)
			{
				// Is there is an X in this position?
				if (board[col][row] == X)
				{
					// Draw an X on the board.
					g.setColor(Color.RED);
					g.drawLine( SpaceSize * col + CellPadding, 
								SpaceSize * row + CellPadding, 
								SpaceSize * col  + SpaceSize - CellPadding , 
								SpaceSize * row  + SpaceSize - CellPadding );
					
					g.drawLine( SpaceSize * col + SpaceSize - CellPadding , 
								SpaceSize * row + CellPadding, 
								SpaceSize * col + CellPadding, 
								SpaceSize * row  + SpaceSize - CellPadding );
				}
				
				// If not an X, is there an O in this position?			
				else if (board[col][row] == O)
				{
					// Draw an O on the board.
					g.setColor(Color.BLUE);
					g.drawOval( SpaceSize * col + CellPadding, 
								SpaceSize * row + CellPadding, 
								SpaceSize - CellPadding * 2, 
								SpaceSize - CellPadding * 2);
				}
			}
		}
	}

	/************************************************************ 
	See if ayone has won the game yet.
	Arguments:
	  player: A char containing the constant X or the constant O.
	   		  This method checks to see if this player has won.
	Return values:
	  0: Game is not over, nobody has won.
	  1: Game is over, player has won.
	  2: Game is over, nobody won.
	*************************************************************/
	private int checkForWin(char player)
	{
		int win = 2;
		if (board[0][0]==player && 
			board[0][1]==player && 
			board[0][2]==player)
			// Top row.
			win = 1;
		else if (board[0][1]==player && 
			board[1][1]==player && 
			board[2][1]==player)
			// Middle row.
			win = 1;
		else if (board[0][2]==player && 
			board[1][2]==player && 
			board[2][2]==player)
			// Bottom row.
			win = 1;
		else if (board[0][0]==player && 
			board[1][1]==player && 
			board[2][2]==player)
			// Upper left to Bottom right diagonal.
			win = 1;
		else if (board[2][0]==player && 
			board[1][1]==player && 
			board[0][2]==player)
			// Upper right to bottom left diagonal.
			win = 1;
	   else if (board[0][0]==player && 
		   board[0][1]==player && 
		   board[0][2]==player)
			// Left side column.
			win = 1;
		else if (board[1][0]==player && 
			board[1][1]==player && 
			board[1][2]==player)
			// Middle column.
			win = 1;
		else if (board[2][0]==player && 
			board[2][1]==player && 
			board[2][2]==player)
			// Right side column.
			win = 1;
		else
		{
			// Check to see if there are any empty spaces left. 
			// If not, the game is over.
			for(int row=0; row < 3; row++)
			{
				for(int col=0; col<3; col++)
				{
					if (board[col][row] == Empty)
					{
						// We still have moves to be made.
						win = 0;
						break;
					}
				}
			}
		}
		
		// Return the result.
		return win;
	}
	
	private void moveMade(int column, int row)
	{
		if (gameOver == 1)
		{
		 	// The game is over, and somebody won.

			JOptionPane.showMessageDialog(null, turn + " already won!!!", 
				"Winner", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// The game is over, and was a tie.
		else if (gameOver == 2)
		{
			JOptionPane.showMessageDialog(null, 
				"The game is already over, and nobody won!!!", 
				"No Winner", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Make sure click event was within the board.
		else if (column <= 2 && column >= 0 && row <= 2 && row >= 0)
		{
			System.out.println("GameOver=" + gameOver);
			if (board[column][row] != Empty)
			{
				JOptionPane.showMessageDialog(null, 
					"You must choose an empty square", 
					"Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				// Add the X or O to the board.
				board[column][row] = turn;
				
				// Since a move was made we need to refresh the display.
				repaint();

				gameOver = checkForWin(turn);
				
				// Somebody won.
				if (gameOver == 1)
				{
					JOptionPane.showMessageDialog(null, turn + " wins!!!", 
						"Winner", JOptionPane.INFORMATION_MESSAGE);
				}
				
				// The game ends as a tie.
				else if (gameOver == 2)
				{
					JOptionPane.showMessageDialog(null, 
						"Nobody wins, sorry!!!", 
						"Winner", JOptionPane.INFORMATION_MESSAGE);
				}

				// Keep playing.
				else
				{
					if (turn == X) turn = O; 
					else turn = X;
				}
			} // end else (spot clicked in was empty).
		} // end else if (click was in a legal spot).
	} // end moveMade method.
} // end class.