package Maestus.Porkyman;

import java.util.List;

public class Typhlosion extends Pockymon {

	public Typhlosion(String nickname, List<Skill> skill, int team) {
		super(157, Type.FIRE, Type.NULL, nickname, (int)(120 + 60*Math.random()), skill, team);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void speak() {
		System.out.println("Bwehehe.");
	}

	@Override
	public void attack(Pockymon other) {
		System.out.println(getNickname() + " burns up the competition! " + other + " can't handle the heat!");
	}

}
