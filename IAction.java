package Maestus.Porkyman;

public interface IAction {
	public static boolean yourTurn = true;
	abstract boolean doSomething(Pockymon caster, Pockymon... targets);
	abstract boolean validate();
}
