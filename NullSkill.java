package Maestus.Porkyman;

/**
 * An empty skill that is instantiated in the event of an error or placeholder
 * @author Oracle
 *
 */
public class NullSkill extends Skill {
	
	/**
	 * An empty constructor
	 */
	public NullSkill() {
		super("NULL SKILL", Type.NULL, -1, Target.NONE);
	}

	/**
	 * Does nothing but is for IAction compliance
	 */
	@Override
	boolean doIt(Pockymon caster, Pockymon... targets) {
		return false;
	}

}
