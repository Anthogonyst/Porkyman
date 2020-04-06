package Maestus.Porkyman;

/**
 * Revival item for player to give to pockymon
 * Currently not in use because of PockymonBattle system architecture
 * @author Oracle
 *
 */
public class MaxRevive implements IItem {
	
	/**
	 * Total amount owned by player
	 */
	private int quantity;
	
	/**
	 * Constructs a bag of this item
	 * Default quantity is 1
	 */
	public MaxRevive() {
		super();
		quantity = 1;
	}
	
	/**
	 * Specifies how to execute item usage
	 * Revives a single dead pockymon
	 * Declines usage if target pockymon is alive
	 */
	@Override
	public boolean doSomething(Pockymon caster, Pockymon... targets) {
		if (quantity < 1) {
		// Ideally shouldn't be printed unless using multiple items at once
			System.out.println("You are out of Max Revive!");
			return false;
		}
		
		if (caster.checkAlive()) {
			System.out.println(caster.getNickname() + " doesn't need this right now.");
			return false;
		} else {
			caster.modHP(999); // HP.MAX if it was enumerated but this is fine too
			quantity--;
			System.out.println(caster.getNickname() + " is revived to max health!");
			return true;
		}		
	}
	
	/**
	 * Not implemented
	 */
	@Override
	public boolean validate(Object o) {
	return yourTurn;
	}
	
	/**
	 * If quantity runs out, informs container to delete this item
	 */
	@Override
	public boolean depleteItem() {
		if (quantity < 1)
			return true;
		else return false;
	}

	/**
	 * Intended target is one pockymon on player team
	 */
	@Override
	public Target getTarget() {
		return Target.SELF;
	}

	/**
	 * Displays name of item
	 */
	@Override
	public String toString() {
		return "Max Revive";
	}
}
