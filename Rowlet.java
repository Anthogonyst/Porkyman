package Maestus.Porkyman;

import java.util.List;

/**
 * Rowlet is a grass type pockymon with self healing capacity
 * https://bulbapedia.bulbagarden.net/wiki/Rowlet_(Pok%C3%A9mon)
 * @author Oracle
 *
 */
public class Rowlet extends Pockymon {

	/**
	 * Constructs a new Rowlet without a nickname
	 * @param skills
	 * @param team
	 */
	public Rowlet(List<Skill> skill, int team) {
		super(722, Type.GRASS, Type.NULL, "Rowlet", (int)(200 + 10*Math.random()), skill, team);
	}
	
	/**
	 * Constructs a new Rowlet with a nickname
	 * @param nickname
	 * @param skills
	 * @param team
	 */
	public Rowlet(String nickname, List<Skill> skill, int team) {
		super(722, Type.GRASS, Type.NULL, nickname, (int)(200 + 10*Math.random()), skill, team);
	}
	
	/**
	 * Imagine an owl in a tree
	 */
	@Override
	public void speak() {
		System.out.println("Hoot hoot.");
	}
	
	/**
	 * Fanfare for flavor text, not function
	 */
	@Override
	public void attack(Pockymon other) {
		System.out.println(getNickname() + " pecks the brains out of " + other.getNickname() + ".");
	}
	
	/**
	 * The pockymon's species
	 */
	@Override
	public String toString() {
		return "Rowlet";
	}
}
