package Maestus.Porkyman;

/**
 * Dark type skill for Incineroar
 * @author Oracle
 *
 */
public class DarkestLariat extends Skill {

	/**
	 * Constructs new skill for pockymon
	 */
	public DarkestLariat() {
		super("Darkest Lariat", Type.DARK, 10, Target.FOE);
	}
	
	/**
	 * Specifies how to execute skill
	 * Hits single target
	 * @return turnComplete
	 */
	@Override
	boolean doIt(Pockymon caster, Pockymon... targets) {
		int damage = calculateDamage(Type.effective(this.getType(), targets[0].getType1(), targets[0].getType2()));
		System.out.println(targets[0].getNickname() + " is completely bewildered and feels " + damage + " hp less healthy.");
		targets[0].modHP(damage);
		
		return true;
	}
	
	/**
	 * Highly random dark damage over a very large range
	 * @param effectiveness
	 * @return damage
	 */
	private int calculateDamage(float f) {
		if (f <= .51f) {
			System.out.println("It's not very effective...");
		} else if (f >= 1.99f) {
			System.out.println("It's super effective!");
		}
		
		int damage = (int)(-20 - 200*Math.random());
		damage *= f;
		return damage;
	}
}
