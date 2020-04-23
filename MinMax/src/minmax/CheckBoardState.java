package minmax;

public class CheckBoardState {

    public int checkThisRow(int y, int x, int[][] board, int moveNumber){
        if (moveNumber == board.length * board[0].length) {
            return checkWin(board);
        } else {
            if (areRow(y, board) && board[y][0] != 0) return board[y][0];
            if (areColumn(x, board) && board[0][x] != 0) return board[0][x];
            if (x == y || x + y == board.length)
                if (board[0][0] != 0 && board[0][0] == board [1][1] && board[0][0] == board [2][2] && board[0][0] == board [3][3]){
                    return board[0][0];
                }
            if (board[0][3] != 0 && board[0][3] == board [1][2] && board[0][3] == board [2][1] && board[0][3] == board [3][0]){
                return board[0][3];
            }
            return 0;
        }
    }

    //0 nothing, 1 = user wins, 2 = computer wins, 3 = tie
    public int checkWin(int[][] board){
        for (int y = 0; y < board.length; y++){
            if (areRow(y, board) && board[y][0] != 0) return board[y][0];
        }

        for (int x = 0; x < board.length; x++){
            if (areColumn(x, board) && board[0][x] != 0) return board[0][x];
        }

        if (board[0][0] != 0 && board[0][0] == board [1][1] && board[0][0] == board [2][2] && board[0][0] == board [3][3]){
            return board[0][0];
        }

        if (board[0][3] != 0 && board[0][3] == board [1][2] && board[0][3] == board [2][1] && board[0][3] == board [3][0]){
            return board[0][3];
        }
        return 3;
    }

    public boolean areRow(int y, int[][] board){
        int counter = 0;

        for (int x = 0; x < board[y].length; x++){
            if (board[y][x] == board[y][0]) counter++;
        }

        if (counter == board[y].length) return true;
        return false;
    }

    public boolean areColumn(int x, int[][] board){
        int counter = 0;

        for (int y = 0; y < board.length; y++){
            if (board[y][x] == board[0][x]) counter++;
        }

        if (counter == board.length) return true;
        return false;
    }
}
