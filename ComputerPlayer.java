package Maestus.Porkyman;

public class ComputerPlayer extends Player {

	private final Pockymon pokeball;
	private final IItem tool;
	
	public ComputerPlayer(Pockymon pokeball, IItem tool) {
		super(false);
		this.pokeball = pokeball;
		this.tool = tool;
	}

	@Override
	public Pockymon getPockymon() {
		return pokeball;
	}

	@Override
	public IItem getItem() {
		return tool;
	}

	@Override
	public void run() {
		// TODO: Implement logic
		System.out.println("The other player ran away! You win!");
	}

}
