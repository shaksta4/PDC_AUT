/*
 * This is an abstract class which holds instance variables and a method signature
 * for a position modifier. It is extended by the Snakes, and the Ladders classes respectively.
 * 
 * @author Shakeel Khan
 */


import java.util.*;

public abstract class PositionModifier {
	
	int top;
	int bottom;
	boolean isTaken;
	Random rand = new Random();
		
	public abstract boolean moveSpaces(Player myPlayer);	
}
