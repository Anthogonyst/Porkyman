package Maestus.Porkyman;

public abstract class Skill implements IAction {
	private String name;
	private Type type;
	private int maxPP;
	
	public boolean doSomething(Pockymon caster, Pockymon... targets) {
		if (validate()) {
			return doIt(caster, targets);
		}
		return validate();
		
		/*
		 * 
		IAction temp;
		validateChoice();
		temp = skills.get(i);
		temp.doSomething(this, this, other);
		speak();
		 */
	}
	
	abstract boolean doIt(Pockymon caster, Pockymon... targets);
	
	/// Getters
	public String getName() { return name; }
	public Type getType() { return type; }
	public int getMaxPP() { return maxPP; }
	
	/// Constructors
	public Skill() {
		name = "NULL";
		type = Type.NULL;
		maxPP = -1;
	}
	
	public Skill(String n, Type t, int pp) {
		name = n;
		type = t;
		maxPP = pp;
	}
	
	public boolean validate() {
		// TODO: Ideally check that the pokemon is healthy and awake here
		return yourTurn;
	}
	
	@Override
	public String toString() {
		String s = name;
		return s;
	}
}
