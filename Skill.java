package Maestus.Porkyman;

public abstract class Skill implements IAction {
	private String name;
	private Type type;
	private int maxPP;
	private Target target;
	
	public boolean doSomething(Pockymon caster, Pockymon... targets) {
		if (validate(caster)) {
			return doIt(caster, targets);
		}
		return false;
	}
	
	abstract boolean doIt(Pockymon caster, Pockymon... targets);
	
	/// Getters
	public String getName() { return name; }
	public Type getType() { return type; }
	public int getMaxPP() { return maxPP; }
	public Target getTarget() { return target; }
	
	/// Constructors
	public Skill() {
		name = "NULL";
		type = Type.NULL;
		maxPP = -1;
		target = Target.NONE;
	}
	
	public Skill(String n, Type t, int pp, Target who) {
		name = n;
		type = t;
		maxPP = pp;
		target = who;
	}
	
	public boolean validate(Object caster) {
		// TODO: Ideally check that the pokemon is healthy and awake here
		Pockymon p = (Pockymon)caster;
		if (p.checkAlive() && p.getPP(p.lookupAttack(name)) > 0)
			return yourTurn;
		else return false;
	}
	
	@Override
	public String toString() {
		String s = name;
		return s;
	}
}
