package Maestus.Porkyman;

import java.util.List;

/**
 * The pockymon Incineroar is a fire/dark type with a bad reputation as a wrestler
 * https://bulbapedia.bulbagarden.net/wiki/Incineroar_(Pok%C3%A9mon)
 * @author Oracle
 *
 */
public class Incineroar extends Pockymon {

	/**
	 * Constructs a new Incineroar without a nickname
	 * @param skills
	 * @param team
	 */
	public Incineroar(List<Skill> skill, int team) {
		super(727, Type.FIRE, Type.DARK, "Incineroar", (int)(150 + 30*Math.random()), skill, team);
	}
	
	/**
	 * Constructs a new Incineroar with a nickname
	 * @param nickname
	 * @param skills
	 * @param team
	 */
	public Incineroar(String nickname, List<Skill> skill, int team) {
		super(727, Type.FIRE, Type.DARK, nickname, (int)(150 + 30*Math.random()), skill, team);
	}
	
	/**
	 * Imagine a big cat growl
	 */
	@Override
	public void speak() {
		System.out.println("Grroar!!");
	}
	
	/**
	 * Fanfare for flavor text, not function
	 */
	@Override
	public void attack(Pockymon other) {
		System.out.println(getNickname() + " astonishes the audience with their showmanship!");
	}
	
	/**
	 * The pockymon's species
	 */
	@Override
	public String toString() {
		return "Incineroar";
	}
}
