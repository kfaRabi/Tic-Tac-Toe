/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimax.algorithm;

import java.util.ArrayList;

/**
 *
 * @author rabi
 */
public class State {

    private char board[][];
    private char currentPlayer;

    public State(int n, char currentPlayer) {
        this.board = new char[n][n];
        this.currentPlayer = currentPlayer;
    }

    public State(char board[][], char currentPlayer) {
        int n = board.length;
        this.board = new char[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                this.board[row][col] = board[row][col];
            }
        }
        this.currentPlayer = currentPlayer;
    }

    public void setValue(int row, int col, char mark) {
        board[row][col] = mark;
    }

    public char getValue(int row, int col) {
        return board[row][col];
    }

    public char getWinner() {
        // returns X if X is the winner
        // returns O if O is the winner
        // returns D if the game is drawn
        // returns U if the game is unfinished
        int n = board.length;

        // check all rows for winner
        for (int row = 0; row < n; row++) {
            int xCount = 0;
            int oCount = 0;
            for (int col = 0; col < n; col++) {
                if (board[row][col] == 'X') {
                    xCount++;
                } else if (board[row][col] == 'O') {
                    oCount++;
                }
            }
            if (xCount == n) {
                return 'X';
            } else if (oCount == n) {
                return 'O';
            }
        }

        // check all cols for winner
        for (int col = 0; col < n; col++) {
            int xCount = 0;
            int oCount = 0;
            for (int row = 0; row < n; row++) {
                if (board[row][col] == 'X') {
                    xCount++;
                } else if (board[row][col] == 'O') {
                    oCount++;
                }
            }
            if (xCount == n) {
                return 'X';
            } else if (oCount == n) {
                return 'O';
            }
        }

        // check main diagonal for winner
        {
            int xCount = 0;
            int oCount = 0;
            for (int i = 0; i < n; i++) {
                if (board[i][i] == 'X') {
                    xCount++;
                } else if (board[i][i] == 'O') {
                    oCount++;
                }
            }
            if (xCount == n) {
                return 'X';
            } else if (oCount == n) {
                return 'O';
            }
        }

        // check inverse diagonal for winner
        {
            int xCount = 0;
            int oCount = 0;
            for (int i = 0; i < n; i++) {
                if (board[i][n - i - 1] == 'X') {
                    xCount++;
                } else if (board[i][n - i - 1] == 'O') {
                    oCount++;
                }
            }
            if (xCount == n) {
                return 'X';
            } else if (oCount == n) {
                return 'O';
            }
        }

        // check for unfinished and drawn games
        {
            int totalCount = 0;
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (board[row][col] == 'X'
                            || board[row][col] == 'O') {
                        totalCount++;
                    }
                }
            }
            if (totalCount == n * n) {
                return 'D';
            } else {
                return 'U';
            }
        }
    }

    public State getNextState(Action action) {
        State newState;
        if (currentPlayer == 'X')
            newState = new State(board, 'O');
        else newState = new State(board, 'X');
        if (newState.board[action.getRow()][action.getCol()] != '.')
            return null;
        newState.board[action.getRow()][action.getCol()] = currentPlayer;
        return newState;
    }
    
    public int getUtilityValue(char player) {
        char winner = getWinner();
        if (player == winner)
            return +1;
        else if (winner == 'U' || winner == 'D')
            return +0;
        else return -1;
    }
    
    public char[][] getBoard() {
        return board;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }
    
    public ArrayList<Action> getActions() {
        int n = board.length;
        ArrayList<Action> actions = new ArrayList<>();
        for (int r = 0; r < n; r++)
            for (int c = 0; c < n; c++)
                if (board[r][c] == '.')
                    actions.add(new Action(r, c));
        return actions;
    }
    
    public String toString() {
        String output = "";
//        output += String.format("Current player: %c\n", currentPlayer);
        output += "Current player: " + currentPlayer + "\n";
        for (int i = 0; i < board.length; i++, output += "\n")
            for (int j = 0; j < board.length; j++)
                output += board[i][j] + "";
        return output;
    }
}
