package Maestus.Porkyman;

import java.util.List;

public class Rowlet extends Pockymon {

	public Rowlet(String nickname, List<Skill> skill, int team) {
		super(722, Type.GRASS, Type.NULL, nickname, (int)(200 + 10*Math.random()), skill, team);
	}
	
	@Override
	public void speak() {
		System.out.println("Hoot hoot.");
	}
	
	@Override
	public void attack(Pockymon other) {
		System.out.println(getNickname() + " pecks the brains out of " + other.getNickname() + ".");
	}
	
	@Override
	public String toString() {
		return "Rowlet";
	}
}
