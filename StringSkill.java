package Maestus.Porkyman;

/**
 * Normal type move to fill in generic moves
 * Used by many pockymon under different "names"
 * @author Oracle
 *
 */
public class StringSkill extends Skill {

	/**
	 * Constructs new skill for pockymon with a random name
	 */
	public StringSkill() {
		super(randomName(), Type.NORMAL, 20, Target.FOE);
	}
	
	/**
	 * Constructs a new skill for pockymon with a pre-decided name
	 * @param name
	 */
	public StringSkill(String n) {
		super(n, Type.NORMAL, 20, Target.FOE);
	}
	
	/**
	 * Specifies how to execute skill
	 * Does damage based on the average char value of the name which may be randomly generated
	 * Hits single target
	 */
	@Override
	boolean doIt(Pockymon caster, Pockymon... targets) {
		int damage = calculateDamage(Type.effective(this.getType(), targets[0].getType1(), targets[0].getType2()));
		System.out.println(targets[0].getNickname() + " catches ow in the magnitude of " + damage + " damage.");
		targets[0].modHP(damage);
		
		return true;
	}
	
	/**
	 * The maximum damage is determined by the average ascii value of the attack's String name
	 * By taking a bell curve, the damage has a normalizing effect and a distinct range of advantage
	 * @param effectiveness
	 * @return damage
	 */
	private int calculateDamage(float f) {
		int sum_char = 0;
		String s = getName();
		
		if (f <= .51f) {
			System.out.println("It's not very effective...");
		} else if (f >= 1.99f) {
			System.out.println("It's super effective!");
		}
		
		// Take average of ascii values
		for (int i = 0; i < s.length(); i++) {
			sum_char += (int)s.charAt(i);
		}
		sum_char *= f / s.length();
		
		// Negative bell curve
		sum_char = (int)Math.floor(-40*Math.exp(-.025*Math.pow(sum_char-105, 2)) - 30);
		
		// The strongest letter is s because sssssnakes are powerful
		return sum_char;
	}
	
	/**
	 * If default constructed, picks a random name from the list to assign as the attack name
	 * @return name
	 */
	private static String randomName() {
		String[] s = { "Headbutt", "Stomp", "Hyper Fang", "Body Slam", "Bite", "Swipe", "Scratch", "Lick", "Demean", "Rollout" };
		int n = (int)(Math.floor(10 * Math.random()) % 10);
		return s[n];
		
	}
}
