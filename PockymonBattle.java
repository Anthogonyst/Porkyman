package Maestus.Porkyman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Maestus.Porkyman.IAction.Target;

public class PockymonBattle {
	// TODO: Separate initialization from runtime logic
	
	private final Player[] participants;
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
			initializePlayer(p, playerCount);
			playerCount++;
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
		
		System.out.println("=======================================");
		for (Player p : participants) {
			boolean turnComplete = false;
			if (p.getPockymon().checkAlive()) {
				//while (!turnComplete) {
					System.out.println(p.getPockymon().print(p.isHuman()));
					IAction verb;
					Pockymon[] affectedTargets;
					
					int choice;
					if (p.isHuman()) {
						if (!p.getItem().equals(new NullItem()))
							System.out.println(p.getItem());
						
						choice = requestChoice(p);
					} else {
						// TODO: Implement Fisher-Yates shuffle here to produce unique results for cycling
						// This would be necessary to success-check a move before execution
						choice = (int)(Math.ceil(p.getPockymon().getNumOfMoves() * Math.random()) % p.getPockymon().getNumOfMoves());
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
				
					affectedTargets = requestTarget(p, verb);
					turnComplete = verb.doSomething(p.getPockymon(), affectedTargets);
					
					if (!p.getPockymon().checkAlive()) {
						turnComplete = true;
					}
					
					if (!turnComplete) {
						boolean completelyExhausted = true;
						
						for (int i = 0; i < p.getPockymon().getNumOfMoves(); i++) {
							if (p.getPockymon().getPP(i) > 0)
								completelyExhausted = false;
						}
						
						if (completelyExhausted) {
							System.out.println(p.getPockymon().getNickname() + " passes out from sheer exhaustion.");
							p.getPockymon().quitFighting();
						}
					}
				//}
			}
			else {
				turnComplete = true;
			}
		}
		
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
		i = f;
		k.add(i);
		o.add(new String("Run"));
		i++;
		k.add(i);
		
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
				if (n >= 0 && n <= p.getPockymon().getNumOfMoves() + 1) {
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
			int[] rando = new int[participants.length];
			
			for (int i = 0; i < rando.length; i++) 
				rando[i] = i;

			// Fisher-Yates shuffle
			for (int i = rando.length - 1; i > 0; i--) {
				int n = (int)(Math.ceil(i*Math.random()) % i);
				int temp = rando[i];
				rando[i] = rando[n];
				rando[n] = temp;
			}
			
			for (int i = 0; i < rando.length; i++) {
				if (participants[rando[i]].getPockymon().getTeam() != thisTeam) {
					enemy[0] = participants[rando[i]].getPockymon();
					p.getPockymon().attack(enemy[0]);
					return enemy;
				}
			}
			
			System.out.println(p.getPockymon().getNickname() + " couldn't find the enemy.");
			enemy[0] = new Typhlosion(null, null, -1);
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
			Pockymon[] none = new Pockymon[0];
			return none;
		}
		default: {
			System.out.println("Something bad happened in the interface.");
			Pockymon[] none = new Pockymon[0];
			return none;
		}
		}
	}
	
	private boolean testWinConditions() {
		int numAlive = participants.length;
		int testFinish = -1;
		boolean flag = false;
		
		// Testing for teams
		for (Player p : participants) {
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
		
		// Test for draw
		if (numAlive == 0) {
			executeDraw();
			return true;
		} else {
			executeWin();
			return true;
		}
	}
	
	private void executeWin() {
		for (Player p : participants) {
			// Check for not living because if your pockymon died, you're the real loser
			if (p.getPockymon().checkAlive())
				System.out.println(p.getPockymon().getNickname() + " is the winnner!");
		}
	}
	
	private void executeDraw() {
		System.out.println("It's a draw!");
	}
}
