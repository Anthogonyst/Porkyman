package Maestus.Porkyman;

public class NullItem implements IItem {

	@Override
	public boolean doSomething(Pockymon caster, Pockymon... targets) {
		return false;
	}

	@Override
	public boolean validate(Object o) {
		return false;
	}

	@Override
	public boolean depleteItem() {
		return false;
	}

	@Override
	public Target getTarget() {
		return Target.NONE;
	}
	
	@Override
	public String toString() {
		return "NULL ITEM";
	}

}
