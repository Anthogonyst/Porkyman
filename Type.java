package Maestus.Porkyman;

public enum Type {
	NORMAL(0),
	FIRE(1),
	WATER(2),
	GRASS(3),
	ELECTRIC(4),
	ICE(5),
	FIGHTING(6),
	POISON(7),
	GROUND(8),
	FLYING(9),
	PSYCHIC(10),
	BUG(11),
	ROCK(12),
	GHOST(13),
	DRAGON(14),
	DARK(15),
	STEEL(16),
	FAIRY(17),
	NULL(-1);

	private final int type;
	static int[][] eff;
	static {
		eff = new int[18][18];
		for (int i = 0; i < 18; i++) {
			for (int j = 0; j < 18; j++) {
				eff[i][j] = 2;
			}
		}
		
		eff[0][12] = 1; eff[0][13] = 0; eff[0][16] = 1;
		eff[1][1] = 1; eff[1][2] = 1; eff[1][3] = 4; eff[1][5] = 4; eff[1][11] = 4; eff[1][12] = 1; eff[1][14] = 1; eff[1][16] = 4;
		eff[2][1] = 4; eff[2][2] = 1; eff[2][3] = 1; eff[2][9] = 4; eff[2][13] = 4; eff[2][15] = 1;
		
		
	}
	
	Type(int n) {
		if (n < 0 || n > 17)
			n = -1;
		
		type = n;
	}
	
	public int getVal() {
		return type;
	}
	
	public float effective(Type attack, Type def1, Type def2) {
		float mult = 1f;
		mult = effective(mult, attack, def1);
		mult = effective(mult, attack, def2);
		return mult;
	}
	
	public float effective(Type attack, Type[] def) {
		return effective(1f, attack, def);
	}
	
	public float effective(Type attack, Type def1, Type[] def2) {
		float mult = effective(1f, attack, def1);
		for (Type t : def2) {
			mult = effective(mult, attack, t);
		}
		return mult;
	}
	
	public float effective(float mult, Type attack, Type def) {
		if (attack.getVal() == -1 || def.getVal() == -1)
			return mult * eff[attack.getVal()][def.getVal()] / 2;
		else return mult;
	}
	
	public float effective(float mult, Type attack, Type[] def) {
		for (Type t : def) {
			mult = effective(mult, attack, t);
		}
		return mult;
	}
}