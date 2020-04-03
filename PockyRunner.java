package Maestus.Porkyman;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PockyRunner {

	private static Scanner sn;
	private static boolean askNickname;
	private static int[] availablePockymon = { 157, 258, 722, 727 };
	private static String[] availablePockymonNames = { "Typhlosion", "Mudkip", "Rowlet", "Incineroar" };
	
	public static void main(String[] args) {
		List<Player> theHungerGames = new LinkedList<Player>();
		sn = new Scanner(System.in);
		int team = 0;
		askNickname = true;
		
		System.out.println("Welcome to Pockymon Coliseum! How many players are we expecting today?");
		int n = requestNum();
		for (int i = 0; i < n; i++) {
			theHungerGames.add(makeHumanPlayer(team));
			team = (team + 1) % 2;
		}
		System.out.println("Ah, excellent! And how many competitors are we expecting?");
		n = requestNum();
		for (int i = 0; i < n; i++) {
			theHungerGames.add(makeComputerPlayer(team));
			team = (team + 1) % 4;
		}
		System.out.println("Alrighties, that's all of the questions for now. May luck smile on you today.");
		
		Player[] mayTheOdds = new Player[theHungerGames.size()];
		for (int i = 0; i < theHungerGames.size(); i++) {
			mayTheOdds[i] = theHungerGames.get(i);
		}
		
		PockymonBattle DEATH = new PockymonBattle(mayTheOdds);
		DEATH.start();
	}

	private static Player makeHumanPlayer(int team) {
		int pokeball = requestPockymon();
		askNickname = true;
		return new HumanPlayer(generatePockymon(pokeball, team), new HiPotion());
	}
	
	private static Player makeComputerPlayer(int team) {
		int rand = (int)(Math.ceil(availablePockymon.length * Math.random()) % availablePockymon.length);
		// Maybe naming the enemy's pokemon would be funny?
		askNickname = false;
		return new ComputerPlayer(generatePockymon(availablePockymon[rand], team), new HiPotion());
	}
	
	private static Pockymon generatePockymon(int n, int team) {
		String nickname = "";
		final List<Skill> doStuff = new LinkedList<Skill>();
		doStuff.add(new StringSkill());
		doStuff.add(new StringSkill());
		doStuff.add(new StringSkill());
		
		if (askNickname)
			nickname = requestNickname();
		
		switch (n) {
		case 157: {
			if (nickname.equals(""))
				nickname = "Tyco";
			
			doStuff.add(new Eruption());
			return new Typhlosion(nickname, doStuff, team);
		}
		case 258: {
			if (nickname.equals(""))
				nickname = "Mudkipz";
			
			doStuff.add(new BubbleBeam());
			return new Mudkip(nickname, doStuff, team);
		}
		case 722: {
			if (nickname.equals(""))
				nickname = "Birb";
			
			doStuff.add(new Synthesis());
			return new Rowlet(nickname, doStuff, team);
		}
		case 727: {
			if (nickname.equals(""))
				nickname = "Barcat Barcat";
			
			doStuff.add(new DarkestLariat());
			return new Incineroar(nickname, doStuff, team);
		}
		default: {
			doStuff.add(new Eruption());
			return new Typhlosion("Typhlosion", doStuff, team);	
		}
		}
	}
	
	private static void printChoices() {
		String s = "";
		for (int i = 0; i < availablePockymon.length; i++) {
			s += "#" + availablePockymon[i] + ":  " + availablePockymonNames[i] + "\n";
		}
		
		System.out.println(s);
	}
	
	private static int requestPockymon() {
		String s = null;
		int n = -1;
		boolean done = false;
		System.out.println("Please choose any of the following pockymon.");
		printChoices();
		
		do {
			s = sn.nextLine();
			
			try {
				n = Integer.parseInt(s);
				
				for (int i = 0; i < availablePockymon.length; i++) {
					if (n == availablePockymon[i])
						return n;
				}
			} catch (NumberFormatException nfe) {
				for (int i = 0; i < availablePockymonNames.length; i++) {
					if (s.equals(availablePockymonNames[i]))
						return availablePockymon[i];
				}
				
				if (s.equals("Quit") || s.equals("quit") || s.equals("q") || s.equals("Q")) {
					System.exit(0);
				}
			}
		} while (!done);
		
		return -1;
	}
	
	private static int requestNum() {
		String s = null;
		int n = -1;
		boolean done = false;
		
		do {
			s = sn.nextLine();
			
			try {
				n = Integer.parseInt(s);
				
				if (n >= 0)
					return n;
			} catch (NumberFormatException nfe) {
				System.out.println("Please type a number.");
				
				if (s.equals("Quit") || s.equals("quit") || s.equals("q") || s.equals("Q")) {
					System.exit(0);
				}
			}
		} while (!done);
		
		return -1;
	}
	
	private static String requestNickname() {
		String s = null;
		boolean done = false;
		System.out.println("Mmmm yes, and what might be its wonderful name?");
		
		do {
			s = sn.nextLine();
			return s;
		} while (!done);
	}
}
