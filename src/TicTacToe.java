import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static State ai;
    public static void main(String[] args) throws Exception {
        char[][] board = {{' ', '|', ' ', '|', ' '},
                        {'-', '+', '-', '+', '-'},
                        {' ', '|', ' ', '|', ' '},
                        {'-', '+', '-', '+', '-'},
                        {' ', '|', ' ', '|', ' '}};
        
        ai = new State(board, State.playsAsMin);
		ai.learn();

        State current = ai;
        boolean player = !State.playsAsMin;
        while(true) {
            if(player) {
                int[] pos = playerTurn(board, current);
                board[pos[0]][pos[1]] = player ? 'X' : 'O';
                current = current.children[pos[0]][pos[1]];
                player = false;
            } else {
                current = current.getChildWithValue();
				board = current.copyField();
                player = true;
            }
            printBoard(board);
            if(checkWinner(board)) {
                System.out.println(player == true ? "You lost!!" : "You won!!!");
                break;
            } else if (fieldFull(board)) {
                System.out.println("Game tied");
                break;
            }
        }
    }


    private static boolean validMove(int pos, char[][] board) {
        switch(pos) {
            case 1:
                if(board[0][0] == ' ') return true;
                break;
            case 2:
                if(board[0][2]== ' ') return true;
                break;
            case 3:
                if(board[0][4]== ' ') return true;
                break;
            case 4:
                if(board[2][0]== ' ') return true;
                break;
            case 5:
                if(board[2][2]== ' ') return true;
                break;
            case 6:
                if(board[2][4]== ' ') return true;
                break;
            case 7:
                if(board[4][0]== ' ') return true;
                break;
            case 8:
                if(board[4][2]== ' ') return true;
                break;
            case 9:
                if(board[4][4]== ' ') return true;
                break;
            default:
                return false;
        }
        return false;
    }

    private static int[] playerTurn(char[][] board, State current) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose your placement (1-9)");
        int pos = sc.nextInt();
        if(!validMove(pos, board)) {
            printBoard(board);
            System.out.println("The position has already been occupied");
            playerTurn(board, current);
        }
        int[] indice = placePiece(pos, board, "player");
        return indice;
    }

    public static void printBoard(char[][] board) {
        for(char[] row : board) {
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static int[] placePiece(int pos, char[][] board, String user) {
        char symbol = 'X';
        int[] result = new int[] {};
        if(user.equals("bot")) {
            symbol = 'O';
        }
        if(!validMove(pos, board)) return null;
        switch(pos) {
            case 1:
                board[0][0] = symbol;
                result =  new int[] {0, 0};
                break;
            case 2:
                board[0][2] = symbol;
                result =  new int[] {0, 2};
                break;
            case 3:
                board[0][4] = symbol;
                result =  new int[] {0, 4};
                break;
            case 4:
                board[2][0] = symbol;
                result =  new int[] {2, 0};
                break;
            case 5:
                board[2][2] = symbol;
                result =  new int[] {2, 2};
                break;
            case 6:
                board[2][4] = symbol;
                result =  new int[] {2, 4};
                break;
            case 7:
                board[4][0] = symbol;
                result =  new int[] {4, 0};
                break;
            case 8:
                board[4][2] = symbol;
                result =  new int[] {4, 2};
                break;
            case 9:
                board[4][4] = symbol;
                result =  new int[] {4, 4};
                break;
            default:
                System.out.println("Error placement.\nPlease enter placement from 1-9");
                break;
        }
        return result;
    }

    public static boolean checkWinner(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            if(i == 1 || i == 3) continue;
			if (board[i][0] == board[i][2] && board[i][2] == board[i][4] && board[i][0] != ' ') {
				return true;
			}
			if (board[0][i] == board[2][i] && board[2][i] == board[4][i] && board[0][i] != ' ') {
				return true;
			}
		}

		if (board[0][0] == board[2][2] && board[2][2] == board[4][4] && board[0][0] != ' ') {
			return true;
		}

		if (board[0][4] == board[2][2] && board[2][2] == board[4][0] && board[0][4] != ' ') {
			return true;
		}

		return false;
    }

    public static boolean fieldFull(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

}
