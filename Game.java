package com.mycompany.racetofifty;

import java.util.Random;
import java.util.Scanner;

public class Game {

    private Player player;
    private Player computer;

    private Random random;
    private Scanner scanner;

    private final int FINAL_SPACE = 50;

    // Constructor
    public Game() {

        player = new Player("Player");
        computer = new Player("Computer");

        random = new Random();
        scanner = new Scanner(System.in);
    }

    // Start the game
    public void start() {

        displayIntroduction();

        while (true) {

            // Player's turn
            playerTurn();

            if (checkWinner()) {
                break;
            }

            // Computer's turn
            computerTurn();

            if (checkWinner()) {
                break;
            }
        }

        scanner.close();
    }

    // Display game instructions
    private void displayIntroduction() {

        System.out.println("======================================");
        System.out.println("           RACE TO FIFTY");
        System.out.println("======================================");
        System.out.println();

        System.out.println("Welcome to Race to Fifty!");
        System.out.println();

        System.out.println("Rules:");
        System.out.println("- The board has 50 spaces.");
        System.out.println("- Roll the dice to move.");
        System.out.println("- Every 5th space has a special twist.");
        System.out.println("- The first player to reach space 50 wins.");
        System.out.println();

        System.out.println("Special spaces:");
        System.out.println("5  = Move forward 3 spaces");
        System.out.println("10 = Lose your next turn");
        System.out.println("15 = Move backward 4 spaces");
        System.out.println("20 = Move forward 5 spaces");
        System.out.println("25 = Lose your next turn");
        System.out.println("30 = Move backward 6 spaces");
        System.out.println("35 = Move forward 4 spaces");
        System.out.println("40 = Lose your next turn");
        System.out.println("45 = Move backward 3 spaces");
        System.out.println("50 = WIN!");
        System.out.println();

        System.out.println("Good luck!");
    }

    // Player's turn
    private void playerTurn() {

        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println("              YOUR TURN");
        System.out.println("--------------------------------------");

        displayPositions();

        // Check if player must skip turn
        if (player.getSkipTurn()) {

            System.out.println();
            System.out.println("You must skip this turn!");

            player.setSkipTurn(false);

            return;
        }

        System.out.println();
        System.out.println("Press ENTER to roll the dice.");

        scanner.nextLine();

        int roll = rollDice();

        System.out.println();
        System.out.println("You rolled a " + roll + "!");

        movePlayer(player, roll);

        displayPositions();
    }

    // Computer's turn
    private void computerTurn() {

        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println("           COMPUTER'S TURN");
        System.out.println("--------------------------------------");

        // Check if computer must skip turn
        if (computer.getSkipTurn()) {

            System.out.println();
            System.out.println("The computer must skip this turn!");

            computer.setSkipTurn(false);

            return;
        }

        int roll = rollDice();

        System.out.println();
        System.out.println("The computer rolled a " + roll + "!");

        movePlayer(computer, roll);

        displayPositions();
    }

    // Roll a six-sided die
    private int rollDice() {

        return random.nextInt(6) + 1;
    }

    // Move a player
    private void movePlayer(Player currentPlayer, int roll) {

        int oldPosition = currentPlayer.getPosition();

        // Make sure the player does not go past space 50
        if (oldPosition + roll > FINAL_SPACE) {

            System.out.println(
                    currentPlayer.getName()
                    + " cannot move " + roll
                    + " spaces."
            );

            System.out.println(
                    currentPlayer.getName()
                    + " stays on space "
                    + oldPosition + "."
            );

            return;
        }

        // Move the player
        currentPlayer.move(roll);

        System.out.println(
                currentPlayer.getName()
                + " moved from space "
                + oldPosition
                + " to space "
                + currentPlayer.getPosition()
                + "."
        );

        // Check for a special space
        checkSpecialSpace(currentPlayer);
    }

    // Check special spaces
    private void checkSpecialSpace(Player currentPlayer) {

        int position = currentPlayer.getPosition();

        switch (position) {

            case 5:

                System.out.println();
                System.out.println("TWIST!");
                System.out.println("Space 5: Move forward 3 spaces!");

                currentPlayer.move(3);

                System.out.println(
                        currentPlayer.getName()
                        + " moved to space "
                        + currentPlayer.getPosition()
                        + "."
                );

                break;

            case 10:

                System.out.println();
                System.out.println("TWIST!");
                System.out.println("Space 10: Lose your next turn!");

                currentPlayer.setSkipTurn(true);

                break;

            case 15:

                System.out.println();
                System.out.println("TWIST!");
                System.out.println("Space 15: Move backward 4 spaces!");

                currentPlayer.move(-4);

                System.out.println(
                        currentPlayer.getName()
                        + " moved back to space "
                        + currentPlayer.getPosition()
                        + "."
                );

                break;

            case 20:

                System.out.println();
                System.out.println("TWIST!");
                System.out.println("Space 20: Move forward 5 spaces!");

                currentPlayer.move(5);

                System.out.println(
                        currentPlayer.getName()
                        + " moved to space "
                        + currentPlayer.getPosition()
                        + "."
                );

                break;

            case 25:

                System.out.println();
                System.out.println("TWIST!");
                System.out.println("Space 25: Lose your next turn!");

                currentPlayer.setSkipTurn(true);

                break;

            case 30:

                System.out.println();
                System.out.println("TWIST!");
                System.out.println("Space 30: Move backward 6 spaces!");

                currentPlayer.move(-6);

                System.out.println(
                        currentPlayer.getName()
                        + " moved back to space "
                        + currentPlayer.getPosition()
                        + "."
                );

                break;

            case 35:

                System.out.println();
                System.out.println("TWIST!");
                System.out.println("Space 35: Move forward 4 spaces!");

                currentPlayer.move(4);

                System.out.println(
                        currentPlayer.getName()
                        + " moved to space "
                        + currentPlayer.getPosition()
                        + "."
                );

                break;

            case 40:

                System.out.println();
                System.out.println("TWIST!");
                System.out.println("Space 40: Lose your next turn!");

                currentPlayer.setSkipTurn(true);

                break;

            case 45:

                System.out.println();
                System.out.println("TWIST!");
                System.out.println("Space 45: Move backward 3 spaces!");

                currentPlayer.move(-3);

                System.out.println(
                        currentPlayer.getName()
                        + " moved back to space "
                        + currentPlayer.getPosition()
                        + "."
                );

                break;

            case 50:

                System.out.println();
                System.out.println("FINISH!");
                System.out.println(
                        currentPlayer.getName()
                        + " reached space 50!"
                );

                break;

            default:

                System.out.println("No twist on this space.");

                break;
        }
    }

    // Display both players' positions
    private void displayPositions() {

        System.out.println();
        System.out.println("CURRENT POSITIONS");
        System.out.println("--------------------------------------");

        player.displayInfo();
        computer.displayInfo();
    }

    // Check if someone reached space 50
    private boolean checkWinner() {

        if (player.getPosition() == FINAL_SPACE) {

            System.out.println();
            System.out.println("======================================");
            System.out.println("              YOU WIN!");
            System.out.println("======================================");
            System.out.println();
            System.out.println("Congratulations!");
            System.out.println("You reached space 50!");

            return true;
        }

        if (computer.getPosition() == FINAL_SPACE) {

            System.out.println();
            System.out.println("======================================");
            System.out.println("          COMPUTER WINS!");
            System.out.println("======================================");
            System.out.println();
            System.out.println("The computer reached space 50 first!");

            return true;
        }

        return false;
    }
}