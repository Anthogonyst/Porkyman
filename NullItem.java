package Maestus.Porkyman;

public class NullItem implements IItem {

	@Override
	public boolean doSomething(Pockymon caster, Pockymon... targets) {
		return false;
	}

	@Override
	public boolean validate() {
		return false;
	}

	@Override
	public boolean depleteItem() {
		return false;
	}

	@Override
	public String toString() {
		return "NULL ITEM";
	}
}
