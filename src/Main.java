import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // TIC TAC TOE

        // TODO:    DONE    3X3 bord
        // TODO:    DONE    players, each with a symbol X or O, take turn with inputs
        // TODO:    DONE    example of inputs were numbers corresponding to 2D array with elements similar to Numpad
        // TODO:    DONE    make sure to get only safe required inputs
        // TODO:    DONE    check for win conditions on Rows Lines and Diagonals
        // TODO:    DONE    check for a tie

        //TODO: DONE logic for player turns does not repeat
        //TODO: add validation for already occupied board spaces so players can not overwrite each other
        //TODO:

        Scanner scanner = new Scanner(System.in);

        char[][] playBoard = new char[3][3];
        char filler = '#';
        final char[][] numBoard = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
        final char p1 = 'X';
        final char p2 = 'O';
        final int winCon = 3;

        fillBoard(playBoard, filler);
        printInstructions(winCon);
        System.out.println("Input coordinates: ");
        printBoard(numBoard);
        System.out.println("Play board: ");
        printBoard(playBoard);

        while (true) {
            if (turn(scanner, numBoard, playBoard, p1, filler, winCon) || turn(scanner, numBoard, playBoard, p2, filler, winCon))
                break;
        }
    }

    public static void printInstructions(int w) {
        System.out.println("\nTIC-TAC-TOE\n");
        System.out.println("Player X and Player O take turns.\nPlayer X goes first.\nPlayer O goes second.\n");
        System.out.println("To WIN, get" + w + "of your symbols in the same row collum or diagonal.\n Choose where to place your symbol based on the example shown below:\n");
    }

    public static void fillBoard(char[][] board, char c) {
        for (int row = 0; row < board.length; row++) {
            Arrays.fill(board[row], c);
        }
    }

    public static void printBoard(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void changeBoard(char[][] boardP, char[][] boardN, int input, char currantPlayer) {
        for (int row = 0; row < boardN.length; row++) {
            for (int col = 0; col < boardN[row].length; col++) {
                if (boardN[row][col] == input) {
                    boardP[row][col] = currantPlayer;
                }
            }
        }
    }

    public static char getPlaceInput(Scanner scanner) {
        while (true) {
            try {
                final String d = scanner.nextLine();

                if (d.length() != 1) {
                    throw new Exception("Input is too long. Enter a single digit number.");
                }
                if (!Character.isDigit(d.charAt(0))) {
                    throw new Exception("Input is not a number. Enter a single digit number.");
                }
                if (d.charAt(0) == '0') {
                    throw new Exception("There is no position 0. Chose a number between 1 and 9.");
                }
                return d.charAt(0);
            } catch (Exception e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
            scanner.nextLine();
        }
    }

    public static boolean checkTie(char[][] board, char f) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == f) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkWinConRow(char[][] board, char p, int w) {
        int score = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == p) {
                    score++;
                }
                if (score >= w) {
                    return true;
                }
            }
            score = 0;
        }
        return false;
    }

    public static boolean checkWinConCol(char[][] board, char p, int w) {
        int score = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[col][row] == p) {
                    score++;
                }
                if (score >= w) {
                    return true;
                }
            }
            score = 0;
        }
        return false;
    }

    public static boolean checkWinConDia(char[][] board, char p, int w) {
        int score = 0;
        for (int row = 0; row < board.length; row++) {
            if (board[row][row] == p) {
                score++;
            }
            if (score >= w) {
                return true;
            }
        }
        score = 0;
        for (int row = (board.length - 1); row > -1; row--) {
            if (board[row][row] == p) {
                score++;
            }
            if (score >= w) {
                return true;
            }
        }
        return false;
    }

    public static boolean turn(Scanner scanner, char[][] bordN, char[][] boardP, char p, char f, int w) {

        System.out.println("Player " + p + ": ");
        changeBoard(boardP, bordN, getPlaceInput(scanner), p);
        printBoard(boardP);
        if (checkWinConRow(boardP, p, w) || checkWinConCol(boardP, p, w) || checkWinConDia(boardP, p, w)) {
            System.out.println("Player " + p + " WINS!");
            return true;
        } else if (checkTie(boardP, f)) {
            System.out.println("TIE!");
            return true;
        } else {
            return false;
        }
    }
}