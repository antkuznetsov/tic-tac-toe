package tictactoe;

import java.util.Scanner;

public class Main {
	private static final int FIELD_SIZE = 3;
	private static final char[][] FIELD = new char[FIELD_SIZE][FIELD_SIZE];

	private static short tempX = 0;
	private static short tempO = 0;

	private static short xCounter = 0;
	private static short oCounter = 0;

	private static boolean thereIsEmptyCell = false;

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		createField("_".repeat(FIELD_SIZE * FIELD_SIZE).toCharArray());
		printField(FIELD);
		while (checkField(FIELD)) {
			move();
			printField(FIELD);
		}

		//old
		/*System.out.print("Enter cells: ");
		String line = scanner.nextLine();

		createField(line.toCharArray());

		printField(FIELD);

		move();

		printField(FIELD);

		checkField(FIELD);*/
	}

	public static Boolean checkField(char[][] field) {
		boolean xWins = false;
		boolean oWins = false;

		// check horizontal lines
		for (int i = 0; i < FIELD_SIZE; i++) {
			int tempX = 0;
			int tempO = 0;
			for (int j = 0; j < FIELD_SIZE; j++) {
				if (field[i][j] == 'X') {
					tempX++;
				} else if (field[i][j] == 'O') {
					tempO++;
				}

				if (tempX == FIELD_SIZE) {
					xWins = true;
				}

				if (tempO == FIELD_SIZE) {
					oWins = true;
				}
			}
		}

		// check vertical lines
		for (int i = 0; i < FIELD_SIZE; i++) {
			int tempX = 0;
			int tempO = 0;
			for (int j = 0; j < FIELD_SIZE; j++) {
				if (field[j][i] == 'X') {
					tempX++;
				} else if (field[j][i] == 'O') {
					tempO++;
				}

				if (tempX == FIELD_SIZE) {
					xWins = true;
				}

				if (tempO == FIELD_SIZE) {
					oWins = true;
				}
			}
		}

		// check diagonal 1
		tempX = 0;
		tempO = 0;
		for (int i = 0; i < FIELD_SIZE; i++) {
			if (field[i][i] == 'X') {
				tempX++;
			} else if (field[i][i] == 'O') {
				tempO++;
			}

			if (tempX == FIELD_SIZE) {
				xWins = true;
			}

			if (tempO == FIELD_SIZE) {
				oWins = true;
			}
		}

		// check diagonal 2
		tempX = 0;
		tempO = 0;
		for (int i = 0; i < FIELD_SIZE; i++) {
			int j = FIELD_SIZE - 1 - i;

			if (field[i][j] == 'X') {
				tempX++;
			} else if (field[i][j] == 'O') {
				tempO++;
			}

			if (tempX == FIELD_SIZE) {
				xWins = true;
			}

			if (tempO == FIELD_SIZE) {
				oWins = true;
			}
		}

		System.out.println();

		// get result
		if (xWins & !oWins) {
			System.out.println("X wins");
			return false;
		} else if (oWins & !xWins) {
			System.out.println("O wins");
			return false;
		} else if (xWins & oWins) {
			System.out.println("Impossible");
			return false;
		} else if (xCounter - oCounter > 1 || oCounter - xCounter > 1) {
			System.out.println("Impossible");
			return false;
		} else if (!thereIsEmptyCell) {
			System.out.println("Draw");
			return false;
		} else {
			//System.out.println("Game not finished");
			return true;
		}
	}

	public static void createField(char[] initLine) {
		for (int i = 0; i < FIELD_SIZE; i++) {
			for (int j = 0; j < FIELD_SIZE; j++) {
				FIELD[i][j] = initLine[FIELD_SIZE * i + j];
				if (FIELD[i][j] == '_') {
					FIELD[i][j] = ' ';
					thereIsEmptyCell = true;
				} else if (FIELD[i][j] == 'X') {
					xCounter++;
				} else if (FIELD[i][j] == 'O') {
					oCounter++;
				}
			}
		}
	}

	public static void printField(char[][] field) {
		System.out.println("---------");
		for (int i = 0; i < FIELD_SIZE; i++) {
			System.out.print("| ");
			for (int j = 0; j < FIELD_SIZE; j++) {
				System.out.print(field[i][j] + " ");
			}
			System.out.print("|\n");
		}
		System.out.println("---------");
	}

	public static void move() {
		while (true) {
			int i;
			int j;

			System.out.print("Enter the coordinates: ");

			if (scanner.hasNextInt()) {
				i = scanner.nextInt();
			} else {
				scanner.nextLine();
                System.out.println("You should enter numbers!");
				continue;
			}

			if (scanner.hasNextInt()) {
				j = scanner.nextInt();
			} else {
			    scanner.nextLine();
				System.out.println("You should enter numbers!");
				continue;
			}

			if (i > FIELD_SIZE || j > FIELD_SIZE) {
				System.out.println("Coordinates should be from 1 to " + FIELD_SIZE + "!");
			} else {
				int realI = 3 - j;
				int realJ = i - 1;

				if (FIELD[realI][realJ] == ' ') {
					FIELD[realI][realJ] = 'X';
					return;
				} else {
					System.out.println("This cell is occupied! Choose another one!");
				}
			}
		}
	}
}