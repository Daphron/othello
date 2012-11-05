import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class TicTacToe extends JFrame
{
	TicTacToePanel game;
	JButton resetButton;
	
	public TicTacToe()
	{
		super("TicTacToe");
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 400);

		// Initialize the TicTacToePanel instance variable.
		game = new TicTacToePanel();

		// initialize the JButton instance variable.
		resetButton = new JButton("New Game");
		
		// Add a listener for the reset button.
		resetButton.addMouseListener( 
			new MouseAdapter() {
				public void mousePressed(MouseEvent e)
				{
					game.reset();
					repaint();
				}
		   }
		);

		// Add the components to the JFrame.
		getContentPane().add(game, BorderLayout.CENTER);
		getContentPane().add(resetButton, BorderLayout.EAST);
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		// Create an instance of this class to kick things off.
		new TicTacToe();
	}
}
