package Maestus.Porkyman;

/**
 * This interface describes the minimum functions necessary to pass a turn based system
 * Additionally, it enumerates a minimal targeting system for singular and group targets
 * @author Oracle
 *
 */
public interface IAction {
	
	/**
	 * Unimplemented, mostly used to other unimplemented methods for now
	 */
	public static boolean yourTurn = true;
	
	/**
	 * The generic method for a pockymon doing something that may affect other pockymon
	 * @param active pockymon
	 * @param targets
	 * @return turnComplete
	 */
	abstract boolean doSomething(Pockymon caster, Pockymon... targets);
	
	/**
	 * Some method to validate the action
	 * @param Object to check
	 * @return isValidated
	 */
	abstract boolean validate(Object o);
	
	/**
	 * Specifies who to target
	 * @return target(s)
	 */
	abstract Target getTarget();
	
	/**
	 * Enumerates who the potential targets are as a group
	 * @author Oracle
	 *
	 */
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
