package Maestus.Porkyman;

import java.util.ArrayList;
import java.util.List;


public abstract class Pockymon {

	// Required components for initialization
	private final int porkyDex;
	private final Type type1;
	private final Type type2;
	private final String nickname;
	private final int maxHP;
	private final List<Skill> skills;
	
	// Volatile components for battles
	private boolean alive;
	private int team;
	private int hp;
	private int[] pp;

	/**
	 * Creates a new pockymon with all of the necessary details in its initialization
	 * @param porkyDex
	 * @param type1
	 * @param type2
	 * @param nickname
	 * @param max HP
	 * @param skill set
	 * @param team
	 */
	public Pockymon(int porkyDex, Type type1, Type type2, String nickname, int maxHP, List<Skill> skillset, int team) {
		super();
		this.porkyDex = porkyDex;
		this.type1 = type1;
		this.type2 = type2;
		this.nickname = nickname;
		this.maxHP = maxHP;
		this.team = team;
		this.alive = true;
		this.hp = maxHP;
		
		this.skills = new ArrayList<Skill>();
		Skill nullSkill = new NullSkill();
		for (Skill s : skillset) {
			if (!s.equals(nullSkill))
				this.skills.add(s);
		}

		this.pp = new int[skills.size()];
		for (int i = 0; i < skills.size(); i++) {
			pp[i] = skills.get(i).getMaxPP();
		}
	}

	/**
	 * Outputs some flavor text to the console
	 * The logic and function is not contained here
	 * @param attacked pockymon
	 */
	public abstract void attack(Pockymon other);
	
	/**
	 * Outputs the pockymon's voice lines to the console
	 */
	public abstract void speak();
	
	/**
	 * Gets the pockymon's nickname
	 * @return nickname
	 */
	public String getNickname() { return nickname; }
	
	/**
	 * Gets the number of moves this pockymon knows
	 * @return moves count
	 */
	public int getNumOfMoves() { return skills.size(); }
	
	/**
	 * The team number that this pockymon is fighting to protecc
	 * @return team number
	 */
	public int getTeam() { return team; }
	
	/**
	 * Gets the pp value of a specific move
	 * @param skill slot
	 * @return pp value
	 */
	public int getPP(int i) {
		if (i >= 0 && i < pp.length) 
			return pp[i]; 
		else return -1;
	}
	
	/**
	 * Verifies whether or not that the pockymon is still alive
	 * @return isAlive
	 */
	public boolean checkAlive() {
		if (hp > 0) 
			alive = true;
		else alive = false;
		return alive;
	}

	/**
	 * Returns the skill in a particular slot
	 * @param skill slot
	 * @return skill
	 */
	public Skill getSkill(int i) {
		if (i >= 0 && i < skills.size()) {
			return skills.get(i);
		}
		else {
			return new NullSkill();
		}
	}
	
	/**
	 * Terminates the battle for immediate stops
	 */
	public void quitFighting() {
		hp = 0;
		checkAlive();
	}
	
	/**
	 * Modifies the hp value of the pockymon
	 * Negative values hurts, positive values heal
	 * @param magnitude
	 */
	void modHP(int n) {
		hp += n;
		if (hp > maxHP) {
			hp = maxHP;
		} else if (hp <= 0) {
			hp = 0;
			checkAlive();
		}
	}

	/**
	 * Gets the first type of a pockymon
	 * @return type one
	 */
	Type getType1() { return type1; }
	
	/**
	 * Gets the second type of a pockymon
	 * @return type two
	 */
	Type getType2() { return type2; }
	
	/**
	 * Consumes pp when a move is executed
	 * @param skill slot
	 * @return isValidated
	 */
	boolean decrementPP(int i) {
		if (pp[i] > 0) {
			pp[i] -= 1;
			return true;
		}
		else return false;
	}
	
	/**
	 * Looks up a skill by name and returns its skill slot
	 * @param skill name
	 * @return skill slot
	 */
	int lookupAttack(String s) {
		for (Skill k : skills) {
			if (k.getName().equals(s)) {
				return skills.indexOf(k);
			}
		}
		return -1;
	}
	
	/**
	 * Two options for printing, either long or short
	 * Long print (true) displays all stats and skills
	 * Short print (false) displays all stats but not skills
	 * @param long print
	 * @return String output
	 */
	public String print(boolean prettyPrint) {
		if (prettyPrint) {
			String s = "#" + porkyDex + ":  " + nickname + "\t\t" + type1 + " | " + type2 + "\t\tHP:  " + hp + "/" + maxHP + "\n";
			for (int i = 0; i < skills.size(); i++) {
				if (Math.floorMod(i, 2) == 0)
					s += "\n";
				s += String.format("|%-22.20s|", skills.get(i)) + "\t" + pp[i] + "\t\t";
			}
			s += "\n";
			return s;
		} else {
			String s = "#" + porkyDex + ":  " + nickname + "\t\t" + type1 + " | " + type2 + "\t\tHP:  " + hp + "/" + maxHP + "\n";
			return s;
		}
	}
	
	/**
	 * Subclasses are intended to return species from this method
	 * But for completeness sake, this returns the nickname of the pockymon if not overriden
	 */
	@Override
	public String toString() {
		return getNickname();
	}
}
