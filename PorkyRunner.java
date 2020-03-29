package Maestus.Porkyman;

import java.util.ArrayList;
import java.util.List;

public class PorkyRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final List<Skill> doStuff = new ArrayList<Skill>();
		doStuff.add(new StringSkill("Headbutt"));
		doStuff.add(new StringSkill("Tail Whip"));
		doStuff.add(new StringSkill("Bite"));
		Pockymon g = new Typhlosion("Tyco", doStuff, 0);
		final List<Skill> doMore = new ArrayList<Skill>();
		doMore.add(new StringSkill());
		doMore.add(new StringSkill());
		doMore.add(new StringSkill());
		Pockymon l = new Typhlosion("Oh no", doMore, 1);
		Player good = new HumanPlayer(g, new NullItem());
		Player luck = new ComputerPlayer(l, new NullItem());
		PockymonBattle DEATH = new PockymonBattle(good, luck);
		DEATH.start();
	}

}
