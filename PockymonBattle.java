package Maestus.Porkyman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PockymonBattle {
	private final Player[] participants;
	// TODO: Oh god, please don't let array list be unindexed in a way that <1 2> is equivalent to <2 1>
	private HashMap<Player, ArrayList<String>> options;
	private HashMap<ArrayList<String>, ArrayList<Integer>> skillNum;
	private int playerCount;
	private Scanner sc;
	
	public PockymonBattle(Player... players) {
		sc = new Scanner(System.in);
		options = new HashMap<Player, ArrayList<String>>();
		skillNum = new HashMap<ArrayList<String>, ArrayList<Integer>>();
		
		playerCount = 0;
		participants = new Player[2];
		
		for (Player p : players) {
			System.out.println(p);
			initializePlayer(p, playerCount);
			playerCount++;
		}
	}
	
	public void start() {
		if (playerCount <= 1) {
			System.out.println("Not enough players to start battle.");
		}
		
		for (Player p : participants) {
			System.out.println(p.getPockymon());
		}
		
		battleLoop();
	}
	
	private void battleLoop() {
		int numAlive = participants.length;
		
		for (Player p : participants) {
			if (!p.getPockymon().checkAlive()) {
				numAlive--;
			}
		}
		
		if (numAlive == 1) {
			executeWin();
			return;
		} else if (numAlive == 0) {
			executeDraw();
			return;
		}
		
		for (Player p : participants) {
			if (p.getPockymon().checkAlive()) {
				System.out.println(p.getPockymon());
				IAction verb;
				
				if (p.isHuman()) {
					System.out.println(p.getItem());
					verb = p.getPockymon().getSkill(requestChoice(p));
					if (verb == null || verb.equals(new NullSkill())) {
						verb = p.getItem();
					}
				} else {
					verb = p.getPockymon().getSkill(0);
				}
				
				// TODO: Make target reticle on other instead of hitting yourself
				Pockymon[] fake = new Pockymon[1];
				fake[0] = p.getPockymon();
				verb.doSomething(p.getPockymon(), fake);
			}
		}
		

		for (Player p : participants) {
			System.out.println(p.getPockymon());
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
		i++;
		k.add(i);
		o.add("Run");
		i++;
		k.add(i);
		
		System.out.println(Arrays.deepToString(o.toArray()));
		
		options.put(p, o);
		skillNum.put(o, k);
		
		if (n <= participants.length) {
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
	
	private void executeWin() {
		for (Player p : participants) {
			if (p.getPockymon().checkAlive())
				System.out.println(p.getPockymon().getNickname() + " is the winnner!");
		}
	}
	
	private void executeDraw() {
		System.out.println("It's a draw!");
	}
	
	private enum State {
		PLAYER_TURN,
		COMPUTER_TURN,
		ALL_TURNS_DONE,
		TEAM_DEFEATED,
		TEAM_WIN,
		ONE_TEAM_LEFT;
	}
}
