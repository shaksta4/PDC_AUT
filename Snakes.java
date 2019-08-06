/*
 * This class models what a Snake is. It extends the PositionModifier class.
 * It holds a default construtor for initialising its values, as well as
 * the method body for the method signature in the PositionModifier class.
 * 
 * @author Shakeel Khan
 * 
 */


public class Snakes extends PositionModifier{
	
	//Default constructor setting approriate initial values.
	public Snakes(int head)
	{
		if(!super.isTaken)
		{
			super.top = head;
			super.bottom = super.rand.nextInt(head) + 1;	
			super.isTaken = true;
		}
	}
	
	/*
	 * This method takes a Player as a parameter. It checks if the player has landed
	 * on the Snake's head. If it has, return true and change the position of the player to the tail of the 
	 * snake. If is had not, it returns false.
	 * @param Player
	 * @return boolean
	 * @see PositionModifier#moveSpaces(Player)
	 */
	@Override
	public boolean moveSpaces(Player myPlayer)
	{
		if(myPlayer.getPosition() == super.top)
		{
			System.out.println("Oh no! You've landed on a snake! Bad luck!");
			myPlayer.setPosition(super.bottom);
			return true;
		}
		return false;
	}
	
	//Override of the toString method for an appropriate representation of the object
	public String toString()
	{
		return "Snake - HEAD:"+this.top+" TAIL:"+this.bottom;
	}
}
