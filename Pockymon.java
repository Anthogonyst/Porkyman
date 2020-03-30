package Maestus.Porkyman;

import java.util.ArrayList;
import java.util.List;

public abstract class Pockymon {
	// Required
	private final int porkyDex;
	private final Type type1;
	private final Type type2;
	private final String nickname;
	private final int maxHP;
	private final List<Skill> skills;
	
	// Volatile
	private boolean alive;
	private int team;
	private int hp;
	private int[] pp;

	public abstract void attack(Pockymon other);
	public abstract void speak();
	
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

	public Type getType1() { return type1; }
	public Type getType2() { return type2; }
	public String getNickname() { return nickname; }
	public int getNumOfMoves() { return skills.size(); }
	public int getTeam() { return team; }
	public int getPP(int i) { if (i > 0 && i < pp.length) return pp[i]; else return -1; }
	public int[] getAllPP() { return pp; }
	
	public boolean checkAlive() {
		if (hp > 0) 
			alive = true;
		else alive = false;
		return alive;
	}

	public Skill getSkill(int i) {
		if (i >= 0 && i < skills.size()) {
			return skills.get(i);
		}
		else {
			System.out.println(getNickname() + " tried to get invalid skill index.");
			return new NullSkill();
		}
	}
	
	public int lookupAttack(String s) {
		// TODO: Test for correctness
		for (Skill k : skills) {
			if (k.getName().equals(s)) {
				return skills.indexOf(k);
			}
		}
		return -1;
	}
	
	public void modHP(int n) {
		hp += n;
		if (hp > maxHP) {
			hp = maxHP;
		} else if (hp <= 0) {
			hp = 0;
			checkAlive();
		}
	}

	@Override
	public String toString() {
		return getNickname();
	}

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
	
	public void printSkills() {
		System.out.println("Lets try this.");
		for (Skill s : skills) {
			System.out.println("Regular print of " + s);
		}
		for (int i = 0; i < skills.size(); i++) {
			System.out.println("i = " + i + " for skill " + skills.get(i));
		}
	}
}
