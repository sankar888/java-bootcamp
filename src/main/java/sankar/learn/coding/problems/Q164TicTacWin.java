package sankar.learn.coding.problems;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Q164TicTacWin {
    /**
     * Design an algorithm to figure out if some one has won the game of tic tac toe
     */
    private static Random random = new Random();

    public static void main(String[] args) {
        solve();
    }

    enum Piece {
        BLANK,
        X,
        O;
    }
    private static final Piece[] players = {Piece.X, Piece.O};

    private static void solve() {
        //consider a n x n board
        //the board is always square , n
        int boardSize = 3; //board size
        Piece[][] board = initializeBoard(3);

        //the board is filled after play
        board = play(board);
        printBoard(board, "Board After play");

        //check who has won
        Piece p = hasWon(board);
        if (p.equals(Piece.BLANK)) {
           System.out.println("Draw. No one has won");
        } else {
            System.out.printf("Player %s has won\n", p);
        }
    }

    private static Piece[][] initializeBoard(int boardSize) {
        Piece[][] board = new Piece[boardSize][boardSize];
        for (int i=0; i < boardSize; i++) {
            for (int j=0; j < boardSize; j++) {
                board[i][j] = Piece.BLANK;
            }
        }
        return board;
    }

    private static Piece[][] play(Piece[][] board) {
        int boardSize = board.length;
        for (int i=0; i < boardSize; i++) {
            for (int j=0; j < boardSize; j++) {
                board[i][j] = players[random.nextInt(2)];
            }
        }
        return board;
    }

    private static Piece hasWon(Piece[][] board) {
        int boardSize = board.length;
        /**
         * There are only 3 combinations of ways one can win in tic tac toe
         * vertical
         * horizontal
         * diagonal
         */

        //check vertical
        for (int i=0; i < boardSize; i++) {
            Piece p = board[i][0];
            boolean won = true;
            if (!p.equals(Piece.BLANK)) {
                for (int j=1; j < boardSize; j++) {
                    if (!board[i][j].equals(p)) {
                        won = false;
                        break;
                    }
                }
                if (won) {
                    return p;
                }
            }
        }

        //check horizontal
        for (int i=0; i < boardSize; i++) {
            Piece p = board[0][i];
            boolean won = true;
            if (!p.equals(Piece.BLANK)) {
                for (int j=1; j < boardSize; j++) {
                    if (!board[j][i].equals(p)) {
                        won = false;
                        break;
                    }
                }
                if (won) {
                    return p;
                }
            }
        }

        //check diagonal - lower left to top right
        Piece p = board[0][0];
        if (!p.equals(Piece.BLANK)) {
            boolean won = true;
            for (int i=1 ; i < boardSize; i++) {
                if (!board[i][i].equals(p)) {
                    won = false;
                    break;
                }
            }
            if (won) {
                return p;
            }
        }

        //check diagonal - top left to lower right
        p = board[0][boardSize-1];
        if (!p.equals(Piece.BLANK)) {
            boolean won = true;
            for (int i=1; i < boardSize; i++) {
                if (!board[i][boardSize-i-1].equals(p)) {
                    won = false;
                    break;
                }
            }
            if (won) {
                return p;
            }
        }

        return Piece.BLANK; //no one has won
    }

    private static void printBoard(Piece[][] board, String headerMsg) {
        int boardSize = board.length;
        System.out.printf("--- %s ---\n",headerMsg);
        for (int i=boardSize-1; i >= 0; i--) {
            for(int j=0; j < boardSize; j++) {
                System.out.printf("%s\t", board[j][i]);
            }
            System.out.print("\n");
        }
        System.out.println("-------");
    }
}
