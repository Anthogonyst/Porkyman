package Maestus.Porkyman;

public abstract class Player {
	private final boolean isHuman;
	
	public Player(boolean isHuman) {
		super();
		this.isHuman = isHuman;
	}
	
	public abstract Pockymon getPockymon();
	public abstract IItem getItem();
	public abstract void run();
	
	public boolean isHuman() { return isHuman; }
}
