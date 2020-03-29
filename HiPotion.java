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
			System.out.println("You are out of Hi-Potions!");
			return false;
		}
		
		for (Pockymon p : targets) {
			if (quantity > 0) {
				p.modHP(200);
				depleteItem();
			}
		}
		
		return true;
	}

	@Override
	public boolean validate() {
		return yourTurn;
	}

	@Override
	public boolean depleteItem() {
		quantity--;
		return true;
	}
	
	@Override
	public String toString() {
		return "Hi-Potion";
	}
}
