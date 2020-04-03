package Maestus.Porkyman;

public class MaxRevive implements IItem {
	
	private int quantity;
	
	public MaxRevive() {
		super();
		quantity = 1;
	}
	
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
	
	@Override
	public boolean validate(Object o) {
	return yourTurn;
	}
	
	@Override
	public boolean depleteItem() {
		if (quantity < 1)
			return true;
		else return false;
	}
	
	@Override
	public Target getTarget() {
		return Target.SELF;
	}
	
	@Override
	public String toString() {
		return "Max Revive";
	}
}
