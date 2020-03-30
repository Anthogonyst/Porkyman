package Maestus.Porkyman;

public class HiPotion implements IItem {

	private int quantity;
	
	public HiPotion() {
		super();
		quantity = 1;
	}

	@Override
	public boolean doSomething(Pockymon caster, Pockymon... targets) {
		if (quantity < 1) {
			// Ideally shouldn't be printed unless using multiple items at once
			System.out.println("You are out of Hi-Potions!");
			return false;
		}
		
		for (Pockymon p : targets) {
			if (quantity > 0) {
				System.out.println(p + " is feeling alot better!");
				p.modHP(200);
				quantity--;
			}
		}
		
		return true;
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
		return "Hi-Potion";
	}
}
