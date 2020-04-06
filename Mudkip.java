package Maestus.Porkyman;

import java.util.List;

/**
 * This piece of meme fuel is a water type pockymon that is popular on the internet
 * https://bulbapedia.bulbagarden.net/wiki/Mudkip_(Pok%C3%A9mon)
 * @author Oracle
 *
 */
public class Mudkip extends Pockymon {

	/**
	 * Constructs a new Mudkip without a nickname
	 * @param skills
	 * @param team
	 */
	public Mudkip(List<Skill> skill, int team) {
		super(258, Type.WATER, Type.NULL, "Mudkip", (int)(90 + 70*Math.random()), skill, team);
	}
	
	/**
	 * Constructs a new mudkip
	 * @param nickname
	 * @param skills
	 * @param team
	 */
	public Mudkip(String nickname, List<Skill> skill, int team) {
		super(258, Type.WATER, Type.NULL, nickname, (int)(90 + 70*Math.random()), skill, team);
	}

	/**
	 * Imagine the most annoying sound in the world
	 */
	@Override
	public void speak() {
		// https://www.youtube.com/watch?v=3DkqMjfqqPc
		System.out.println("Mud! ...kip.");
	}

	/**
	 * Fanfare for flavor text, not function
	 */
	@Override
	public void attack(Pockymon other) {
		System.out.println(getNickname() + " kind of just barely touches " + other.getNickname() + ".");
	}

	/**
	 * The pockymon's species
	 */
	@Override
	public String toString() {
		return "Mudkip";
	}
}
