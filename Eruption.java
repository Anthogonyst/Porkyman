package Maestus.Porkyman;

public class Eruption extends Skill {

	public Eruption() {
		super("Eruption", Type.FIRE, 15, Target.RIVALS);
	}
	
	@Override
	boolean doIt(Pockymon caster, Pockymon... targets) {
		for (Pockymon p : targets) {
			int damage = calculateDamage(Type.effective(Type.FIRE, p.getType1(), p.getType2()));
			System.out.println(p.getNickname() + " is set ablaze for " + damage + " health.");
			
			p.modHP(damage);
		}
		return true;
	}
	
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
