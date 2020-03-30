package Maestus.Porkyman;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PorkyRunner {

	private static Scanner sn;
	private static int[] availablePockymon = { 157, 258, 722, 727 };
	private static String[] availablePockymonNames = { "Typhlosion", "Mudkip", "Rowlet", "Incineroar" };
	
	public static void main(String[] args) {
		List<Player> theHungerGames = new ArrayList<Player>();
		sn = new Scanner(System.in);
		int team = 0;
		
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
		return new HumanPlayer(generatePockymon(pokeball, team), new HiPotion());
	}
	
	private static Player makeComputerPlayer(int team) {
		int rand = (int)(availablePockymon.length * Math.ceil(Math.random()) % availablePockymon.length);
		return new ComputerPlayer(generatePockymon(rand, team), new HiPotion());
	}
	
	private static Pockymon generatePockymon(int n, int team) {
		final List<Skill> doStuff = new ArrayList<Skill>();
		doStuff.add(new StringSkill());
		doStuff.add(new StringSkill());
		doStuff.add(new StringSkill());
		
		switch (n) {
		case 157: {
			doStuff.add(new Eruption());
			return new Typhlosion("Tyco", doStuff, team);
		}
		case 258: {
			doStuff.add(new BubbleBeam());
			return new Mudkip("Mudkipz", doStuff, team);
		}
		case 722: {
			doStuff.add(new Synthesis());
			return new Rowlet("Birb", doStuff, team);
		}
		case 727: {
			doStuff.add(new DarkestLariat());
			return new Incineroar("Barcat Barcat", doStuff, team);
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
}
