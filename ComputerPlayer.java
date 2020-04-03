package Maestus.Porkyman;

public class ComputerPlayer extends Player {

	private final Pockymon pokeball;
	private IItem tool;
	
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
		if (tool.depleteItem()) {
			tool = new NullItem();
		}
		return tool;
	}

	@Override
	public void run() {
		pokeball.modHP(-999);
		System.out.println("The other player ran away!");
	}

}
