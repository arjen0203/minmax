package minmax;

import java.util.Scanner;

public class Input {
    public boolean ended;
    public int user = 1;
    public int computer = 2;
    public Logic logic;
    boolean computersTurn;
    Scanner in;
    Node test;

    public Input(){
        ended = false;
        logic = new Logic();
        in = new Scanner(System.in);
    }

    public void start(){
        String s = in.nextLine();
        if (s.equals("start")){
            logic.setMove(1,1, 2);
            System.out.println("B2");
        } else {
            decodeMoveInput(s);
        }
        runGame();
    }
    public void runGame(){
        while (!ended){
            if (computersTurn){
                Node rootTree = logic.getTree();
                System.out.println(getComputerOutputString(computerMove(rootTree)));
                computersTurn = false;
            } else {
                String s = in.nextLine();
                decodeMoveInput(s);
            }
        }
    }
    public void getBoardState(int y, int x){
        int outcome = logic.check(y, x);
        if (outcome == 0){
            return;
        }
        ended = true;

        if (outcome == 1){
            System.out.println("User has won!");
            return;
        }

        if (outcome == 2){
            System.out.println("Computer has won!");
            return;
        }
        System.out.println("It was a tie!");
    }

    public String computerMove(Node root){
        String move = logic.algoritme.getMove(root);
        logic.setMove(Integer.parseInt(move.substring(0,1)), Integer.parseInt(move.substring(2,3)), 2);
        getBoardState(Integer.parseInt(move.substring(0,1)), Integer.parseInt(move.substring(2,3)));
        return move;
    }

//    public void doComputerMove(){
//        String output = logic.computerMoveRnd(computer);
//        getBoardState(Integer.parseInt(output.substring(0,1)), Integer.parseInt(output.substring(2,3)));
//        System.out.println(getComputerOutputString(output));
//    }

    public void decodeMoveInput(String input){
        String trimmedInput = input.trim();

        if (trimmedInput.length() > 2) {
            System.out.println("please enter valid move");
            return;
        }

        int y;
        switch (trimmedInput.substring(0,1)) {
            case "A":
            case "a":
                y = 0;
                break;
            case "B":
            case "b":
                y = 1;
                break;
            case "C":
            case "c":
                y = 2;
                break;
            case "D":
            case "d":
                y = 3;
                break;
            default:
                System.out.println("please enter valid move");
                return;
        }

        int x;

        try {
            x = Integer.parseInt(input.substring(1,2)) - 1;
        } catch (Exception e){
            System.out.println("please enter valid move");
            return;
        }

        if (!logic.isSpace(y, x)){
            System.out.println("please enter valid move");
            return;
        }

        logic.setMove(y, x, user);
        getBoardState(y, x);
        computersTurn = true;
    }

    public String getComputerOutputString(String coords){
        String row = "";
        switch (coords.charAt(0)) {
            case '0':
                row = "A";
                break;
            case '1':
                row = "B";
                break;
            case '2':
                row = "C";
                break;
            case '3':
                row = "D";
                break;
            default:
                break;
        }
        int column = Character.getNumericValue(coords.charAt(2)) + 1 ;
        return row + column;
    }
}
