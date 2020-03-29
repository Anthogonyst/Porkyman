package Maestus.Porkyman;

import java.util.List;

public abstract class Pockymon {
	private final static int NUM_OF_MOVES = 4;
	
	// Required
	private final int porkyDex;
	private final Type type1;
	private final Type type2;
	private String nickname;
	private int maxHP;
	private final List<Skill> skills;
	
	// Volatile
	private boolean alive;
	private int team;
	private int hp;
	private int[] pp;

	public abstract void attack(Pockymon other);
	public abstract void speak();
	
	public Pockymon(int porkyDex, Type type1, Type type2, String nickname, int maxHP, List<Skill> skills, int team) {
		super();
		this.porkyDex = porkyDex;
		this.type1 = type1;
		this.type2 = type2;
		this.nickname = nickname;
		this.maxHP = maxHP;
		this.team = team;
		this.alive = true;
		this.hp = maxHP;
		
		this.pp = new int[skills.size()];
		for (int i = 0; i < skills.size(); i++) {
			pp[i] = 20;
		}
		
		this.skills = skills;
		/*this.skills = new List<Skill>();
		Skill nullSkill = new NullSkill();
		for(Skill s : skills) {
			if (!s.equals(nullSkill))
				this.skills.add(s);
		}*/
	}
	
	public int getPorkyDex() { return porkyDex; }
	public Type getType1() { return type1; }
	public Type getType2() { return type2; }
	public String getNickname() { return nickname; }
	public int getMaxHP() { return maxHP; }
	public int getNumOfMoves() { return skills.size(); }
	public List<Skill> getAllSkills() { return skills; }
	public int getHp() { return hp; }
	public int getTeam() { return team; }
	public int getPP(int i) { if (i > 0 && i < pp.length) return pp[i]; else return -1; }
	public int[] getAllPP() { return pp; }
	public boolean checkAlive() { if (hp > 0) alive = true; return alive; }

	public Skill getSkill(int i) {
		if (i >= 0 && i < skills.size())
			return skills.get(i);
		else return new NullSkill();
	}
	
	public int lookupAttack(String s) {
		// TODO: Test for correctness
		Skill choice = new NullSkill();
		for (Skill k : skills) {
			if (k.getName().equals(s)) {
				choice = k;
				return skills.indexOf(k);
			}
		}
		return -1;
	}
	
	public void modHP(int n) { hp += n; }
	
	public String toString() {
		String s = "#" + porkyDex + ":  " + nickname + "\t\t" + type1 + " | " + type2 + "\t\tHP:  " + hp + "/" + maxHP + "\n";
		for (int i = 0; i < skills.size(); i++) {
			if (Math.floorMod(i, 2) == 0)
				s += "\n";
			s += String.format("|%-22.20s|", skills.get(i)) + "\t" + pp[i] + "\t\t";
		}
		s += "\n";
		return s;
	}
}
