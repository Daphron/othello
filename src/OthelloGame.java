import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
//http://zetcode.com/tutorials/javaswingtutorial/swinglayoutmanagement/
import javax.swing.SwingUtilities;

public class OthelloGame extends TwoDGame {
	private Scanner keyboard;
	private JButton[][] buttons;
	private JPanel panel;
	private GUI myGUI;
	private int[][] oldboardValues = { { 50, -8, 5, 2, 2, 5, -8, 50 },
			{ -8, -16, 1, 1, 1, 1, -16, -8 }, { 5, 1, 1, 1, 1, 1, 1, 5 },
			{ 2, 1, 1, 0, 0, 1, 1, 2 }, { 2, 1, 1, 0, 0, 1, 1, 2 },
			{ 5, 1, 1, 1, 1, 1, 1, 5 }, { -8, -16, 1, 1, 1, 1, -16, -8 },
			{ 50, -8, 5, 2, 2, 5, -8, 50 } };
	private int[][] boardValues = { { 50, -8, 6, 4, 4, 6, -8, 50 },
			{ -8, -16, -3, -3, -3, -3, -16, -8 }, { 6, -3, 1, 1, 1, 1, -3, 6 },
			{ 4, -3, 1, 0, 0, 1, -3, 4 }, { 4, -3, 1, 0, 0, 1, -3, 4 },
			{ 6, -3, 1, 1, 1, 1, -3, 6 }, { -8, -16, -3, -3, -3, -3, -16, -8 },
			{ 50, -8, 6, 4, 4, 6, -8, 50 } };
	private int[][] forsightboardValues = { { 200, 6, 8, 6, 6, 8, 6, 200 },
			{ 6, 1, 1, 1, 1, 1, 1, 6 }, 
			{ 8, 1, 1, 1, 1, 1, 1, 8 },
			{ 6, 1, 1, 1, 1, 1, 1, 6 }, 
			{ 6, 1, 1, 1, 1, 1, 1, 6 },
			{ 8, 1, 1, 1, 1, 1, 1, 8 }, 
			{ 6, 1, 1, 1, 1, 1, 1, 6 },
			{ 200, 6, 8, 6, 6, 8, 6, 200 } };

	public OthelloGame() {
		super(8, 8);

		setPiece(3, 3, new OthelloPiece("X"));
		setPiece(4, 4, new OthelloPiece("X"));
		setPiece(3, 4, new OthelloPiece("O"));
		setPiece(4, 3, new OthelloPiece("O"));

		// initUI();
		// this.setVisible(true);

		this.keyboard = new Scanner(System.in);
		// this.myGUI = new GUI(board);
	}

