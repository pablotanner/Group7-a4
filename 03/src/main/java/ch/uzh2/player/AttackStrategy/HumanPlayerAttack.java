package main.java.ch.uzh2.player.AttackStrategy;

import main.java.ch.uzh2.player.HumanPlayer1;
import main.java.ch.uzh2.player.IPlayer;
import main.java.ch.uzh2.board.Position;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayerAttack extends AbstractAttackStrategy{
    public HumanPlayerAttack() {}

    @Override
    public void shootAt(IPlayer player,IPlayer opponent) {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        while(true){
            try{
                if (player instanceof HumanPlayer1)
                System.out.printf("This is Player1's turn.\n Please enter the position to shoot at.\n");
                else System.out.printf("This is Player2's turn.\n Please enter the position to shoot at.\n");
                String input = scanner.nextLine();  // Read user input
                verifyInput(input);

                Position position = Position.parse(input);

                if (opponent.wasAttackedAtPosition(position)) {
                    System.out.println("A shot has already been fired at " + position);
                }
                else {
                    opponent.takeShotAt(position);
                    break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred, please try again: " + e.getMessage());
            }
        }
    }

    private void verifyInput(String s){
        //verify against basic heuristics
        if(s.length() != 2)
            throw new InputMismatchException("Please enter valid input");


        //check if coordinate is valid
        if(Position.parse(s) == null)
            throw new InputMismatchException("Please enter valid location");
    }
}
