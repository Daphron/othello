import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GUI extends JFrame implements ActionListener {
	private JPanel panel;
	private JButton[][] buttons;
	private Piece[][] board;
	private OthelloGame game;

	public GUI() {
		game = new OthelloGame();
		this.setBoard(game.getBoard());
		initUI();
		this.setVisible(true);
	}

	public GUI(Piece[][] board) {
		this.setBoard(board);
		initUI();
		this.setVisible(true);
	}

	public void initUI() {
		panel = new JPanel();

		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.setLayout(new GridLayout(8, 8, 5, 5));

		this.buttons = new JButton[8][8];

		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				if (this.board[i][j] != null) {
					this.buttons[i][j] = new JButton(
							this.board[i][j].toString());
					panel.add(this.buttons[i][j]);
				} else {
					this.buttons[i][j] = new JButton(".");
					panel.add(this.buttons[i][j]);
				}
				buttons[i][j].addActionListener(this);
				if (buttons[i][j].getText().equals("X"))
					buttons[i][j].setBackground(Color.BLACK);
				else if (buttons[i][j].getText().equals("O"))
					buttons[i][j].setBackground(Color.WHITE);
				else if (buttons[i][j].getText().equals("."))
					buttons[i][j].setBackground(Color.LIGHT_GRAY);
			}
			panel.setVisible(true);
		}

		add(panel);

		setTitle("Othello");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void setBoard(Piece[][] board) {
		this.board = board;
	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				if (buttons[i][j] == e.getSource()) {
					if (humanPart(i, j)) {
						computerPart();
					}
				}
			}
		}
	}

	public boolean humanPart(int i, int j) {
		boolean tf = false;
		if (this.game.humansTurn(i, j))
			tf = true;
		setBoard(game.getBoard());
		this.printBoardGUI();
		if (game.gameOver()) {
			System.out.println("The game has ended");
			System.out.println(game.determineWinner());
		}
		if (!game.humanCanMove() && !game.gameOver()) {
			System.out.println("You can't move! So it's the AI's turn now");
		}

		return tf;
	}

	public void computerPart() {
		Thread thread = new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
				game.computersTurn();
				setBoard(game.getBoard());
				printBoardGUI();
			}
		};
		thread.start();
		if (game.gameOver()) {
			System.out.println("The game has ended. ");
			System.out.println(game.determineWinner());
		}

	}

	public void printBoardGUI() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != null)
					buttons[i][j].setText(board[i][j].toString());
				if (buttons[i][j].getText().equals("X"))
					buttons[i][j].setBackground(Color.BLACK);
				else if (buttons[i][j].getText().equals("O"))
					buttons[i][j].setBackground(Color.WHITE);
			}
		}
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUI q = new GUI();
			}
		});
	}
}