	public boolean validMove(int r, int c, String player, Piece[][] board) {
		if (super.validMove(r, c, board)) {
			if (getPiece(r, c, board) != null)
				return false;

			int piecesToFlip = 0;

			// Checks all to the left.
			for (int i = c - 1; i >= 0; i--) {
				if (getPiece(r, i, board) == null)
					break;

				if (getPiece(r, i, board).toString().equals(player)
						&& piecesToFlip == 0)
					break;

				if (!(getPiece(r, i, board).toString().equals(player)))
					piecesToFlip++;

				else if (piecesToFlip > 0
						&& getPiece(r, i, board).toString().equals(player))
					return true;
			}
			piecesToFlip = 0;

			// Checks all to the right.
			for (int i = c + 1; i < numColumns(); i++) {
				if (getPiece(r, i, board) == null)
					break;

				if (getPiece(r, i, board).toString().equals(player)
						&& piecesToFlip == 0)
					break;

				if (!(getPiece(r, i, board).toString().equals(player)))
					piecesToFlip++;

				else if (piecesToFlip > 0
						&& getPiece(r, i, board).toString().equals(player))
					return true;
			}
			piecesToFlip = 0;

			// Checks all upwards.
			for (int i = r - 1; i >= 0; i--) {
				if (getPiece(i, c, board) == null)
					break;

				if (getPiece(i, c, board).toString().equals(player)
						&& piecesToFlip == 0)
					break;

				if (!(getPiece(i, c, board).toString().equals(player)))
					piecesToFlip++;

				else if (piecesToFlip > 0
						&& getPiece(i, c, board).toString().equals(player))
					return true;
			}
			piecesToFlip = 0;

			// Checks all downwards.
			for (int i = r + 1; i < numRows(); i++) {
				if (getPiece(i, c, board) == null)
					break;

				if (getPiece(i, c, board).toString().equals(player)
						&& piecesToFlip == 0)
					break;

				if (!(getPiece(i, c, board).toString().equals(player)))
					piecesToFlip++;

				else if (piecesToFlip > 0
						&& getPiece(i, c, board).toString().equals(player))
					return true;
			}
			piecesToFlip = 0;

			// Checks all down-right.
			int i = c + 1;
			int j = r + 1;
			while (i < numColumns() && j < numRows()) {
				if (getPiece(j, i, board) == null)
					break;

				if (getPiece(j, i, board).toString().equals(player)
						&& piecesToFlip == 0)
					break;

				if (!(getPiece(j, i, board).toString().equals(player)))
					piecesToFlip++;

				else if (piecesToFlip > 0
						&& getPiece(j, i, board).toString().equals(player))
					return true;

				i++;
				j++;
			}
			piecesToFlip = 0;

			// Checks all down-left.
			i = c - 1;
			j = r + 1;
			while (i >= 0 && j < numRows()) {
				if (getPiece(j, i, board) == null)
					break;

				if (getPiece(j, i, board).toString().equals(player)
						&& piecesToFlip == 0)
					break;

				if (!(getPiece(j, i, board).toString().equals(player)))
					piecesToFlip++;

				else if (piecesToFlip > 0
						&& getPiece(j, i, board).toString().equals(player))
					return true;

				i--;
				j++;
			}
			piecesToFlip = 0;

			// Checks all up-right.
			i = c + 1;
			j = r - 1;
			while (i < numColumns() && j >= 0) {
				if (getPiece(j, i, board) == null)
					break;

				if (getPiece(j, i, board).toString().equals(player)
						&& piecesToFlip == 0)
					break;

				if (!(getPiece(j, i, board).toString().equals(player)))
					piecesToFlip++;

				else if (piecesToFlip > 0
						&& getPiece(j, i, board).toString().equals(player))
					return true;

				i++;
				j--;
			}
			piecesToFlip = 0;

			// Checks all up-left.
			i = c - 1;
			j = r - 1;
			while (i >= 0 && j >= 0) {
				if (getPiece(j, i, board) == null)
					break;

				if (getPiece(j, i, board).toString().equals(player)
						&& piecesToFlip == 0)
					break;

				if (!(getPiece(j, i, board).toString().equals(player)))
					piecesToFlip++;

				else if (piecesToFlip > 0
						&& getPiece(j, i, board).toString().equals(player))
					return true;

				i--;
				j--;
			}
			piecesToFlip = 0;
		}
		return false;
	}

	public boolean validMove(int r, int c, String player) {
		return validMove(r, c, player, this.getBoard());
	}

	public boolean gameOver(Piece[][] board) {
		int countX = 0;
		int countO = 0;

		for (int r = 0; r < numRows(); r++) {
			for (int c = 0; c < numColumns(); c++) {
				if (validMove(r, c, "X", board))
					countX++;
				if (validMove(r, c, "O", board))
					countO++;
			}
		}
		return (countX == 0 || countO == 0);
	}

	public boolean gameOver() {
		return gameOver(this.getBoard());
	}

	public boolean humanCanMove(Piece[][] board) {
		for (int r = 0; r < numRows(); r++) {
			for (int c = 0; c < numColumns(); c++) {
				if (validMove(r, c, "X"))
					return true;
			}
		}
		return false;
	}

	public boolean humanCanMove() {
		return humanCanMove(this.getBoard());
	}

	public boolean humansTurn(int row, int column, Piece[][] board) {
		if (validMove(row, column, "X")) {
			setPiece(row, column, new OthelloPiece("X"));
			updateBoard(row, column, "X");
			printBoard();
			return true;
		} else
			return false;
	}

	public boolean humansTurn(int row, int column) {
		return humansTurn(row, column, this.getBoard());
	}

