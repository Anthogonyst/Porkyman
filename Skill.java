package Maestus.Porkyman;

public abstract class Skill implements IAction {
	private String name;
	private Type type;
	private int maxPP;
	private Target target;
	
	public boolean doSomething(Pockymon caster, Pockymon... targets) {
		if (validate(caster)) {
			return doIt(caster, targets);
		} else {
			System.out.println(caster.getNickname() + " tries to do something but hurts themself in the process.");
			caster.modHP(-5);
			return false;
		}
	}
	
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

	abstract boolean doIt(Pockymon caster, Pockymon... targets);
	
	/// Getters
	public String getName() { return name; }
	public int getMaxPP() { return maxPP; }
	public Target getTarget() { return target; }
	protected Type getType() { return type; }
	
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
	
	@Override
	public String toString() {
		String s = name;
		return s;
	}
}
