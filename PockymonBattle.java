package Maestus.Porkyman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Maestus.Porkyman.IAction.Target;

public class PockymonBattle {
	private final Player[] participants;
	// TODO: Oh god, please don't let array list be unindexed in a way that <1 2> is equivalent to <2 1>
	private HashMap<Player, ArrayList<String>> options;
	private HashMap<ArrayList<String>, ArrayList<Integer>> skillNum;
	private HashMap<Integer, Integer> teams;
	private LinkedList<Integer> teamNumbers;
	private int playerCount;
	private Scanner sc;
	
	public PockymonBattle(Player... players) {
		sc = new Scanner(System.in);
		options = new HashMap<Player, ArrayList<String>>();
		skillNum = new HashMap<ArrayList<String>, ArrayList<Integer>>();
		teams = new HashMap<Integer, Integer>();
		teamNumbers = new LinkedList<Integer>();
		
		playerCount = 0;
		participants = new Player[players.length];
		
		for (Player p : players) {
			System.out.println(p);
			initializePlayer(p, playerCount);
			playerCount++;
		}
		
		for (Player p: players) {
			p.getPockymon().printSkills();
		}
	}
	
	public void start() {
		if (playerCount <= 1) {
			System.out.println("Not enough players to start battle.");
			return;
		}
		
		if (teamNumbers.size() < 2) {
			System.out.println("There is no battle where there is only friendship.");
			return;
		}
		
		int i = 0;
		for (Player p : participants) {
			System.out.println("\nPlayer " + (i + 1) + " is ready for battle! Good luck, team " + p.getPockymon().getTeam() + "!");
			System.out.println(p.getPockymon().print(p.isHuman()));
			p.getPockymon().speak();
			i++;
		}
		
		battleLoop();
	}
	
	private void battleLoop() {
		boolean finish = testWinConditions();
		if (finish)
			return;
		
		for (Player p : participants) {
			if (p.getPockymon().checkAlive()) {
				System.out.println(p.getPockymon().print(p.isHuman()));
				IAction verb;
				Pockymon[] affectedTargets;
				
				int choice;
				if (p.isHuman()) {
					if (!p.getItem().equals(new NullItem()))
						System.out.println(p.getItem());
					
					choice = requestChoice(p);
				} else {
					choice = (int)(Math.ceil(p.getPockymon().getNumOfMoves() * Math.random()) % p.getPockymon().getNumOfMoves());
					System.out.println(choice + " is what they chose.");
				}

				if (choice >= 0 && choice < p.getPockymon().getNumOfMoves()) {
					verb = p.getPockymon().getSkill(choice);
				} else if (choice == p.getPockymon().getNumOfMoves()) {
					verb = p.getItem();
				} else {
					// Default path for running and malformed response
					System.out.println("What?");
					p.run();
					continue;
				}
				
				System.out.println("Try to do this: " + verb);
				p.getPockymon().printSkills();
				affectedTargets = requestTarget(p, verb);
				System.out.println("Targets: " + affectedTargets);
				verb.doSomething(p.getPockymon(), affectedTargets);
			}
		}
		
		testWinConditions();
		battleLoop();
	}
	
	private void initializePlayer(Player p, int n) {
		int i = 0;
		ArrayList<String> o = new ArrayList<String>();
		ArrayList<Integer> k = new ArrayList<Integer>();
		
		int f = p.getPockymon().getNumOfMoves();
		for (i = 0; i < f; i++) {
			String s = p.getPockymon().getSkill(i).toString().trim();
			o.add(s);
			k.add(i);
		}
		
		o.add(p.getItem().toString().trim());
		i++;
		k.add(i);
		o.add("Run");
		i++;
		k.add(i);
		
		System.out.println(Arrays.deepToString(o.toArray()));
		
		options.put(p, o);
		skillNum.put(o, k);
		
		Integer thisTeam = p.getPockymon().getTeam();
		if (teamNumbers.contains(thisTeam)) {
			Integer teamCount = teams.get(thisTeam);
			teamCount++;
			teams.put(thisTeam, teamCount);
		} else {
			teamNumbers.add(thisTeam);
			Integer teamCount = 1;
			teams.put(thisTeam, teamCount);
		}
		
		if (n < participants.length) {
			participants[n] = p;
		}
	}
	