	public int userInputRow() {
		System.out
				.print("Please enter the row number where you would like to place your piece: ");
		return this.keyboard.nextInt();
	}

	public int userInputColumn() {
		System.out
				.print("Please enter the column number where  you would like to place your piece: ");
		return this.keyboard.nextInt();
	}

	public void computersTurn() {
		System.out.println("Computer's turn.");
		// randAI();
		// oneMoveAI();
		// strategicOneMoveAI(); // Use move set 1 or 2 for beter play
		nForsightAI(3); // 6 gets really slow, 4 is fast enough to play vs.

		printBoard();
	}

	public void randAI() {
		Random rocky = new Random();

		int row = rocky.nextInt(8);
		int column = rocky.nextInt(8);

		while (!validMove(row, column, "O")) {
			// System.out.println("Move at " + "(" + (row + 1) + ", " + (column
			// + 1) + ")" + "is not valid. Trying again...");
			row = rocky.nextInt(8);
			column = rocky.nextInt(8);
		}

		System.out.println("Placing piece at " + "(" + (row + 1) + ", "
				+ (column + 1) + ")...");
		setPiece(row, column, new OthelloPiece("O"));

		updateBoard(row, column, "O");
	}

	public void oneMoveAI() {
		int[][] moveValues = new int[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (validMove(i, j, "O")) {
					moveValues[i][j] = getNumFlipped(i, j, "O");
				} else {
					moveValues[i][j] = 0;
				}
			}
		}
		int rMove = 0;
		int cMove = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (moveValues[i][j] > moveValues[rMove][cMove]) {
					rMove = i;
					cMove = j;

				}
			}
		}
		setPiece(rMove, cMove, new OthelloPiece("O"));

		updateBoard(rMove, cMove, "O");
	}

	public void oneMoveAI2() {
		int[][] moveValues = new int[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (validMove(i, j, "X")) {
					moveValues[i][j] = getNumFlipped(i, j, "X");
				} else {
					moveValues[i][j] = 0;
				}
			}
		}
		int rMove = 0;
		int cMove = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (moveValues[i][j] > moveValues[rMove][cMove]) {
					rMove = i;
					cMove = j;

				}
			}
		}
		setPiece(rMove, cMove, new OthelloPiece("X"));

		updateBoard(rMove, cMove, "X");
	}

	public void strategicOneMoveAI() {

		int[][] moveValues = new int[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (validMove(i, j, "O")) {
					// System.out.println(getNumFlipped(i,j,"O"));
					moveValues[i][j] = getNumFlipped(i, j, "O")
							+ boardValues[i][j];
				} else {
					moveValues[i][j] = -10000;
				}
			}
		}
		int rMove = 0;
		int cMove = 0;
		ArrayList<Integer> rMoves = new ArrayList<Integer>();
		ArrayList<Integer> cMoves = new ArrayList<Integer>();
		rMoves.add(0);
		cMoves.add(0);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (validMove(i, j, "O")) {
					if (moveValues[i][j] > moveValues[rMove][cMove]) {
						rMoves.clear();
						cMoves.clear();
						rMove = i;
						cMove = j;
						rMoves.add(i);
						cMoves.add(j);
					} else if (moveValues[i][j] == moveValues[rMove][cMove]) {
						rMoves.add(i);
						cMoves.add(j);
					}
				}
			}
		}

		Random rocky = new Random();
		int move = rocky.nextInt(rMoves.size());

		rMove = rMoves.get(move);
		cMove = cMoves.get(move);

		printAList(rMoves);
		printAList(cMoves);

		printArray(moveValues);
		System.out.println("\nrMove = " + rMove + ", " + cMove);

		if (validMove(rMove, cMove, "O")) {
			setPiece(rMove, cMove, new OthelloPiece("O"));

			updateBoard(rMove, cMove, "O");
		} else {
			System.out.println("Computer can not move, make your move");
		}

	}

	public int[][] nForsightAI(int n, String color, Piece[][] board) {
		// Returns the move values that IT would use
		int[][] moveValues = new int[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				if (n <= 0) {
					if (validMove(i, j, color, board)) {
						moveValues[i][j] = this.boardValues[i][j];
						// getNumFlipped(i, j, color, board) used to also be added
						ArrayList<Integer> rLocs;
						ArrayList<Integer> cLocs;
						ArrayList<ArrayList<Integer>> locs = getFlippedLocales(i, j, color, board);
						for(int z = 0; z < locs.get(0).size(); z++)
						{
							moveValues[i][j] += 2 * (boardValues[locs.get(0).get(z)][locs.get(1).get(z)]);
						}
					} else {
						moveValues[i][j] = -10000;
					}
				} else {
					if (validMove(i, j, color, board)) {
						moveValues[i][j] = + this.boardValues[i][j];
						//getNumFlipped(i, j, color, board) was there before
						ArrayList<Integer> rLocs;
						ArrayList<Integer> cLocs;
						ArrayList<ArrayList<Integer>> locs = getFlippedLocales(i, j, color, board);
						for(int z = 0; z < locs.get(0).size(); z++)
						{
							moveValues[i][j] += 2 * (boardValues[locs.get(0).get(z)][locs.get(1).get(z)]);
						}
						
						Piece[][] vBoard = new Piece[8][8];
						for (int k = 0; k < 8; k++)
							for (int h = 0; h < 8; h++) {
								if (board[k][h] != null
										&& board[k][h].toString().equals("O"))
									setPiece(k, h, new OthelloPiece("O"),
											vBoard);
								else if (board[k][h] != null
										&& board[k][h].toString().equals("X"))
									setPiece(k, h, new OthelloPiece("X"),
											vBoard);
							}
						setPiece(i, j, new OthelloPiece(color), vBoard);
						updateBoard(i, j, color, vBoard);
						// minus because it's the best move for opponent
						moveValues[i][j] -= maxElement(nForsightAI(n - 1,
								flipColor(color), vBoard));
					} else {
						moveValues[i][j] = -10000;
					}
				}
			}
		}
		return moveValues;

	}

	public void nForsightAI(int n) {
		int[][] moveValues = new int[8][8];
		Piece[][] vBoard = new Piece[8][8];
		for (int k = 0; k < 8; k++)
			for (int h = 0; h < 8; h++)
				vBoard[k][h] = getBoard()[k][h];
		moveValues = nForsightAI(n, "O", vBoard);

		// Now we choose our move based on the moveValues
		int rMove = 0;
		int cMove = 0;
		ArrayList<Integer> rMoves = new ArrayList<Integer>();
		ArrayList<Integer> cMoves = new ArrayList<Integer>();
		rMoves.add(0);
		cMoves.add(0);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (validMove(i, j, "O")) {
					if (moveValues[i][j] > moveValues[rMove][cMove]) {
						rMoves.clear();
						cMoves.clear();
						rMove = i;
						cMove = j;
						rMoves.add(i);
						cMoves.add(j);
					} else if (moveValues[i][j] == moveValues[rMove][cMove]) {
						rMoves.add(i);
						cMoves.add(j);
					}
				}
			}
		}

		Random rocky = new Random();
		int move = rocky.nextInt(rMoves.size());

		rMove = rMoves.get(move);
		cMove = cMoves.get(move);

		printAList(rMoves);
		printAList(cMoves);

		printArray(moveValues);
		System.out.println("rMove = " + rMove + ", " + cMove);

		if (validMove(rMove, cMove, "O")) {
			setPiece(rMove, cMove, new OthelloPiece("O"));

			updateBoard(rMove, cMove, "O");
		} else {
			System.out.println("Computer can not move, make your move");
		}

	}

	public void updateBoard(int r, int c, String player) {
		updateBoard(r, c, player, this.getBoard());
	}

	public void updateBoard(int r, int c, String player, Piece[][] board) {
		ArrayList<Piece> flippingPieces = new ArrayList<Piece>();

		// Checks all to the left.
		for (int i = c - 1; i >= 0; i--) {
			if (getPiece(r, i, board) == null)
				break;

			if (getPiece(r, i, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(r, i, board).toString().equals(player)))
				flippingPieces.add(getPiece(r, i, board));

			else if (flippingPieces.size() > 0
					&& getPiece(r, i, board).toString().equals(player)) {
				flipPieces(flippingPieces);
				break;
			}
		}
		flippingPieces.clear();

		// Checks all to the right.
		for (int i = c + 1; i < numColumns(); i++) {
			if (getPiece(r, i, board) == null)
				break;

			if (getPiece(r, i, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(r, i, board).toString().equals(player)))
				flippingPieces.add(getPiece(r, i, board));

			else if (flippingPieces.size() > 0
					&& getPiece(r, i, board).toString().equals(player)) {
				flipPieces(flippingPieces);
				break;
			}
		}
		flippingPieces.clear();

		// Checks all upwards.
		for (int i = r - 1; i >= 0; i--) {
			if (getPiece(i, c, board) == null)
				break;

			if (getPiece(i, c, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(i, c, board).toString().equals(player)))
				flippingPieces.add(getPiece(i, c, board));

			else if (flippingPieces.size() > 0
					&& getPiece(i, c, board).toString().equals(player)) {
				flipPieces(flippingPieces);
				break;
			}
		}
		flippingPieces.clear();

		// Checks all downwards.
		for (int i = r + 1; i < numRows(); i++) {
			if (getPiece(i, c, board) == null)
				break;

			if (getPiece(i, c, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(i, c, board).toString().equals(player)))
				flippingPieces.add(getPiece(i, c, board));

			else if (flippingPieces.size() > 0
					&& getPiece(i, c, board).toString().equals(player)) {
				flipPieces(flippingPieces);
				break;
			}
		}
		flippingPieces.clear();

		// Checks all down-right.
		int i = c + 1;
		int j = r + 1;
		while (i < numColumns() && j < numRows()) {
			if (getPiece(j, i, board) == null)
				break;

			if (getPiece(j, i, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(j, i, board).toString().equals(player)))
				flippingPieces.add(getPiece(j, i, board));

			else if (flippingPieces.size() > 0
					&& getPiece(j, i, board).toString().equals(player)) {
				flipPieces(flippingPieces);
				break;
			}

			i++;
			j++;
		}
		flippingPieces.clear();

		// Checks all down-left.
		i = c - 1;
		j = r + 1;
		while (i >= 0 && j < numRows()) {
			if (getPiece(j, i, board) == null)
				break;

			if (getPiece(j, i, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(j, i, board).toString().equals(player)))
				flippingPieces.add(getPiece(j, i, board));

			else if (flippingPieces.size() > 0
					&& getPiece(j, i, board).toString().equals(player)) {
				flipPieces(flippingPieces);
				break;
			}

			i--;
			j++;
		}
		flippingPieces.clear();

		// Checks all up-right.
		i = c + 1;
		j = r - 1;
		while (i < numColumns() && j >= 0) {
			if (getPiece(j, i, board) == null)
				break;

			if (getPiece(j, i, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(j, i, board).toString().equals(player)))
				flippingPieces.add(getPiece(j, i, board));

			else if (flippingPieces.size() > 0
					&& getPiece(j, i, board).toString().equals(player)) {
				flipPieces(flippingPieces);
				break;
			}

			i++;
			j--;
		}
		flippingPieces.clear();

		// Checks all up-left.
		i = c - 1;
		j = r - 1;
		while (i >= 0 && j >= 0) {
			if (getPiece(j, i, board) == null)
				break;

			if (getPiece(j, i, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(j, i, board).toString().equals(player)))
				flippingPieces.add(getPiece(j, i, board));

			else if (flippingPieces.size() > 0
					&& getPiece(j, i, board).toString().equals(player)) {
				flipPieces(flippingPieces);
				break;
			}

			i--;
			j--;
		}
		flippingPieces.clear();
	}

	public int getNumFlipped(int r, int c, String player, Piece[][] board) {
		ArrayList<Piece> flippingPieces = new ArrayList<Piece>();
		int numToFlip = 0;

		// Checks all to the left.
		for (int i = c - 1; i >= 0; i--) {
			if (getPiece(r, i, board) == null)
				break;

			if (getPiece(r, i, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(r, i, board).toString().equals(player)))
				flippingPieces.add(getPiece(r, i, board));

			else if (flippingPieces.size() > 0
					&& getPiece(r, i, board).toString().equals(player)) {
				numToFlip += flippingPieces.size();
				break;
			}
		}
		flippingPieces.clear();

		// Checks all to the right.
		for (int i = c + 1; i < numColumns(); i++) {
			if (getPiece(r, i, board) == null)
				break;

			if (getPiece(r, i, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(r, i, board).toString().equals(player)))
				flippingPieces.add(getPiece(r, i, board));

			else if (flippingPieces.size() > 0
					&& getPiece(r, i, board).toString().equals(player)) {
				numToFlip += flippingPieces.size();
				break;
			}
		}
		flippingPieces.clear();

		// Checks all upwards.
		for (int i = r - 1; i >= 0; i--) {
			if (getPiece(i, c, board) == null)
				break;

			if (getPiece(i, c, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(i, c, board).toString().equals(player)))
				flippingPieces.add(getPiece(i, c, board));

			else if (flippingPieces.size() > 0
					&& getPiece(i, c, board).toString().equals(player)) {
				numToFlip += flippingPieces.size();
				break;
			}
		}
		flippingPieces.clear();

		// Checks all downwards.
		for (int i = r + 1; i < numRows(); i++) {
			if (getPiece(i, c, board) == null)
				break;

			if (getPiece(i, c, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(i, c, board).toString().equals(player)))
				flippingPieces.add(getPiece(i, c, board));

			else if (flippingPieces.size() > 0
					&& getPiece(i, c, board).toString().equals(player)) {
				numToFlip += flippingPieces.size();
				break;
			}
		}
		flippingPieces.clear();

		// Checks all down-right.
		int i = c + 1;
		int j = r + 1;
		while (i < numColumns() && j < numRows()) {
			if (getPiece(j, i, board) == null)
				break;

			if (getPiece(j, i, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(j, i, board).toString().equals(player)))
				flippingPieces.add(getPiece(j, i, board));

			else if (flippingPieces.size() > 0
					&& getPiece(j, i, board).toString().equals(player)) {
				numToFlip += flippingPieces.size();
				break;
			}

			i++;
			j++;
		}
		flippingPieces.clear();

		// Checks all down-left.
		i = c - 1;
		j = r + 1;
		while (i >= 0 && j < numRows()) {
			if (getPiece(j, i, board) == null)
				break;

			if (getPiece(j, i, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(j, i, board).toString().equals(player))) {
				flippingPieces.add(getPiece(j, i, board));
			}

			else if (flippingPieces.size() > 0
					&& getPiece(j, i, board).toString().equals(player)) {
				numToFlip += flippingPieces.size();
				break;
			}

			i--;
			j++;
		}
		flippingPieces.clear();

		// Checks all up-right.
		i = c + 1;
		j = r - 1;
		while (i < numColumns() && j >= 0) {
			if (getPiece(j, i, board) == null)
				break;

			if (getPiece(j, i, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(j, i, board).toString().equals(player)))
				flippingPieces.add(getPiece(j, i, board));

			else if (flippingPieces.size() > 0
					&& getPiece(j, i, board).toString().equals(player)) {
				numToFlip += flippingPieces.size();
				break;
			}

			i++;
			j--;
		}
		flippingPieces.clear();

		// Checks all up-left.
		i = c - 1;
		j = r - 1;
		while (i >= 0 && j >= 0) {
			if (getPiece(j, i, board) == null)
				break;

			if (getPiece(j, i, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(j, i, board).toString().equals(player)))
				flippingPieces.add(getPiece(j, i, board));

			else if (flippingPieces.size() > 0
					&& getPiece(j, i, board).toString().equals(player)) {
				numToFlip += flippingPieces.size();
				break;
			}

			i--;
			j--;
		}
		flippingPieces.clear();
		return numToFlip;
	}

	public int getNumFlipped(int r, int c, String player) {
		return getNumFlipped(r, c, player, this.getBoard());
	}

	public void flipPieces(ArrayList<Piece> p) {
		for (Piece q : p) {
			OthelloPiece x = (OthelloPiece) q;
			x.flip();
		}
	}

	public String determineWinner(Piece[][] board) {
		int x = 0;
		int y = 0;

		for (Piece[] row : getBoard()) {
			for (Piece p : row) {
				if (p != null && p.toString().equals("X"))
					x++;
				if (p != null && p.toString().equals("O"))
					y++;
			}
		}
		System.out.println("X has " + x + " stones.");
		System.out.println("O has " + y + " stones.");

		if (x > y)
			return "X";
		else if (y > x)
			return "O";
		else
			return "T";
	}

	public String determineWinner() {
		return determineWinner(this.getBoard());
	}

	public void printBoard() {
		super.printBoard();
	}

	public void printBoard(Piece[][] board) {
		super.printBoard(board);
	}

	public void printArray(int[][] arr) {
		for (int i = 0; i < getBoard().length; i++) {
			System.out.println("");
			for (int j = 0; j < getBoard()[i].length; j++) {
				if (arr[i][j] != -10000)
					System.out.print("\t" + arr[i][j]);
				else
					System.out.print("\t" + getBoard()[i][j]);
			}
		}
	}

	public void printAList(ArrayList<Integer> arr) {
		System.out.println();
		for (int i : arr) {
			System.out.print("\t" + i);
		}
	}

	public int maxElement(int[][] array) {
		int max = -10000;
		for (int[] i : array)
			for (int j : i)
				max = Math.max(max, j);
		if (max == -10000)
			return 0;
		return max;
	}

	public String flipColor(String s) {
		if (s.equals("X"))
			return "O";
		else
			return "X";
	}

	public ArrayList<ArrayList<Integer>> getFlippedLocales(int r, int c, String player, Piece[][] board) {
		ArrayList<Piece> flippingPieces = new ArrayList<Piece>();
		ArrayList<Integer> rLocs = new ArrayList<Integer>();
		ArrayList<Integer> cLocs = new ArrayList<Integer>();
		int numToFlip = 0;
		ArrayList<Integer> tmprLocs = new ArrayList<Integer>();
		ArrayList<Integer> tmpcLocs = new ArrayList<Integer>();

		// Checks all to the left.
		for (int i = c - 1; i >= 0; i--) {
			if (getPiece(r, i, board) == null)
				break;

			if (getPiece(r, i, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(r, i, board).toString().equals(player))) {
				flippingPieces.add(getPiece(r, i, board));
				tmprLocs.add(r);
				tmpcLocs.add(i);
			}

			else if (flippingPieces.size() > 0
					&& getPiece(r, i, board).toString().equals(player)) {
				numToFlip += flippingPieces.size();
				for(int z=0; z < tmprLocs.size(); z++)
				{
					rLocs.add(tmprLocs.get(z));
					cLocs.add(tmpcLocs.get(z));
					rLocs.clear();
					cLocs.clear();
				}
				break;
			}
		}
		flippingPieces.clear();

		// Checks all to the right.
		for (int i = c + 1; i < numColumns(); i++) {
			if (getPiece(r, i, board) == null)
				break;

			if (getPiece(r, i, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(r, i, board).toString().equals(player))) {
				flippingPieces.add(getPiece(r, i, board));
				tmprLocs.add(r);
				tmpcLocs.add(i);
			} else if (flippingPieces.size() > 0
					&& getPiece(r, i, board).toString().equals(player)) {
								for(int z=0; z < tmprLocs.size(); z++)
				{
					rLocs.add(tmprLocs.get(z));
					cLocs.add(tmpcLocs.get(z));
					rLocs.clear();
					cLocs.clear();
				}
				break;
			}
		}
		flippingPieces.clear();

		// Checks all upwards.
		for (int i = r - 1; i >= 0; i--) {
			if (getPiece(i, c, board) == null)
				break;

			if (getPiece(i, c, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(i, c, board).toString().equals(player))) {
				flippingPieces.add(getPiece(i, c, board));
				tmprLocs.add(i);
				tmpcLocs.add(c);
			}

			else if (flippingPieces.size() > 0
					&& getPiece(i, c, board).toString().equals(player)) {
								for(int z=0; z < tmprLocs.size(); z++)
				{
					rLocs.add(tmprLocs.get(z));
					cLocs.add(tmpcLocs.get(z));
					rLocs.clear();
					cLocs.clear();
				}
				break;
			}
		}
		flippingPieces.clear();

		// Checks all downwards.
		for (int i = r + 1; i < numRows(); i++) {
			if (getPiece(i, c, board) == null)
				break;

			if (getPiece(i, c, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(i, c, board).toString().equals(player))) {
				flippingPieces.add(getPiece(i, c, board));
				tmprLocs.add(i);
				tmpcLocs.add(c);
			}

			else if (flippingPieces.size() > 0
					&& getPiece(i, c, board).toString().equals(player)) {
								for(int z=0; z < tmprLocs.size(); z++)
				{
					rLocs.add(tmprLocs.get(z));
					cLocs.add(tmpcLocs.get(z));
					rLocs.clear();
					cLocs.clear();
				}
				break;
			}
		}
		flippingPieces.clear();

		// Checks all down-right.
		int i = c + 1;
		int j = r + 1;
		while (i < numColumns() && j < numRows()) {
			if (getPiece(j, i, board) == null)
				break;

			if (getPiece(j, i, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(j, i, board).toString().equals(player))) {
				flippingPieces.add(getPiece(j, i, board));
				tmprLocs.add(j);
				tmpcLocs.add(i);
			}

			else if (flippingPieces.size() > 0
					&& getPiece(j, i, board).toString().equals(player)) {
								for(int z=0; z < tmprLocs.size(); z++)
				{
					rLocs.add(tmprLocs.get(z));
					cLocs.add(tmpcLocs.get(z));
					rLocs.clear();
					cLocs.clear();
				}
				break;
			}

			i++;
			j++;
		}
		flippingPieces.clear();

		// Checks all down-left.
		i = c - 1;
		j = r + 1;
		while (i >= 0 && j < numRows()) {
			if (getPiece(j, i, board) == null)
				break;

			if (getPiece(j, i, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(j, i, board).toString().equals(player))) {
				flippingPieces.add(getPiece(j, i, board));
				tmprLocs.add(j);
				tmpcLocs.add(i);
			}

			else if (flippingPieces.size() > 0
					&& getPiece(j, i, board).toString().equals(player)) {
								for(int z=0; z < tmprLocs.size(); z++)
				{
					rLocs.add(tmprLocs.get(z));
					cLocs.add(tmpcLocs.get(z));
					rLocs.clear();
					cLocs.clear();
				}
				break;
			}

			i--;
			j++;
		}
		flippingPieces.clear();

		// Checks all up-right.
		i = c + 1;
		j = r - 1;
		while (i < numColumns() && j >= 0) {
			if (getPiece(j, i, board) == null)
				break;

			if (getPiece(j, i, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(j, i, board).toString().equals(player))) {
				flippingPieces.add(getPiece(j, i, board));
				tmprLocs.add(j);
				tmpcLocs.add(i);
			}

			else if (flippingPieces.size() > 0
					&& getPiece(j, i, board).toString().equals(player)) {
								for(int z=0; z < tmprLocs.size(); z++)
				{
					rLocs.add(tmprLocs.get(z));
					cLocs.add(tmpcLocs.get(z));
					rLocs.clear();
					cLocs.clear();
				}
				break;
			}

			i++;
			j--;
		}
		flippingPieces.clear();

		// Checks all up-left.
		i = c - 1;
		j = r - 1;
		while (i >= 0 && j >= 0) {
			if (getPiece(j, i, board) == null)
				break;

			if (getPiece(j, i, board).toString().equals(player)
					&& flippingPieces.size() == 0)
				break;

			if (!(getPiece(j, i, board).toString().equals(player))) {
				flippingPieces.add(getPiece(j, i, board));
				tmprLocs.add(j);
				tmpcLocs.add(i);
			}

			else if (flippingPieces.size() > 0
					&& getPiece(j, i, board).toString().equals(player)) {
								for(int z=0; z < tmprLocs.size(); z++)
				{
					rLocs.add(tmprLocs.get(z));
					cLocs.add(tmpcLocs.get(z));
					rLocs.clear();
					cLocs.clear();
				}
				break;
			}

			i--;
			j--;
		}
		flippingPieces.clear();
		
		ArrayList<ArrayList<Integer>> returned = new ArrayList<ArrayList<Integer>>();
		returned.add(rLocs);
		returned.add(cLocs);
		return returned;
	}
}
