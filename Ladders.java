/*
 * This class models what a Snake is. It extends the PositionModifier class.
 * It holds a default construtor for initialising its values, as well as
 * the method body for the method signature in the PositionModifier class.
 * 
 * @author Shakeel Khan
 * 
 */


public class Ladders extends PositionModifier {

	/*
	 * Default constructor setting approriate initial values.
	 * The bottom of the ladder is passed in as a parameter, and
	 * the top of the ladder is a random place between the bottom and 100 on the game board.
	 */
	public Ladders(int bottom)
	{
		if(!super.isTaken)
		{
			super.top = super.rand.nextInt(100-bottom)+bottom;
			super.bottom = bottom;
			super.isTaken = true;
		}
	}
	
	/*
	 * This method takes a Player as a parameter. It checks if the player has landed
	 * on the Ladder's bottom. If it has, return true and change the position of the player to the top of the 
	 * ladder. If is had not, it returns false.
	 * @param Player
	 * @return boolean
	 * @see PositionModifier#moveSpaces(Player)
	 */
	@Override
	public boolean moveSpaces(Player myPlayer) 
	{
		if(myPlayer.getPosition() == super.bottom)
		{
			System.out.println("Congratulations! You've landed on a ladder spot!");
			myPlayer.setPosition(super.top);
			return true;
		}		
		return false;
	}

	//Override of the toString method for an appropriate representation of the object
	public String toString()
	{
		return "Ladder - BOTTOM:"+this.bottom+" TOP:"+this.top;
	}
}
