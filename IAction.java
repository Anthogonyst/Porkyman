package Maestus.Porkyman;

public interface IAction {
	public static boolean yourTurn = true;
	abstract boolean doSomething(Pockymon caster, Pockymon... targets);
	abstract boolean validate(Object o);
	abstract Target getTarget();
	
	enum Target {
		SELF,
		ALLIES,
		FOE,
		RIVALS,
		//OTHER,
		ALL,
		//EXCEPT,
		//ANY,
		//RANDOM,
		NONE;
	}
}
