package Maestus.Porkyman;

/**
 * Fire type skill for Typhlosion
 * @author Oracle
 *
 */
public class Eruption extends Skill {

	/**
	 * Constructs new skill for pockymon
	 */
	public Eruption() {
		super("Eruption", Type.FIRE, 15, Target.RIVALS);
	}
	
	/**
	 * Specifies how to execute skill
	 * Hits multiple targets
	 * @return turnComplete
	 */
	@Override
	boolean doIt(Pockymon caster, Pockymon... targets) {
		for (Pockymon p : targets) {
			int damage = calculateDamage(Type.effective(this.getType(), p.getType1(), p.getType2()));
			System.out.println(p.getNickname() + " is set ablaze for " + damage + " health.");
			
			p.modHP(damage);
		}
		return true;
	}
	
	/**
	 * Skill is mostly flat fire damage with slightly random deviation
	 * @param effectiveness
	 * @return damage
	 */
	private int calculateDamage(float f) {
		if (f <= .51f) {
			System.out.println("It's not very effective...");
		} else if (f >= 1.99f) {
			System.out.println("It's super effective!");
		}
		
		int damage = -50;
		damage *= (f + Math.random()/10);
		return damage;
	}
}
