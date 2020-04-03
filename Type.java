package Maestus.Porkyman;

enum Type {
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
		eff[3][1] = 1; eff[3][2] = 4; eff[2][3] = 1; eff[3][8] = 1; eff[2][9] = 4; eff[3][10] = 1; eff[3][12] = 1; eff[3][13] = 4; eff[2][15] = 1; eff[3][17] = 1;
		eff[4][2] = 4; eff[4][3] = 1; eff[4][4] = 1; eff[4][8] = 0; eff[4][9] = 4; eff[4][15] = 1;
		eff[5][1] = 1; eff[5][2] = 1; eff[5][3] = 4; eff[5][5] = 1; eff[5][8] = 4; eff[5][9] = 4; eff[5][14] = 4; eff[5][16] = 1;
		eff[6][0] = 4; eff[6][5] = 4; eff[6][7] = 1; eff[6][9] = 1; eff[6][10] = 1; eff[6][11] = 1; eff[6][12] = 4; eff[6][13] = 0; eff[6][15] = 4; eff[6][16] = 4; eff[6][17] = 1;
		eff[7][3] = 4; eff[7][7] = 1; eff[7][8] = 1; eff[7][12] = 1; eff[7][13] = 1; eff[7][16] = 0; eff[7][17] = 4;
		eff[8][1] = 4; eff[8][3] = 1; eff[8][4] = 4; eff[8][7] = 4; eff[8][9] = 0; eff[8][11] = 1; eff[8][12] = 4; eff[8][16] = 4;
		eff[9][3] = 4; eff[9][4] = 1; eff[9][6] = 4; eff[9][11] = 4; eff[9][12] = 1; eff[9][16] = 4;
		eff[10][6] = 4; eff[10][7] = 4; eff[10][10] = 1; eff[10][15] = 0; eff[10][16] = 1;
		eff[11][1] = 1; eff[11][3] = 4; eff[11][6] = 1; eff[11][7] = 1; eff[11][9] = 1; eff[11][10] = 4; eff[11][13] = 1; eff[11][15] = 4; eff[11][16] = 1; eff[11][17] = 1;
		eff[12][1] = 4; eff[12][5] = 4; eff[12][6] = 1; eff[12][8] = 1; eff[12][9] = 4; eff[12][11] = 4; eff[12][16] = 1;
		eff[13][0] = 0; eff[13][10] = 4; eff[13][13] = 4; eff[13][15] = 1;
		eff[14][14] = 4; eff[14][16] = 1; eff[14][17] = 0;
		eff[15][6] = 1; eff[15][10] = 4; eff[15][13] = 4; eff[15][15] = 1; eff[15][17] = 1;
		eff[16][1] = 1; eff[16][2] = 1; eff[16][4] = 1; eff[16][5] = 4; eff[16][12] = 4; eff[16][16] = 1; eff[16][17] = 4;
		eff[17][1] = 1; eff[17][6] = 4; eff[17][7] = 1; eff[17][14] = 4; eff[17][15] = 4; eff[17][16] = 1;
		
	}
	
	Type(int n) {
		if (n < 0 || n > 17)
			n = -1;
		
		type = n;
	}
	
	public int getVal() {
		return type;
	}
	
	public static float effective(Type attack, Type def1, Type def2) {
		float mult = 1f;
		mult = effective(mult, attack, def1);
		mult = effective(mult, attack, def2);
		return mult;
	}
	
	public static float effective(Type attack, Type[] def) {
		return effective(1f, attack, def);
	}
	
	public static float effective(Type attack, Type def1, Type[] def2) {
		float mult = effective(1f, attack, def1);
		for (Type t : def2) {
			mult = effective(mult, attack, t);
		}
		return mult;
	}
	
	public static float effective(float mult, Type attack, Type def) {
		if (attack.getVal() != -1 &&
				def.getVal() != -1)
			return mult * eff[attack.getVal()][def.getVal()] / 2;
		else return mult;
	}
	
	public static float effective(float mult, Type attack, Type[] def) {
		for (Type t : def) {
			mult = effective(mult, attack, t);
		}
		return mult;
	}
}