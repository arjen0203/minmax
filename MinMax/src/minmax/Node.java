package minmax;

import java.util.ArrayList;

public class Node {
    private int yMove;
    private int xMove;
    private int value;
    private int[][] boardState;
    ArrayList<Node> children = new ArrayList<>();

    public Node(int[][] boardStat){
        this.boardState = boardStat;
        value = 0;
    }

    public Node(int y, int x, int[][] boardStat){
        yMove = y;
        xMove = x;
        this.boardState = boardStat;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int[][] getBoardState() {
        return boardState;
    }

    public void addChild(Node node) {
        children.add(node);
    }

    public void getChildValuesMinMax(boolean computersTurn){
        int temp = 0;
        if (computersTurn){
            for (Node child: children) {
                if (child.value > temp) {
                    temp = child.value;
                }
            }
        } else {
            for (Node child: children) {
                if (child.value < temp) temp = child.value;
            }
        }

        value = temp;
    }
    public void getChildValuesSum(){
        int temp = 0;
            for (Node child: children) {
                temp = temp + child.value;
            }

        value = temp;
    }

    public String getMoveString(){
        return yMove + "," + xMove;
    }
}
