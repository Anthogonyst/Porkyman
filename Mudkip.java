package Maestus.Porkyman;

import java.util.List;

public class Mudkip extends Pockymon {

	public Mudkip(String nickname, List<Skill> skill, int team) {
		super(258, Type.WATER, Type.NULL, nickname, (int)(90 + 70*Math.random()), skill, team);
	}

	@Override
	public void speak() {
		System.out.println("Mud! ...kip.");
	}

	@Override
	public void attack(Pockymon other) {
		System.out.println(getNickname() + " kind of just barely touches " + other.getNickname() + ".");
	}

	@Override
	public String toString() {
		return "Mudkip";
	}
}
