import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {

    static ArrayList<Integer> playerPos = new ArrayList<>();
    static ArrayList<Integer> botPos = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        char[][] board = {{' ', '|', ' ', '|', ' '},
                        {'-', '+', '-', '+', '-'},
                        {' ', '|', ' ', '|', ' '},
                        {'-', '+', '-', '+', '-'},
                        {' ', '|', ' ', '|', ' '}};
        
        while(true) {
            printBoard(board);
            String result = checkWinner();
            if(result.length() > 0) {
                printBoard(board);
                System.out.println(result);
                break;
            }
            playerTurn(board);
            botTurn(board);    
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

    private static void playerTurn(char[][] board) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please choose your placement (1-9)");
        int pos = sc.nextInt();
        if(!validMove(pos, board)) {
            printBoard(board);
            System.out.println("The position has already been occupied");
            playerTurn(board);
        }
        placePiece(pos, board, "player");
    }

    private static void botTurn(char[][] board) {
        Random rand = new Random();
        while(true) {
            int botMove = rand.nextInt(9) +1;
            if(validMove(botMove, board)) {
                placePiece(botMove, board, "bot");
                break;
            }
        }
    }

    public static void printBoard(char[][] board) {
        for(char[] row : board) {
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(int pos, char[][] board, String user) {
        char symbol = 'X';
        if(user.equals("bot")) {
            symbol = 'O';
        }
        if(!validMove(pos, board)) return;
        switch(pos) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
            default:
                System.out.println("Error placement.\nPlease enter placement from 1-9");
                break;
        }
        if(user.equals("player")) playerPos.add(pos);
        if(user.equals("bot")) botPos.add(pos);
        
    }

    private static String checkWinner() {
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> middleRow = Arrays.asList(4, 5, 6);
        List<Integer> BottomRow = Arrays.asList(7, 8, 9);
        List<Integer>leftCol = Arrays.asList(1, 4, 7);
        List<Integer>midCol = Arrays.asList(2, 5, 8);
        List<Integer>rightCol = Arrays.asList(3, 6, 9);
        List<Integer>cross1 = Arrays.asList(1, 5, 9);
        List<Integer>cross2 = Arrays.asList(3, 5, 7);

        List<List<Integer>> winCon = new ArrayList<List<Integer>>();
        winCon.add(topRow);
        winCon.add(middleRow);
        winCon.add(BottomRow);
        winCon.add(leftCol);
        winCon.add(midCol);
        winCon.add(rightCol);
        winCon.add(cross1);
        winCon.add(cross2);

        for(List<Integer>l : winCon) {
            if(playerPos.containsAll(l)) return "Congratulation, you won!!";
            if(botPos.containsAll(l)) return "Too bad, you lost.";
            if(playerPos.size() + botPos.size() == 9) return "Game is tied.";
        }
        return "";
    }

}
