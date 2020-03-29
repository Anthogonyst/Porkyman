package Maestus.Porkyman;

public class NullSkill extends Skill {
	
	public NullSkill() {
		super("NULL SKILL", Type.NULL, -1);
	}

	@Override
	boolean doIt(Pockymon caster, Pockymon... targets) {
		return false;
	}

}
