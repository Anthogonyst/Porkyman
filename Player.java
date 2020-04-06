package Maestus.Porkyman;

/**
 * The player is the entity that represents a person's character
 * The player may have a pockymon and an item to support it in battle
 * @author Oracle
 *
 */
public abstract class Player {
	
	private final boolean isHuman;
	
	/**
	 * Constructs one of two possible players regarding either human or computerized input
	 * @param isHuman
	 */
	public Player(boolean isHuman) {
		super();
		this.isHuman = isHuman;
	}
	
	/**
	 * Retrieves the player's pockymon
	 * @return pockymon
	 */
	public abstract Pockymon getPockymon();
	
	/**
	 * Retrieves the player's item
	 * @return
	 */
	public abstract IItem getItem();
	
	/**
	 * Implements a method for running away
	 */
	public abstract void run();
	
	/**
	 * Ascertains whether human or computer player
	 * @return isHuman
	 */
	public boolean isHuman() { return isHuman; }
}
