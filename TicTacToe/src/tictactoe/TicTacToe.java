/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.Scanner;

/**
 *
 * @author ssingh
 * set value of isPlaying
 * Set value of current player
 * while isPlaying is true continue to play
 * clear the board if its a new game
 * if its not a wind and its not a tie continue to next move 
 * prompt user to input move 
 * check if it is a valid move 
 * if it is an invalid move, promt user to input move again
 * if it is a valid move, fill up the cell in tic tac toe
 * toggle player
 * continue to do this till all the cells are filled 
 * check if its a tie or a win
 * prompt user to confirm if he/she wants to play again
 * save the user input in isPlaying
 */
public class TicTacToe {
    
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board [][] = new String[ROW][COL];
    private static boolean isTie = false;
    private static String currentPlayer = "O";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        boolean isPlaying = true;
        while(isPlaying) {
            clearBoard();
            currentPlayer = "O";
            while(!isWin(currentPlayer) && !isTie()) {
                togglePlayer();
                Scanner scanner = new Scanner(System.in);
                String promptRow = "Player " + currentPlayer + " Enter row number";
                String promptCol = "Player " + currentPlayer + " Enter column number";
                int rowNum = SafeInput.getRangedInt(scanner, promptRow, 1, ROW) - 1;
                int colNum = SafeInput.getRangedInt(scanner, promptCol, 1, COL) - 1;
                while(!isValidMove(rowNum, colNum)) {
                    rowNum = SafeInput.getRangedInt(scanner, promptRow, 1, ROW) - 1;
                    colNum = SafeInput.getRangedInt(scanner, promptCol, 1, COL) - 1;
                }
                makeMove(currentPlayer, rowNum, colNum);
            }
            if (isTie) {
                isTie = false;
                System.out.println("It's a tie.");
            } else {
                System.out.println("Player " + currentPlayer + " has won.");
            }
            Scanner scanner = new Scanner(System.in);
            isPlaying = SafeInput.getYNConfirm(scanner, "Do you wish to continue playing?");
        }        
    }
    
    private static void makeMove(String player, int row, int col) {
        board[row][col] = player;
    }
    
    private static void clearBoard() {
        for(int i = 0; i < ROW; i++) {
            for (int j = 0;j < COL; j++) {
                board[i][j] = "";
            }
        }
    }
    
    private static void togglePlayer() {
        if (currentPlayer == "X") {
            currentPlayer = "O";
        } else {
            currentPlayer = "X";
        }
    }
    
    private static void display() {
        
    }
    
    private static boolean isValidMove(int row, int col) {
        return board[row][col].isEmpty();
    }
    
    private static boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagnolWin(player);
    }
    
    private static boolean isColWin(String player) {
        for (int i = 0; i < COL; i++) {
            boolean isSameAsPlayer = true;
            int j = 0;
            while(j < ROW && isSameAsPlayer) {
                if (board[j][i] != player) {
                    isSameAsPlayer = false;
                }
                j++;
            }
            if(isSameAsPlayer) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROW; i++) {
            boolean isSameAsPlayer = true;
            int j = 0;
            while(j < COL && isSameAsPlayer) {
                if (board[i][j] != player) {
                    isSameAsPlayer = false;
                }
                j++;
            }
            if(isSameAsPlayer) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean isDiagnolWin(String player) {
        int i=0, j=0;
        boolean isSameAsPlayer = true;
        while(i < ROW && j < COL && isSameAsPlayer) {
            if(board[i][j] != player) {
                isSameAsPlayer = false;
            }
            i++;
            j++;
        }
        if (isSameAsPlayer) {
            return true;
        }
        
        i = ROW - 1; 
        j = COL - 1;
        isSameAsPlayer = true;
        while(i >= 0 && j >= 0 && isSameAsPlayer) {
            if(board[i][j] != player) {
                isSameAsPlayer = false;
            }
            i--;
            j--;
        }
        if (isSameAsPlayer) {
            return true;
        }
        return false;
    }
    
    private static boolean isTie() {
        for(int i = 0; i < ROW; i++) {
            for (int j = 0;j < COL; j++) {
                if (board[i][j] == "") {
                     return false;
                }
            }
        }
        isTie = true;
        return true;
    }    
}