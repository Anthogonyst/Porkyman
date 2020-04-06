package Maestus.Porkyman;

/**
 * Water type skill for Mudkip
 * @author Oracle
 *
 */
public class BubbleBeam extends Skill {

	/**
	 * Constructs new skill for pockymon
	 */
	public BubbleBeam() {
		super("Bubble Beam", Type.WATER, 25, Target.FOE);
	}
	
	/**
	 * Specifies how to execute skill
	 * Hits one target
	 * @return turnComplete
	 */
	@Override
	boolean doIt(Pockymon caster, Pockymon... targets) {
		int damage = calculateDamage(Type.effective(this.getType(), targets[0].getType1(), targets[0].getType2()));
		System.out.println(targets[0].getNickname() + " feels the water pressure of " + damage + " psi.");
		targets[0].modHP(damage);
		
		return true;
	}
	
	/**
	 * Skill is flat water damage
	 * @param effectiveness
	 * @return damage
	 */
	private int calculateDamage(float f) {
		if (f <= .51f) {
			System.out.println("It's not very effective...");
		} else if (f >= 1.99f) {
			System.out.println("It's super effective!");
		}
		
		int damage = -80;
		damage *= f;
		return damage;
	}
}
