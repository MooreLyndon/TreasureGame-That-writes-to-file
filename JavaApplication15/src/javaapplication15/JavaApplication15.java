
package javaapplication15;


import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.util.*;




public class JavaApplication15 {

   

    private static String AcquireDirectory;

    private static String[][] gameBoard;
    private static int boardSize;
    private static int numberOfGuesses;
    private static int score;

    public static void main(String[] args) {
        setUpGame();

        gameBoard = new String[boardSize][boardSize];
        score = 0;
        setUpBoard();
        addTreasure();

        for (int i = 0; i < numberOfGuesses; i++) {
            coordinateUserGuess();
            
            System.out.println("You have got "+ ((numberOfGuesses-1)-i) +" guesses left.");
        }
        printBoard(); 
        System.out.println("Your final score is: " + score);
        writeFile();
    }

    public static void setUpBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                gameBoard[i][j] = "[  ]";
            }
        }
    }

    public static void printBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(gameBoard[i][j]);
            }
            System.out.println("");
        }
    }

    //In random positions in the array store a random amount of coins
    public static void addTreasure() {
        Random random = new Random();
        //Random amount of treasure, between 1/4 and 1/2 of the boardsize

        int min = (boardSize * boardSize) / 4;
        int max = (boardSize * boardSize) / 2;

        int numberOfTreasureItems = random.nextInt((max - min) + 1) + min;

        System.out.println("Bits of treasure to find: " + numberOfTreasureItems);

        for (int i = 0; i < numberOfTreasureItems; i++) {
            //random number is 2 digits long
            gameBoard[random.nextInt(boardSize)][random.nextInt(boardSize)] = "[" + (random.nextInt((99 - 10) + 1) + 10) + "]";
        }

    }

    //Get the user to enter coordinates where they think the treasure is
    public static void coordinateUserGuess() {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("Please type in the X coordinate which is the row (any number between 1 and " + boardSize + ")");
                int x = input.nextInt() - 1;

                System.out.println("Please type in the y coordinate which is the column(any number between 1 and " + boardSize + ")");
                int y = input.nextInt() - 1;

                //Only accepts numbers between 0 and 9 to match the gameBoard size
                if (x >= 0 && x <= boardSize - 1 && y >= 0 && y <= boardSize - 1) {
                    checkForTreasure(x ,y);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e);
                System.out.println("Please type in only numbers between 0 and " + (boardSize - 1) + ")");
            }
            System.out.println("That input was not correct. Please check carefully.");
        }

    }

    //If there is treasure at this point this gets added to the users total
    public static void checkForTreasure(int x, int y) {
        if (!gameBoard[x][y].substring(1, 3).equals("  ")) {
            score = score + Integer.parseInt(gameBoard[x][y].substring(1, 3));
            System.out.println("Yes! You found treasure! Your score is "+score);
        }
        else{
            System.out.println("No treasure here. Your score is "+score);
        }
    }

    //Asks for board size
    public static void setUpGame() {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("What size would you like the board to be?");
                boardSize = input.nextInt();

                System.out.println("How many guesses would you like?");
                numberOfGuesses = input.nextInt();
                
                if(boardSize>=2 && numberOfGuesses>=2){
                    break;
                }
            } catch (Exception e) {
                System.out.println(" incorrect inputs. The board size should be a positive number more than 2, and the guesses should be a positive number more than 2");
            }
            System.out.println("That input was not correct. Please check carefully.");
        
        }
    }
    
public static void JavaApplication15(){
}
    public static void writeFile() {
        
        AcquireDirectory = System.getProperty("user.dir") + "\\Results.txt";
        
        
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your Name!");
        String Name = input.nextLine();
        
         try{
             FileWriter writeToFile = new FileWriter(AcquireDirectory,  true);
             PrintWriter printToFile = new PrintWriter(writeToFile);
             printToFile.print(Name);
             printToFile.print(" Score : " + score + "\n");
             printToFile.close();
             writeToFile.close();        
         } catch (Exception e) {
             System.out.println("error : " + e);
         }

} 
}  