package Maestus.Porkyman;

import java.util.ArrayList;

public abstract class Porkyman {
	private final static int NUM_OF_MOVES = 4;
	private final static int NUM_OF_STATS = 6;
	
	// Required
	private final int porkyDex;
	private final Type type1;
	
	// Optional
	private final Type[] type2;
	private String nickname;
	private int[] stats;
	private Skill[] skillz;
	private int[] skills;
	
	// Volatile
	private int hp;
	private int team;
	private int[] pp;
	
	private Porkyman(PorkymanBuilder b) {
		this.porkyDex = b.porkyDex;
		this.type1 = b.type1;
		this.type2 = b.type2;
		this.nickname = b.nickname;
		this.stats = b.stats;
		//this.skillz;
		//this.skills;
		//this.hp = b.stats.HP;
		//this.team = 0;
		//this.pp;
		
	}
	
	/*public Porkyman() {
		super();
		pp = new int[NUM_OF_MOVES];
		hp = 1;
	}
	
	public Porkyman(String n) {
		super();
		nickname = n;
		System.out.println(hp);
	}*/
	
	protected static abstract class PorkymanBuilder {
		//https://cindystlai.wordpress.com/2017/04/20/adopting-builder-pattern-with-abstract-class/
		// Required params
		private final int porkyDex;
		private final Type type1;
		
		// Optional params
		private String nickname;
		private Type[] type2;
		private int[] stats;
		private int[] skills;
		
		public PorkymanBuilder(int a, Type b) {
			porkyDex = a;
			type1 = b;
		}

		public PorkymanBuilder nickname(String s) {
			nickname = s;
			return this;
		}
		
		public PorkymanBuilder typeTwo(Type t) {
			if (t.equals(Type.NULL) || t.equals(type1))
				return this;
			else type2 = new Type[] { t };
			return this;
		}
		
		public PorkymanBuilder typeTwo(Type[] t) {
			// TODO: Delete this cuz this was a joke
			for (int i = 0; i < t.length; i++) {
				if (t[i].equals(Type.NULL) || t[i].equals(type1))
					return this;
				for (int j = 0; j < i; j++) {
					if (t[i].equals(t[j]))
						return this;
				}
			}
			type2 = t;
			return this;
		}
		
		public PorkymanBuilder stats(int[] s) {
			if (s.length == NUM_OF_STATS)
				stats = s;
			return this;
		}
		
		public PorkymanBuilder skills(int[] s) {
			if (s.length == NUM_OF_MOVES)
				skills = s;
			return this;
		}
		
		public Porkyman build() {
			Porkyman p = specifics(this);
			validate(p);
			return p;
		}
		
		abstract Porkyman specifics(PorkymanBuilder p);
		
		abstract void validate(Porkyman p);
	}
	
	protected abstract Porkyman Porkyman(PorkymanBuilder porkymanBuilder);

	public int getPorkyDex() { return porkyDex; }
	public String getNickname() { return nickname; }
	public int[] getSkillSet() { return skills; }
	public int getTeam() { return team; }
	public void setTeam(int t) { if (Math.floorMod(t,  1) == 1) team = t; }
	
	public int getPP(int n) {
		if (n < NUM_OF_MOVES && n >= 0)
			return pp[n];
		else return -1;
	}
	
	public int getSkill(int n) {
		if (n < NUM_OF_MOVES && n >= 0)
			return skills[n];
		else return -1;
	}
	
	public Type[] getType() {
		// TODO: Verify integrity of this equals operation
		if (type2[0].equals(Type.NULL)) {
			Type[] t = { type1 };
			return t;
		} else {
			Type[] t = new Type[type2.length + 1];
			for (int i = 0; i < type2.length; i++) {
				t[i] = type2[i];
			}
			
			t[type2.length] = type1;
			return t;
		}
	}
	
	public boolean chooseSkill(int n) {
		if (n < NUM_OF_MOVES && n >= 0 && pp[n] > 0) {
			pp[n]--;
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String toString() {
		String s = "#" + porkyDex + ":  " + nickname + "\t\t" + type1 + " | " + type2 + "\t\tHP:  " + hp + "/" + stats[0] + "\n";
		for (int i = 1; i < 6; i++) {
			s += stats[i] + "\t";
		}
		s += "\n" + skills[0] + "\t" + pp[0] + "\t\t" + skills[1] + "\t" + pp[1] + "\n" + skills[2] + "\t" + pp[2] + "\t\t"
				+ skills[3] + "\t" + pp[3];
		return s;
	}
}


/*
 * You want to declare T as extends AbstractBuilder<T> in AbstractBuilder.

Use an abstract protected method to get this of type T.

abstract class AbstractBuilder<T extends AbstractBuilder<T>> {
protected abstract T getThis();

public T foo() {
// set some property
return getThis();
}
}


class TheBuilder extends AbstractBuilder<TheBuilder> {
@Override protected TheBuilder getThis() {
return this;
}
...
}
Alternatively, drop the generic type parameter, rely on covariant return types and make the code cleaner for clients 
(although usually they would be using TheBuilder rather than the largely implementation detail of the base class), 
if making the implementation more verbose.
 */

/*
https://docs.oracle.com/javase/specs/jls/se8/html/jls-8.html#jls-8.9
*/

/*public Porkyman(String n, int a, Type b, Type c, int d, int e, int f, int g, int h, int i, int j, int k, int l, int m) {
	//#,Name,Type 1,Type 2,Total,HP,Attack,Defense,Sp. Atk,Sp. Def,Speed,Generation,Legendary
	porkyDex = a;
	type1 = b;
	type2 = c;
	hp = d;
	int[] temp = { d, e, f, g, h, i };
	stats = temp;
	int[] temp2 = { j, k, l, m };
	skills = temp2;
	pp = temp2;
	team = 0;
}*/
