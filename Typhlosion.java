package Maestus.Porkyman;

import java.util.List;

/**
 * The pockymon Typhlosion is a fire type with wide area of effect damage
 * It is also the first pockymon created and is initially used for error types
 * https://bulbapedia.bulbagarden.net/wiki/Typhlosion_(Pok%C3%A9mon)
 * @author Oracle
 *
 */
public class Typhlosion extends Pockymon {

	/**
	 * Constructs a new Typhlosion without a nickname
	 * @param skills
	 * @param team
	 */
	public Typhlosion(List<Skill> skill, int team) {
		super(157, Type.FIRE, Type.NULL, "Typhlosion", (int)(120 + 60*Math.random()), skill, team);
	}
	
	/**
	 * Constructs a new typhlosion
	 * @param nickname
	 * @param skills
	 * @param team
	 */
	public Typhlosion(String nickname, List<Skill> skill, int team) {
		super(157, Type.FIRE, Type.NULL, nickname, (int)(120 + 60*Math.random()), skill, team);
	}

	/**
	 * Imagine a big, tired badger-guy making a creepy laugh
	 */
	@Override
	public void speak() {
		System.out.println("Bwehehe.");
	}

	/**
	 * Fanfare for flavor text, not function
	 */
	@Override
	public void attack(Pockymon other) {
		System.out.println(getNickname() + " burns up the competition! " + other.getNickname() + " can't handle the heat!");
	}

	/**
	 * The pockymon's species
	 */
	@Override
	public String toString() {
		return "Typhlosion";
	}
}
