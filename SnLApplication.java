/*
 * This application is a simple, text based version of the board game Snakes and Ladders. 
 * It holds functionality for any specified number of players, as long as the player count is not less than
 * or equal to zero. Each snake and ladder is randomly generated on the board every time the 
 * program is run. The methods for each part of the interactive user interface are separated in 
 * their own methods for easy viewing. 
 * 
 * For the purpose of the Assignment, the snake and ladder positions are printed out to a file called "output.txt"
 * They are then read back in line by line onto the console for the user to view. 
 * 
 * @title Snakes and Ladders
 * @author Shakeel khan
 * Version 1.2
 * 
 */

import java.io.*;
import java.util.*;

public class SnLApplication {
	
	static Scanner keyboard = new Scanner(System.in);
    static ArrayList<Player> players = new ArrayList<Player>();
	static ArrayList<PositionModifier> SorL = new ArrayList<PositionModifier>();
    final static int snakeNLadderValues = 12; // Set this number to as many snakes or ladders you want
    
    /*
     * Main method for this application. Holds the main functionality of the program. 
     * It consists of the other methods devised in this application.
     * 
     */
	public static void main(String args[])
	{
		int userInput;
		int numPlayers = 0;
		
		//Menu options
		System.out.println("Welcome to the game of Snakes and Ladders!");
		System.out.println("Please choose a menu option"); 																	
		while(true)
		{
			
			System.out.println("1. Play\n"
							 + "2. Rules\n"
							 + "3. Exit");
			try
			{
				userInput = keyboard.nextInt();
				//If user wants to play
				if(userInput == 1) 																	
				{
					Dice d1 = setBoard();
					while(true)
					{
						try
						{
							// Select number of players
							System.out.println("Please select the number of players.");  										
							numPlayers = keyboard.nextInt();
							setPlayers(numPlayers);
							while(true)
							{
								try
								{
									// Ask each player to roll dice and reassign their positions accordingly
									for(int j = 0; j < numPlayers; j++) 																
									{
										snlPlay(d1, numPlayers);	
									}
								}
								catch(InputMismatchException e)
								{
									System.out.println(e.getMessage());
								}
							}
						}
						catch(InputMismatchException e)
						{
							System.out.println(e.getMessage());
						}
					}
				}
				//If user wishes to view the game rules
				else if(userInput == 2) 																										
				{
					printRules();
				}
				//If user wishes to quit.
				else if(userInput == 3) 																													
				{
					exitGame();
				}
				else
				{
					throw new InputMismatchException();
				}
			}
			catch(InputMismatchException e)
			{
				System.out.println("Incorrect input! Please choose the correct menu number");
			} 
			finally
			{
				keyboard.nextLine();
			}
		}
	}
	
	/*
	 * This method simply prints out the rules to the console. 	 * 
	 */
	public static void printRules()
	{
		System.out.println("Here are the official rules for Snakes and Ladders Version 2.0 \n"
				+ "*************************************************************************************************************\n"
				+ "> The aim of the game is to reach 100 as soon as possible \n"
				+ "> On the way, there will be snakes and ladders at randomly generated places \n"
				+ "> If you land on a snake head, you will be eaten and must travel down to the tail position \n"
				+ "> If you land on a ladder bottom, you will climb up the ladder and travel to the top of the ladder's position \n"
				+ "> If you roll a 6, you get another turn \n"
				+ "> If you reach 100 or go beyond, you win !! \n"
				+ "> Have fun playing this game and good luck !\n"
				+ "*************************************************************************************************************");
	}
	
	/*
	 * This method simply prints an exit message for the user and quits the program.
	 */
	public static void exitGame()
	{
		System.out.println("Thank you for using this Application!");
		System.exit(0);		
	}
	/*
	 * This method takes in the dice, and the number of players as parameters. It holds 
	 * a lot of the functionality in the program. Players will be asked to roll the dice. 
	 * And the value they obtain will be used to increase their position. 
	 * If a user lands on a snake, or ladder, their position will be modified accordingly. 
	 */
	public static void snlPlay(Dice d1, int numPlayers)
	{
		char rollDice;
		int diceValue;
		// Ask each player to roll dice
		for(int j = 0; j < numPlayers; j++) 																
		{
			System.out.println(players.get(j)+" roll dice? (y/n)");
			rollDice = keyboard.next().charAt(0);
			// If yes
			if(rollDice == 'Y' || rollDice == 'y') 	
			{
				diceValue = d1.rollDice();
				System.out.println("You rolled a "+diceValue);
				players.get(j).setPosition(players.get(j).getPosition() + diceValue);
				// If number rolled is 6
				if(diceValue == 6) 																									
				{
					System.out.println("You get an extra roll because you rolled a 6!");
					diceValue = d1.rollDice();
					System.out.println("You rolled a "+diceValue);
					players.get(j).setPosition(players.get(j).getPosition() + diceValue);
				}
				System.out.println("Your new position is "+players.get(j).getPosition());
				for(int i = 0; i < SorL.size(); i++) 															
				{
					// If player lands on snake or ladder
					if(SorL.get(i).moveSpaces(players.get(j)))
					{
						System.out.println("Your new position is "+players.get(j).getPosition());
					}
				}
				System.out.println("--------------------------------");
			}
			// If player forfeits turn
			else if(rollDice == 'N' || rollDice == 'n') 																	
			{
				System.out.println(players.get(j).getName()+" has forfeit their turn!");
			}
			else
			{
				throw new InputMismatchException("Please choose either y or n");
			}	
			//Condition for winner
			if(players.get(j).getPosition() >= 100)  																	
			{
				System.out.println("Well done "+players.get(j).getName()+"! You have won the game!");
				System.exit(0);
			}
		}
	}
	
	/*
	 * This method takes an int as a parameter. it
	 * Asks each player to enter their name.
	 * @param int
	 * @return null
	 */
	public static void setPlayers(int numPlayers)
	{
		String playerName;
		if(numPlayers == 0)
		{
			throw new InputMismatchException("There must be at least one player!");
		}
		// Set each player's name
		for(int i = 0; i < numPlayers; i++) 														
		{
			players.add(new Player());
			System.out.println("Player "+(i+1)+", enter your name?");
			playerName = keyboard.next();
			players.get(i).setName(playerName);
		}
		
	}
	
	/*
	 * This method returns a Dice. It holds functionality for assigning snake and ladder positions onto the 
	 * game board. It also writes the snake and ladder positions to a text file "output.txt".
	 * It then reads from the textfile onto the console for the user to view. 
	 * @return Dice
	 */
	public static Dice setBoard()
	{
		Random rand = new Random();
		String line = null;
		
		System.out.println("Snakes and ladder positions!\n"+
				"----------------------------");
		try
		{
			FileOutputStream fos = new FileOutputStream("output.txt");
			PrintWriter pw = new PrintWriter(fos);
			
			FileReader fr = new FileReader("output.txt");
			BufferedReader br = new BufferedReader(fr);
			
			// Add the snakes and ladders at random positions in the board
			for(int i = 0; i < snakeNLadderValues; i++)																	 
			{
				// Create new snake where the head is anywhere between 10-99 (tail is random lower value than head)
				SorL.add(new Snakes(rand.nextInt(89)+10)); 
				// Create new ladder where the bottom is anywhere between 2-89 (top is random higher value than top)
				SorL.add(new Ladders(rand.nextInt(87)+2)); 
				pw.println(SorL.get(i));
				pw.flush();
			}
			while((line = br.readLine()) != null)
			{
				System.out.println(line);
			}
			System.out.println("");
			br.close();
			pw.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		Dice dice = new Dice();
		return dice;
	}
}
