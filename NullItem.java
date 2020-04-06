package Maestus.Porkyman;

/**
 * An empty item that does nothing and is the result of an item that is marked for deletion
 * @author Oracle
 *
 */
public class NullItem implements IItem {

	/**
	 * Does nothing but is for IAction compliance
	 */
	@Override
	public boolean doSomething(Pockymon caster, Pockymon... targets) {
		System.out.println("You are out of this item.");
		return false;
	}

	/**
	 * Does not validate ever
	 */
	@Override
	public boolean validate(Object o) {
		return false;
	}

	/**
	 * Can't deplete what doesn't exist
	 */
	@Override
	public boolean depleteItem() {
		return false;
	}

	/**
	 * Nothing cannot target anything
	 */
	@Override
	public Target getTarget() {
		return Target.NONE;
	}
	
	/**
	 * Displays name of item
	 * May need to rename it to a blank String in the future but this works better right now
	 */
	@Override
	public String toString() {
		return "NULL ITEM";
	}

}
