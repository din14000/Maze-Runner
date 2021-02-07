package mazerunner;

import java.util.*;

public class MazeRunner {
	static Maze myMap = new Maze();
	static Scanner scanner = new Scanner(System.in);
	static int moves = 0;

	public static void main(String[] args) {
		intro();
		while (!myMap.didIWin() && moves < 100) {
			movesMessage(moves);
			try {
				userMover(userMove());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (moves < 100) {
			System.out.println("YOU WIN");
			System.out.println("and you did it in" + moves + "moves");
		}

		else if (moves >= 100)
			System.out.println("Sorry, but you didn't escape in time - YOU LOSE!");

	}

	public static void intro() {
		// Welcome user to the game and print the new map
		System.out.println("Welcome to Maze Runner!");
		myMap.printMap();

	}

	private static char userMove() {
		char input = '*'; // initiating with starlet (*) indicates that this is the first time the user is
		// trying to answer

		boolean isWrongInput = true;
		while (isWrongInput) {

			if (input != '*' && isWrongInput)
				System.out.println("Wrong input");
			System.out.println("Where would you like to move? (R, L, U, D)");
			input = scanner.next().toUpperCase().charAt(0);
			isWrongInput = (input != 'R' && input != 'L' && input != 'U' && input != 'D');

		}
		moves++;
		return input;

	}

	private static void userMover(char dir) throws Exception {
		if (dir == 'R' && myMap.canIMoveRight()) // Returns true if the space to the right is free, false if there is a
													// wall
			myMap.moveRight();
		else if (dir == 'L' && myMap.canIMoveLeft()) // Returns true if the space to the left is free, false if
														// there is a wall
			myMap.moveLeft();
		else if (dir == 'U' && myMap.canIMoveUp()) // Returns true if the space above is free, false if there is a
													// wall
			myMap.moveUp();
		else if (dir == 'D' && myMap.canIMoveDown()) // Returns true if the space below is free, false if there is a
														// wall
			myMap.moveDown();
		else
			navigatePit('R');

	}

	private static void movesMessage(int moves) {
		switch (moves) {
		case 50:
			System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes.");
			return;
		case 75:
			System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
			return;
		case 90:
			System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
			return;
		case 100:
			System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
			return;
		}
	}

	private static void navigatePit(char dir) {
		String s = String.valueOf(dir); // change Char to String for the built-in pit methods
		String input = "No";
		if (!myMap.isThereAPit(s))
			System.out.println("Sorry, you’ve hit a wall.");
		else {
				System.out.println("Watch out! There's a pit ahead, jump it?");
				input = scanner.next();
				if (input.toUpperCase().charAt(0) == 'Y')
					myMap.jumpOverPit(s);
				else return;
			
		}
	}

}
