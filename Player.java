
/*
 * This class models what a player is in the application.
 * It holds instance variables, a constructor which defines the initial values, and
 * methods which help get and set values for the variables
 * 
 * @author Shakeel Khan
 * 
 */


public class Player {
	
	private String name;
	private int position;
	
	// Default constructor
	public Player()
	{
		this.setPosition(1);
		this.name = null;
	}

	/*
	 * Get and set methods for the name attribute
	 */
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	/*
	 * Get and set methods for the position attribute
	 */
	public int getPosition() 
	{
		return position;
	}

	public void setPosition(int position) 
	{
		this.position = position;
	}
	
	/*
	 * Override of the toString method to print an appropriate representation of the object.
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return this.name+" ("+this.position+")";
	}
}
