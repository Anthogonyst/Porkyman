package Maestus.Porkyman;

/**
 * Contains functions for actions that the pockymon is able to do
 * Implements the IAction interface to pass turns
 * @author Oracle
 *
 */
public abstract class Skill implements IAction {
	private String name;
	private Type type;
	private int maxPP;
	private Target target;
	
	/**
	 * Default constructor is the NullSkill
	 * Unusable by itself
	 */
	public Skill() {
		name = "NULL";
		type = Type.NULL;
		maxPP = -1;
		target = Target.NONE;
	}
	
	/**
	 * Constructor to generate a new skill
	 * @param skill name
	 * @param type
	 * @param max pp
	 * @param target(s)
	 */
	public Skill(String n, Type t, int pp, Target who) {
		name = n;
		type = t;
		maxPP = pp;
		target = who;
	}

	/**
	 * Encloses IAction interface with a validation method and falsification result
	 * The lack of options results in self harm
	 * @return turnComplete
	 */
	public boolean doSomething(Pockymon caster, Pockymon... targets) {
		if (validate(caster)) {
			return doIt(caster, targets);
		} else {
			System.out.println(caster.getNickname() + " tries to do something but hurts themself in the process.");
			caster.modHP(-5);
			return false;
		}
	}
	
	/**
	 * Logic for an actual skill is implemented here after it passes validation checks
	 * @param acting pockymon
	 * @param targeted pockymon
	 * @return
	 */
	abstract boolean doIt(Pockymon caster, Pockymon... targets);
	
	/**
	 * Gets the name of the skill
	 * @return skill name
	 */
	public String getName() { return name; }

	/**
	 * Gets the intended targets of the skill
	 * @return target(s)
	 */
	public Target getTarget() { return target; }
	
	/**
	 * Gets the element type of the skill
	 * Only used internally
	 * @return skill type
	 */
	Type getType() { return type; }

	/**
	 * Gets the maximum pp for skill usage count
	 * Only used for initialization
	 * @return max pp
	 */
	int getMaxPP() { return maxPP; }
	
	/**
	 * Validates this object by checking that it has enough pp to execute the skill
	 * Additionally decrements pp upon checking that the action is available
	 */
	public boolean validate(Object caster) {
		Pockymon p = (Pockymon)caster;
		int n = p.lookupAttack(name);
		
		if (p.checkAlive() && p.getPP(n) > 0) {
			p.decrementPP(n);
			return yourTurn;
		}
		else {
			System.out.println("But " + p.getNickname() + " is too tired...");
			return false;
		}
	}
	
	/**
	 * Gets the name of the skill
	 */
	@Override
	public String toString() {
		String s = name;
		return s;
	}
}
