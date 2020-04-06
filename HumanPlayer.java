package Maestus.Porkyman;

/**
 * An instance of player that is controlled and dictated by human actions
 * @author Oracle
 *
 */
public class HumanPlayer extends Player {

	/**
	 * The player may have only one pockymon
	 */
	private final Pockymon pokeball;
	
	/**
	 * The player may have only one item
	 */
	private IItem tool;
	
	/**
	 * Construct a new human-powered pockymon trainer and their kit
	 * @param pockymon
	 * @param item
	 */
	public HumanPlayer(Pockymon pokeball, IItem tool) {
		super(true);
		this.pokeball = pokeball;
		this.tool = tool;
	}

	/**
	 * Retrieves the player's pockymon
	 */
	@Override
	public Pockymon getPockymon() {
		return pokeball;
	}

	/**
	 * Retrieves the player's item
	 */
	@Override
	public IItem getItem() {
		if (tool.depleteItem()) {
			tool = new NullItem();
		}
		return tool;
	}
	
	/**
	 * Running is disallowed for human players
	 */
	@Override
	public void run() {
		System.out.println("You can't run away from this fight!");
	}

}
