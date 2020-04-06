package Maestus.Porkyman;

/**
 * An instance of Player that randomly generates its own actions
 * @author Oracle
 *
 */
public class ComputerPlayer extends Player {

	/**
	 * The player may have only one pockymon
	 */
	private final Pockymon pokeball;

	/**
	 * The player may have only one item
	 */
	private IItem tool;

	/**
	 * Construct a new computer pockymon trainer and their kit
	 * @param pockymon
	 * @param item
	 */
	public ComputerPlayer(Pockymon pokeball, IItem tool) {
		super(false);
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
	 * Running away effectively ends the battle by defeating all pockymon in possession
	 */
	@Override
	public void run() {
		pokeball.modHP(-999);
		System.out.println("The other player ran away!");
	}

}
