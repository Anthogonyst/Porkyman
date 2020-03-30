package Maestus.Porkyman;

import java.util.List;

public class Incineroar extends Pockymon {

	public Incineroar(String nickname, List<Skill> skill, int team) {
		super(727, Type.FIRE, Type.DARK, nickname, (int)(150 + 30*Math.random()), skill, team);
	}
	
	@Override
	public void speak() {
		System.out.println("Grroar!!");
	}
	
	@Override
	public void attack(Pockymon other) {
		System.out.println(getNickname() + " astonishes the audience with their showmanship!");
	}
	
	@Override
	public String toString() {
		return "Incineroar";
	}
}
