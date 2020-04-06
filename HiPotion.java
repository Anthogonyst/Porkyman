package Maestus.Porkyman;

/**
 * Healing item for player to give to pockymon
 * @author Oracle
 *
 */
public class HiPotion implements IItem {

	/**
	 * Total amount owned by player
	 */
	private int quantity;
	
	/**
	 * Constructs a bag of this item
	 * Default quantity is 1
	 */
	public HiPotion() {
		super();
		quantity = 1;
	}

	/**
	 * Specifies how to execute item usage
	 * Strongly heals a single target
	 */
	@Override
	public boolean doSomething(Pockymon caster, Pockymon... targets) {
		if (quantity < 1) {
			// Ideally shouldn't be printed unless using multiple items at once
			System.out.println("You are out of Hi-Potions!");
			return false;
		}
		
		// TODO: Change this to single target after finishing debug trials
		for (Pockymon p : targets) {
			if (quantity > 0) {
				System.out.println(p + " is feeling alot better!");
				p.modHP(200);
				quantity--;
			}
		}
		
		return true;
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
		return "Hi-Potion";
	}
}