	private int requestChoice(Player p) {
		String s = null;
		int n = -1;
		boolean done = false;
		System.out.println("What will you do?");
		
		do {
			s = sc.nextLine();
			
			try {
				n = Integer.parseInt(s);
				if (n >= 0 && n <= p.getPockymon().getNumOfMoves()) {
					return n;
				}
			} catch (NumberFormatException nfe) {
				ArrayList<String> keys = options.get(p);
				ArrayList<Integer> values = skillNum.get(keys);
				
				if (s != null) {
					for (String k : keys) {
						if (k.equals(s)) {
							int result = values.get(keys.indexOf(k));
							return result;
						}
					}
				}
				
				if (s.equals("Quit") || s.equals("quit") || s.equals("q") || s.equals("Q")) {
					System.exit(0);
				}
			}
		} while (!done);
		
		return -1;
	}
	
	private Pockymon[] requestTarget(Player p, IAction v) {
		// TODO: Implement teams better
		Target hit = v.getTarget();
		int thisTeam = p.getPockymon().getTeam();
		
		switch(hit) {
		case SELF: {
			Pockymon[] self = new Pockymon[1];
			self[0] = p.getPockymon();
			return self;
		}
		case ALLIES: {
			List<Player> helpers = new LinkedList<Player>();

			for (Player who : participants) {
				if (who.getPockymon().getTeam() == thisTeam) {
					helpers.add(who);
				}
			}
			
			Pockymon[] allies = new Pockymon[helpers.size()];
			for (int i = 0; i < helpers.size(); i++) {
				allies[i] = helpers.get(i).getPockymon();
			}
			
			return allies;
		}
		case FOE: {
			// TODO: Scan for player choice instead of randomly picking enemy
			// ...or the battle is too furious to coordinate exact commands?
			Pockymon[] enemy = new Pockymon[1];
			
			for (int i = 0; i < 20; i++) {
				int rand = (int)(Math.ceil(participants.length * Math.random()) % participants.length);
				if (participants[rand].getPockymon().getTeam() != thisTeam) {
					enemy[0] = participants[rand].getPockymon();
					i = 20;
				}
			}
			
			if (enemy[0].equals(null)) {
				System.out.println("Sorry for my laziness. You hit the 1 in a million chance that random couldn't find the enemy.");
				return null;
			}
			
			// Flavor text
			p.getPockymon().attack(enemy[0]);
			return enemy;
			
		}
		case RIVALS: {
			List<Player> enemies = new LinkedList<Player>();

			for (Player who : participants) {
				if (who.getPockymon().getTeam() != thisTeam) {
					enemies.add(who);
				}
			}
			
			Pockymon[] rivals = new Pockymon[enemies.size()];
			for (int i = 0; i < enemies.size(); i++) {
				rivals[i] = enemies.get(i).getPockymon();
			}
			
			return rivals;
			
		}
		//case OTHER: { /* hit any one pockymon by choice except self */ }
		case ALL: {
			Pockymon[] all = new Pockymon[participants.length];
			for (int i = 0; i < participants.length; i++) {
				all[i] = participants[i].getPockymon();
			}
			return all;
		}
		//case EXCEPT: { /* hit every pockymon except self */ }
		//case ANY: { /* the world is your oyster so pick any one pockymon */ }
		//case RANDOM: { /* randomly hit one pockymon */ }
		case NONE: {
			return null;
		}
		default: {
			System.out.println("Something bad happened in the interface.");
			return null;
		}
		}
	}
	
	private boolean testWinConditions() {
		int numAlive = participants.length;
		int testFinish = -1;
		
		// Testing for teams
		// TODO: Fix this loop because this doesn't work rn
		for (Player p : participants) {
			boolean flag = false;
			if (!p.getPockymon().checkAlive()) {
				numAlive--;
			} else {
				if (flag == false && testFinish != p.getPockymon().getTeam()) {
					testFinish = p.getPockymon().getTeam();
					flag = true;
				} else {
					return false;
				}
			}
		}
		
		System.out.println("Value of test: " + testFinish + " \t Val of alive: " + numAlive);
		// Simple tests
		if (numAlive == 1) {
			executeWin();
			return true;
		} else if (numAlive == 0) {
			executeDraw();
			return true;
		}
		
		return false;
	}
	
	private void executeWin() {
		for (Player p : participants) {
			if (p.getPockymon().checkAlive())
				System.out.println(p.getPockymon().getNickname() + " is the winnner!");
		}
	}
	
	private void executeDraw() {
		System.out.println("It's a draw!");
	}
}
