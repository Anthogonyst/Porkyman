package Maestus.Porkyman;

public class NullSkill extends Skill {
	
	public NullSkill() {
		super("NULL SKILL", Type.NULL, -1, Target.NONE);
	}

	@Override
	boolean doIt(Pockymon caster, Pockymon... targets) {
		return false;
	}

}
