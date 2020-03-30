package Maestus.Porkyman;

public class Synthesis extends Skill {

	public Synthesis() {
		super("Synthesis", Type.GRASS, 10, Target.SELF);
	}
	
	@Override
	boolean doIt(Pockymon caster, Pockymon... targets) {
		System.out.println(caster.getNickname() + " bathes in the sunlight.");
		caster.modHP(-60);
		return true;
	}
	
}
