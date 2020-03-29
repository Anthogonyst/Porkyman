package Maestus.Porkyman;

import java.util.Random;

public class StringSkill extends Skill {

	public StringSkill() {
		super(randomName(), Type.NORMAL, 20);
	}
	
	public StringSkill(String n) {
		super(n, Type.NORMAL, 20);
	}
	
	@Override
	protected boolean doIt(Pockymon caster, Pockymon... targets) {
		for (Pockymon p : targets) {
			int damage = calculateDamage(Type.effective(Type.NORMAL, p.getType1(), p.getType2()));
			System.out.println(p.getNickname() + " catches ow in the magnitude of " + damage);
			p.modHP(damage);
		}
		return true;
	}
	
	private int calculateDamage(float f) {
		int sum_char = 0;
		String s = getName();
		
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
	
	private static String randomName() {
		String[] s = { "Headbutt", "Stomp", "Hyper Fang", "Body Slam", "Bite", "Swipe", "Scratch", "Lick", "Demean", "Rollout" };
		int n = (int)(Math.floor(10 * Math.random()) % 10);
		return s[n];
		
	}
}
