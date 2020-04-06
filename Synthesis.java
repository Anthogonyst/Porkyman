package Maestus.Porkyman;

/**
 * Grass type skill for Rowlet
 * @author Oracle
 *
 */
public class Synthesis extends Skill {

	/**
	 * Constructs new skill for pockymon
	 */
	public Synthesis() {
		super("Synthesis", Type.GRASS, 10, Target.SELF);
	}
	
	/**
	 * Specifies how to execute skill
	 * Heals user by flat amount unmodified by type
	 * @return turnComplete
	 */
	@Override
	boolean doIt(Pockymon caster, Pockymon... targets) {
		System.out.println(caster.getNickname() + " bathes in the sunlight.");
		caster.modHP(-60);
		return true;
	}
	
}
