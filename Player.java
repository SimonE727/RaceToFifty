package com.mycompany.racetofifty;

public class Player {

    private String name;
    private int position;
    private boolean skipTurn;

    // Constructor
    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.skipTurn = false;
    }

    // Get the player's name
    public String getName() {
        return name;
    }

    // Get the player's position
    public int getPosition() {
        return position;
    }

    // Move the player
    public void move(int spaces) {
        position = position + spaces;

        // Don't go below space 0
        if (position < 0) {
            position = 0;
        }

        // Don't go past space 50
        if (position > 50) {
            position = 50;
        }
    }

    // Check if the player must skip a turn
    public boolean getSkipTurn() {
        return skipTurn;
    }

    // Set whether the player must skip a turn
    public void setSkipTurn(boolean skipTurn) {
        this.skipTurn = skipTurn;
    }

    // Display player's information
    public void displayInfo() {
        System.out.println(
                name + " is on space " + position
        );
    }
}