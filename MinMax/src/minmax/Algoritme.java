package minmax;

public class Algoritme {
    CheckBoardState checkBoardState;
    int maxDepth;
    public Algoritme(CheckBoardState checkBoardStat) {
        this.checkBoardState = checkBoardStat;
    }
    int bestMovePlayer;
    int bestMoveComputer;

    public void setMaxDepth(int turn) {
        this.maxDepth = turn + 7;
    }

    public void getPossibleMove(Node node, int turn, boolean computersTurn) {
        int moveNumber = turn + 1;
        if (moveNumber == maxDepth) return;
        int modifieer = (maxDepth - turn) * 10;
        for (int y = 0; y < node.getBoardState().length; y++) {
            for (int x = 0; x < node.getBoardState()[y].length; x++) {
                if (node.getBoardState()[y][x] == 0) {
                    int[][] newArray = copyArray(node.getBoardState());
                    if (computersTurn) newArray[y][x] = 2;
                    else newArray[y][x] = 1;
                    Node child = new Node(y, x, newArray);
                    int boardState = checkBoardState.checkThisRow(y, x, newArray, moveNumber);
                    if (boardState != 0) {
                        if (boardState == 1){
                            child.setValue(-1 * modifieer);
                        } else
                        if (boardState == 2){
                            child.setValue(1 * modifieer);
                        } else
                        if (boardState == 3){
                            child.setValue(0 * modifieer);
                        }
                        node.addChild(child);
                    } else {
                        node.addChild(child);
                        getPossibleMove(child, moveNumber, !computersTurn);
                    }
                }
            }
        }
        //counts the values instead of min maxing because it does not have enough values
        if (maxDepth > 10){
            node.getChildValuesMinMax(computersTurn);
        } else {
            node.getChildValuesSum();
        }

    }

    public int[][] copyArray(int[][] oldArray){
        int[][] newArray = new int[oldArray.length][oldArray[0].length];
        for (int y = 0; y < oldArray.length; y++){
            for (int x = 0; x < oldArray[y].length; x++){
                newArray[y][x] = oldArray[y][x];
            }
        }
        return newArray;
    }

    public String getMove(Node root){
        int bestMove = root.children.get(0).getValue();
        int bestMoveIndex = 0;
        for (int i = 1; i < root.children.size(); i++){
            if (bestMove < root.children.get(i).getValue()){
                bestMove = root.children.get(i).getValue();
                bestMoveIndex = i;
            }
        }
        return root.children.get(bestMoveIndex).getMoveString();
    }

}
