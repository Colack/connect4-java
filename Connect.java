import java.util.Scanner;

public class Connect {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;
        String piece = "X";

        String[][] board = new String[6][7];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = " ";
            }
        }

        while (!gameOver) {
            clearScreen();
            printBoard(board);

            int userInput = getUserInput(scanner, piece);

            placePiece(board, userInput, piece);

            gameOver = isGameOver(board);

            if (piece.equals("X")) {
                piece = "O";
            } else {
                piece = "X";
            }
        }

        // Clear the screen to make the game over screen look nicer
        clearScreen();

        // Print the final results
        System.out.println("Game Over!");
        System.out.println("Here are the final results:");
        if (isDraw(board)) {
            System.out.println("It's a draw!");
        } else {
            if (piece.equals("X")) {
                System.out.println("O wins!");
            } else {
                System.out.println("X wins!");
            }
        }
        // Print the final board
        printBoard(board);

        // Print the credits, and a blatantly obvious plug for my website and my github
        System.out.println("Thanks for playing!");
        System.out.println("Created by Jack");
        System.out.println("If you want to see more of my work, check out my github: https://github.com/colack");
    }

    /**
     * Prints the current board.
     * @param board
     */
    public static void printBoard(String[][] board) {
        /*
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        */

        // Remake the previous code, except with a border around the board.
        // Also try to make the board centered on the terminal

        for (int i = 0; i < 6; i++) {
            System.out.print("|");
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("|");
        }
    }

    /**
     * Gets users input and returns it.
     * @param scanner
     * @param piece
     * @return The user's input
     */
    public static int getUserInput(Scanner scanner, String piece) {
        System.out.println("\n\n" + piece + " enter a number between 1 and 7");
        int userInput = scanner.nextInt();
        while (userInput < 1 || userInput > 7) {
            if (userInput < 1 || userInput > 7) {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
            }
            userInput = scanner.nextInt();
        }
        return userInput;
    }

    /**
     * Places a piece on the board
     * @param board
     * @param userInput
     * @param piece
     */
    public static void placePiece(String[][] board, int userInput, String piece) {
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][userInput - 1].equals(" ")) {
                board[i][userInput - 1] = piece;
                break;
            }
        }
    }

    /**
     * Checks if the game is over.
     * @param board
     * @return True if the game is over, false if it is not.
     */
    public static boolean isGameOver(String[][] board) {
        if (isWinner(board)) {
            System.out.println("We have a winner!");
            return true;
        } else if (isDraw(board)) {
            System.out.println("It's a draw!");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if there is a winner
     * @param board
     * @return True if there is a winner, false if there is not.
     */
    public static boolean isWinner(String[][] board) {
        return isVerticalWin(board) || isHorizontalWin(board) || isDiagonalWin(board);
    }

    /**
     * Checks if there is a draw
     * @param board
     * @return True if there is a draw, false if there is not.
     */
    public static boolean isDraw(String[][] board) {
        // Loop through the board and check if there are any empty spaces
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if there is a vertical win
     * @param board
     * @return True if there is a vertical win, false if there is not.
     */
    public static boolean isVerticalWin(String[][] board) {
        // Loop through the board and check if there are 4 in a row vertically
        for (int i = 0; i < board.length - 3; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!board[i][j].equals(" ") && board[i][j].equals(board[i + 1][j]) && board[i][j].equals(board[i + 2][j]) && board[i][j].equals(board[i + 3][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if there is a horizontal win
     * @param board
     * @return True if there is a horizontal win, false if there is not.
     */
    public static boolean isHorizontalWin(String[][] board) {
        // Loop through the board and check if there are 4 in a row horizontally
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length - 3; j++) {
                if (!board[i][j].equals(" ") && board[i][j].equals(board[i][j + 1]) && board[i][j].equals(board[i][j + 2]) && board[i][j].equals(board[i][j + 3])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if there is a diagonal win
     * @param board
     * @return True if there is a diagonal win, false if there is not.
     */
    public static boolean isDiagonalWin(String[][] board) {
        // First, check the top left to bottom right diagonals
        for (int i = 0; i < board.length - 3; i++) {
            for (int j = 0; j < board[i].length - 3; j++) {
                if (!board[i][j].equals(" ") && board[i][j].equals(board[i + 1][j + 1]) && board[i][j].equals(board[i + 2][j + 2]) && board[i][j].equals(board[i + 3][j + 3])) {
                    return true;
                }
            }
        }
        // Then, check the top right to bottom left diagonals
        for (int i = 0; i < board.length - 3; i++) {
            for (int j = 3; j < board[i].length; j++) {
                if (!board[i][j].equals(" ") && board[i][j].equals(board[i + 1][j - 1]) && board[i][j].equals(board[i + 2][j - 2]) && board[i][j].equals(board[i + 3][j - 3])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Clears the screen
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
