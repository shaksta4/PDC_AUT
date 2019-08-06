/*
 * This class models what a dice is and holds a function 
 * for determining a random number on the dice
 * between 1 to 6.
 * 
 * @author Shakeel Khan
 * 
 */


public class Dice {

	int diceValue;
	
	// Default constructor for the dice. Initial value is 0.
	public Dice()
	{
		this.diceValue = 0;
	}
	
	/*
	 * This function returns an int. It uses Math.random to 
	 * generate a random number between 1 and 6 and then returns it.
	 * @return random number between 1 and 6
	 */
	public int rollDice()
	{
		return (int)(6 * Math.random()) + 1;
	}
}
