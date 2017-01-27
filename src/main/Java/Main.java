import maze.Maze;
import maze.MazeUI;

import javax.swing.*;

/**
 * Created by benjaminblack on 8/25/16.
 */
public class Main {

    private static Boolean gameActive = true;

    private static Boolean firstRun = true;

    private static Maze maze = null;

    private static Maze generateBoard(){
        if (firstRun){
            maze = MazeUI.getMazeWithGUI();
            firstRun = false;
        } else {
            maze.newMaze();
        }
        return maze;
    }

    public static void main(String[] args){
        System.out.println("Maze Runner Started");
        while (gameActive){
            int gameType = JOptionPane.showConfirmDialog(
                    null,
                    "Do you want the computer to play for you?",
                    "Welcome to the maze!",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if (gameType == JOptionPane.YES_OPTION) {
                computerPlayer();
            } else if (gameType == JOptionPane.NO_OPTION){
                onePlayer();
            } else {
                System.exit(0);
            }
        }
        JOptionPane.showMessageDialog(null,"Thanks for playing!","Come Back Soon!",JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }

    private static void computerPlayer(){
        generateBoard();
        Boolean active = true;
        int numberOfMoves = 0;

        while (active){
            numberOfMoves = numberOfMoves + 1;
            String mazeString = maze.toString();
            System.out.println(mazeString);
            if (mazeString.contains("@")){
                active = false;
            }
            Boolean front, right, left;
            front = maze.isOpenFront();
            right = maze.isOpenRight();
            left = maze.isOpenLeft();

            if (right){
                //Do if the computer can move right
                maze.turnClockwise();
                maze.moveForward();
            } else if (front){
                //If can't move right
                maze.moveForward();
            } else if (left){
                //If can't move forward
                maze.turnCounterClockwise();
            } else {
                //If can't move right, forward or left
                maze.turnClockwise();
            }
        }
        String name = "The Computer";
        playAgain(name, numberOfMoves);
    }

    private static void onePlayer(){

        if(firstRun) {
            JOptionPane.showMessageDialog(null,"Move List: \n" +
                    "w = forward. \n" +
                    "a = counter clockwise. \n" +
                    "d = clockwise. \n" +
                    "new = generate new board. \n" +
                    "quit = end game",
                    "How to Play",
                    JOptionPane.PLAIN_MESSAGE);
        }
        generateBoard();
        Boolean active = true;
        int numberOfMoves = 0;

        while (active){
            String mazeString = maze.toString();
            System.out.println(mazeString);

            if (mazeString.contains("@")){
                active = false;
            }

            numberOfMoves = numberOfMoves + 1;
            String move = null;

            while (move == null){
                move = JOptionPane.showInputDialog(null,"Enter Move (? for help)","Enter Your Move",JOptionPane.PLAIN_MESSAGE);
            }

            if (move.equals("w")){
                maze.moveForward();
                mazeString = maze.toString();
            } else if (move.equals("a")){
                maze.turnCounterClockwise();
                mazeString = maze.toString();
            } else if (move.equals("d")){
                maze.turnClockwise();
                mazeString = maze.toString();
            } else if (move.equals("quit")){
                int quit = JOptionPane.showConfirmDialog(null,
                        "Do you want to quit?", "Don't go!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                if (quit == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null,"Thanks for playing!","Exiting Game",JOptionPane.PLAIN_MESSAGE);
                    System.exit(0);
                }
            } else if (move.equals("cheat")){
                active = false;
            } else if (move.equals("new")){
                maze.newMaze();
                mazeString = maze.toString();
            } else if (move.equals("?") || move.equals("help")){
                JOptionPane.showMessageDialog(null,"Enter a letter to move or a command: \n" +
                        "'w' to go forward. \n" +
                        "'a' to turn counter clockwise. \n" +
                        "'d' to turn clockwise. \n" +
                        "'new' to generate a new board. \n" +
                        "'quit' to exit the game. \n" +
                        "'?' or 'help' to bring up this dialog. \n" +
                        "\n" +
                        "Credits:\n" +
                        "Logic developed by Benjamin Black, \n" +
                        "Libraries by Christopher Cantrell.\n" +
                        "Completed: 8/25/2016",
                        "Help & About",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }
        String name = "You";
        playAgain(name,numberOfMoves);
    }

    private static void playAgain(String name, int moves){
        JOptionPane.showMessageDialog(null,name+" completed the Maze in " + moves + " moves!","Maze Solved!",JOptionPane.PLAIN_MESSAGE);
        int again = JOptionPane.showConfirmDialog(
                null,
                "Do you want to play again?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (again == JOptionPane.NO_OPTION){
            gameActive = false;
        }
    }


}
