package minmax;

import java.util.Random;

public class Logic {
    public int[][] board;
    public int counter;
    public CheckBoardState checkBoardState = new CheckBoardState();
    public Algoritme algoritme = new Algoritme(checkBoardState);

    final int boardSize = 4;

    public Logic(){
        board = new int[][] {{0,0,0,0},
                             {0,0,0,0},
                             {0,0,0,0},
                             {0,0,0,0}};
        counter = 0;
    }

    public void setMove(int y, int x, int player){
        board[y][x] = player;
    }

    public boolean isSpace(int y, int x){
        if (x > board[0].length - 1) return false;
        if (board[y][x] == 0) return true;
        return false;
    }

    public String computerMoveRnd(int computer){
        Random rnd = new Random();
        int x = rnd.nextInt(4);
        int y = rnd.nextInt(4);

        if (isSpace(y, x)) {
            setMove(y, x, computer);
            return y + "," + x;
        }
        else return computerMoveRnd(2);
    }
    public int check(int y, int x){
        counter++;
        return checkBoardState.checkThisRow(x, y, board, counter);
    }


    public Node getTree(){
        Node root = new Node(board);
        algoritme.setMaxDepth(counter);
        algoritme.getPossibleMove(root, counter, true);
        return root;
    }

}
